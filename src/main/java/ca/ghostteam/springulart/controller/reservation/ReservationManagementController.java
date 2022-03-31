package ca.ghostteam.springulart.controller.reservation;

import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.service.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@RestController
@RequestMapping("management/api/v1/reservations")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ReservationManagementController {

    private final ReservationService reservationService;

    public ReservationManagementController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved all reservations of User")
    @ApiOperation(value = "Get all reservations")
    @GetMapping()
    public List<ReservationDTO> getAllReservations(){
        return this.reservationService.findAll();
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a reservation by id")
    @ApiOperation(value = "Get a reservation by id")
    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable("id") Long id){
        return this.reservationService
                .findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id)));
    }

    @ApiResponse(code = 201, message = "Successfully save a reservation")
    @ApiOperation(value = "Save a reservation")
    @PostMapping()
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservation){
        return this.reservationService
                .save(reservation)
                .orElseThrow(() -> new IllegalStateException("the reservation was not created"));
    }

    @ApiResponse(code = 200, message = "Successfully updated a reservation")
    @ApiOperation(value = "Update a reservation")
    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable("id") Long id, @RequestBody ReservationDTO reservation){
        return this.reservationService
                .update(id, reservation)
                .orElseThrow(() -> new IllegalStateException(String.format("the reservation with ID %s was not updated", id)));
    }

    @ApiResponse(code = 204, message = "Successfully deleted a reservation")
    @ApiOperation(value = "Delete a reservation")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        this.reservationService.deleteReservationById(id);
    }
}
