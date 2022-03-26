package ca.ghostteam.springulart.controller.haircut;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.service.HaircutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/api/v1/haircuts")
public class HaircutController {

    private final HaircutService haircutService;

    public HaircutController(HaircutService haircutService) {
        this.haircutService = haircutService;
    }

    @GetMapping
    public List<HaircutDTO> getHaircuts() {
        return haircutService.findAllHaircuts();
    }

    @GetMapping("/{id}")
    public HaircutDTO getHaircut(@PathVariable("id") String id) {
        return haircutService
                .findHaircutById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Haircut with id %s not found", id)));
    }
}