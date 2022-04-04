package ca.ghostteam.springulart.service.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
@Service
public class AWSS3ServiceImpl implements FileService {

    private final AmazonS3Client awsS3Client;

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    public AWSS3ServiceImpl(@Qualifier("amazonS3Client") AmazonS3Client awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        // Check if file is empty
        if (file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is empty");
        }

        String fileName = file.getOriginalFilename();

        // Check if file name hasText
        if (!StringUtils.hasText(fileName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is empty");
        }

        // remove extension from file name
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        // verify if file is an image with extension jpg, jpeg, png
        if (!validateExtension(extension)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File is not an image");
        }

        // create unique fileName for file
        fileName = UUID.randomUUID() + "." + extension;

        // create metadata for file and set content type
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try{
            // upload file to S3
            awsS3Client.putObject(bucketName, fileName, file.getInputStream(), metadata);
        }
        catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error uploading file to S3");
        }

        // set permissions to public
        awsS3Client.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);

        // return url to file
        return awsS3Client.getResourceUrl(bucketName, fileName);
    }

    @Override
    public void deleteImage(String imageURL) {
        // check if file name hasText
        if (!StringUtils.hasText(imageURL)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name is empty");
        }

        // extract file name from url
        String fileName = imageURL.substring(imageURL.lastIndexOf("/") + 1);

        // delete file from S3
        awsS3Client.deleteObject(bucketName, fileName);
    }

    /**
     * Validate if file name is valid
     * @param originalFilename file name of file
     * @return true if file name is valid
     */
    private boolean validateName(String originalFilename) {
        // regex to validate file name with only letters, numbers, underscores and dashes
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\\-]+$");
        return pattern.matcher(originalFilename).matches();
    }

    /**
     * Validate if file is an image with extension jpg, jpeg, png
     * @param originalFilename file name of file
     * @return true if file is an image
     */
    private boolean validateExtension(String originalFilename) {
        String[] extensions = {"jpg", "jpeg", "png", "svg"};
        for (String extension : extensions) {
            if (originalFilename.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

}
