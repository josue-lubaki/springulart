//package ca.ghostteam.springulart.controller.haircut;
//
//import ca.ghostteam.springulart.dto.HaircutDTO;
//import ca.ghostteam.springulart.service.HaircutService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiResponse;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
///**
// * @author Josue Lubaki
// * @version 1.0
// * @since 2022-03-25
// */
//@RestController
//@RequestMapping("/api/v1/haircuts")
//public class HaircutController {
//
//    private final HaircutService haircutService;
//
//    public HaircutController(HaircutService haircutService) {
//        this.haircutService = haircutService;
//    }
//
//    @ApiResponse(code = 200, message = "Successfully retrieved all haircuts")
//    @GetMapping
//    public List<HaircutDTO> getHaircuts() {
//        return haircutService.findAllHaircuts();
//    }
//
//    @ApiResponse(code = 200, message = "Successfully retrieved haircut")
//    @GetMapping("/{id}")
//    public HaircutDTO getHaircut(@PathVariable("id") String id) {
//        return haircutService
//                .findHaircutById(id)
//                .orElseThrow(() -> new NoSuchElementException(String.format("Haircut with id %s not found", id)));
//    }
//}
