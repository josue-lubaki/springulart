package ca.ghostteam.springulart.service.file;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
public interface FileService {
    String uploadImage(MultipartFile file);

    @Transactional
    void deleteImage(String fileName);
}
