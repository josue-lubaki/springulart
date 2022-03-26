package ca.ghostteam.springulart.controller.haircut;

import ca.ghostteam.springulart.dto.HaircutDTO;
import ca.ghostteam.springulart.service.HaircutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    public HaircutManagementController(HaircutService haircutService) {
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

    @PostMapping
    @PreAuthorize("hasAuthority('haircut:write')")
    public void createHaircut(@RequestBody HaircutDTO haircutDTO) {
        System.out.println("registerNewHaircut a haircut");
        System.out.println(haircutDTO);
    }

    @DeleteMapping(path = "/{haircutId}")
    @PreAuthorize("hasAuthority('haircut:write')")
    public void deleteHaircut(@PathVariable("haircutId") String haircutId){
        System.out.println("deleteHaircut a haircut");
        System.out.println(haircutId);
    }

    @PutMapping(path = "/{haircutId}")
    @PreAuthorize("hasAuthority('haircut:write')")
    public void updateHaircut(@PathVariable("haircutId") String haircutId, @RequestBody HaircutDTO haircutDTO){
        System.out.println("updateHaircut a haircut");
        System.out.printf("%s %s\n", haircutId, haircutDTO);
    }
}
