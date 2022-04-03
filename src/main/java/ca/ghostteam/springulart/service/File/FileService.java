package ca.ghostteam.springulart.service.File;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-02
 */
public interface FileService {
    String uploadImage(MultipartFile file);
}
