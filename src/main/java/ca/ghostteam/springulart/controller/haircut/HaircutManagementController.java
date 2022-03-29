//package ca.ghostteam.springulart.controller.haircut;
//
//import ca.ghostteam.springulart.dto.HaircutDTO;
//import ca.ghostteam.springulart.service.HaircutService;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
///**
// * @author Josue Lubaki
// * @version 1.0
// * @since 2022-03-26
// */
//@RestController
//@RequestMapping("management/api/v1/haircuts")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//public class HaircutManagementController {
//
//    private final HaircutService haircutService;
//
//    public HaircutManagementController(HaircutService haircutService) {
//        this.haircutService = haircutService;
//    }
//
//    @ApiResponses(value = {
//            @ApiResponse(code=200, message = "OK", response = HaircutDTO.class),
//            @ApiResponse(code=400, message = "Bad Request"),
//    })
//    @GetMapping
//    public List<HaircutDTO> getHaircuts() {
//        return this.haircutService.findAllHaircuts();
//    }
//
//
//    @ApiResponses(value = {
//            @ApiResponse(code=200, message = "OK", response = HaircutDTO.class),
//            @ApiResponse(code=400, message = "Bad Request"),
//    })
//    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public HaircutDTO getHaircut(@PathVariable("id") String id) {
//        return this.haircutService
//                .findHaircutById(id)
//                .orElseThrow(() -> new NoSuchElementException(String.format("Haircut with id %s not found", id)));
//    }
//
//
//    @ApiResponses(value = {
//            @ApiResponse(code=201, message = "Created", response = HaircutDTO.class),
//            @ApiResponse(code=400, message = "Bad Request"),
//    })
//    @PostMapping
//    @PreAuthorize("hasAuthority('haircut:write')")
//    public HaircutDTO createHaircut(@RequestBody HaircutDTO haircutDTO) {
//        return this.haircutService
//                .saveHaircut(haircutDTO)
//                .orElseThrow(() -> new IllegalStateException("Haircut not created"));
//    }
//
//
//    @ApiResponses(value = {
//            @ApiResponse(code=200, message = "Deleted hair"),
//            @ApiResponse(code=400, message = "Bad Request"),
//    })
//    @DeleteMapping(path = "/{haircutId}")
//    @PreAuthorize("hasAuthority('haircut:write')")
//    public void deleteHaircut(@PathVariable("haircutId") String haircutId){
//        this.haircutService.deleteHaircut(haircutId);
//    }
//
//
//    @ApiResponses(value = {
//            @ApiResponse(code=200, message = "Updated hair"),
//            @ApiResponse(code=400, message = "Bad Request"),
//    })
//    @PutMapping(path = "/{haircutId}")
//    @PreAuthorize("hasAuthority('haircut:write')")
//    public HaircutDTO updateHaircut(@PathVariable("haircutId") String haircutId,
//                                    @RequestBody HaircutDTO haircutDTO){
//        return this.haircutService
//                .updateHaircut(haircutId, haircutDTO)
//                .orElseThrow(() -> new IllegalStateException(String.format("haircut with ID %s is not updated", haircutId)));
//    }
//}
