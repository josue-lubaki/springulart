package ca.ghostteam.springulart.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Data
public class HaircutCreateDTO {
    private MultipartFile imageURL;
    private Integer price;
    private String title;
    private String estimatedTime;// check frontend
    private String description;
}
