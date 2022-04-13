package ca.ghostteam.springulart.controller.haircut;

import ca.ghostteam.springulart.dto.HaircutCreateDTO;
import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.service.file.AWSS3ServiceImpl;
import ca.ghostteam.springulart.service.file.FileService;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-26
 */
@RestController
@RequestMapping("management/api/v1/haircuts")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class HaircutManagementController {

    private final HaircutService haircutService;
    private final FileService awss3ServiceImpl;

    public HaircutManagementController(HaircutService haircutService,
                                       FileService awss3ServiceImpl) {
        this.haircutService = haircutService;
        this.awss3ServiceImpl = awss3ServiceImpl;
    }

    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = HaircutDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Get all haircuts")
    @GetMapping
    public List<HaircutDTO> getHaircuts() {
        return this.haircutService.findAllHaircuts();
    }


    @ApiResponses(value = {
            @ApiResponse(code=200, message = "OK", response = HaircutDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Get haircut by ID")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public HaircutDTO getHaircut(@PathVariable("id") String id) {
        return this.haircutService
                .findHaircutById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Haircut with id %s not found", id)));
    }


    @ApiResponses(value = {
            @ApiResponse(code=201, message = "Created", response = HaircutDTO.class),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Create haircut")
    @PostMapping
    @PreAuthorize("hasAuthority('haircut:write')")
    public HaircutDTO createHaircut(@ModelAttribute HaircutCreateDTO haircutCreateDTO) {

        // Retrieve imageURL from haircutCreateDTO
        MultipartFile image = haircutCreateDTO.getImageURL();
        // upload image to S3
        String imageURL = this.awss3ServiceImpl.uploadImage(image, "haircuts");

        // Convert haircutCreateDTO to HaircutDTO
        HaircutDTO haircutDTO = converterHaircutCreateDtoToHaircutDTO(haircutCreateDTO, imageURL);

        return this.haircutService
                .saveHaircut(haircutDTO)
                .orElseThrow(() -> new IllegalStateException("Haircut not created"));
    }

    /**
     * Method to convert HaircutCreateDTO to HaircutDTO
     * @param haircutCreateDTO HaircutCreateDTO object to convert to HaircutDTO
     * @param imageURL imageURL to add to HaircutDTO object
     * @return urn HaircutDTO object
     * */
    private HaircutDTO converterHaircutCreateDtoToHaircutDTO(HaircutCreateDTO haircutCreateDTO, String imageURL) {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setTitle(haircutCreateDTO.getTitle());
        haircutDTO.setDescription(haircutCreateDTO.getDescription());
        haircutDTO.setPrice(haircutCreateDTO.getPrice());
        haircutDTO.setEstimatedTime(haircutCreateDTO.getEstimatedTime());
        haircutDTO.setImageURL(imageURL);
        return haircutDTO;
    }


    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Deleted hair"),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Delete haircut")
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('haircut:write')")
    public void deleteHaircut(@PathVariable("id") String id){
        // get imageURL from haircut
        String imageURL = this.haircutService
                .findHaircutById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format("Haircut with id %s not found", id))
                ).getImageURL();

        if(imageURL != null) {
            // get name of image from imageURL
            String imageName = imageURL.substring(imageURL.lastIndexOf("/") + 1);

            // delete image from S3
            this.awss3ServiceImpl.deleteImage(imageName, "haircuts");
        }

        this.haircutService.deleteHaircut(id);
    }


    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Updated hair"),
            @ApiResponse(code=400, message = "Bad Request"),
    })
    @ApiOperation(value = "Update haircut")
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('haircut:write')")
    public HaircutDTO updateHaircut(@PathVariable("id") String haircutId,
                                    @RequestBody HaircutDTO haircutDTO){
        return this.haircutService
                .updateHaircut(haircutId, haircutDTO)
                .orElseThrow(() -> new IllegalStateException(String.format("haircut with ID %s is not updated", haircutId)));
    }
}
