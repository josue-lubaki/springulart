package ca.ghostteam.springulart.controller.reservation;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.ReservationService;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;
    private final HttpServletRequest request;
    private final JwtConfig jwtConfig;
    private final JwtTokenVerifier jwtTokenVerifier;

    public ReservationController(ReservationService reservationService,
                                 UserService userService,
                                 HttpServletRequest request,
                                 JwtConfig jwtConfig,
                                 JwtTokenVerifier jwtTokenVerifier) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.request = request;
        this.jwtConfig = jwtConfig;
        this.jwtTokenVerifier = jwtTokenVerifier;
    }

    @ApiResponse(code = 200, message = "Successfully retrieved all reservations")
    @GetMapping()
    public List<ReservationDTO> getAllReservations(){
        return this.reservationService.findAll();
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a reservation by id")
    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable("id") String id){
        return this.reservationService
                .findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id)));
    }

    @ApiResponse(code = 201, message = "Successfully save a reservation")
    @PostMapping()
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservation){
        return this.reservationService
                .save(reservation)
                .orElseThrow(() -> new IllegalStateException("the reservation was not created"));
    }

    @ApiResponse(code = 200, message = "Successfully updated a reservation")
    @PutMapping("/{id}")
    public ReservationDTO updateReservation(@PathVariable("id") String id, @RequestBody ReservationDTO reservation){
        // check if user has permission to do that
        if(dontDoThisOperation(id))
            throw new IllegalStateException("You are not authorized to update reservation with ID " + id);

        return this.reservationService
                .update(id, reservation)
                .orElseThrow(() -> new IllegalStateException(String.format("the reservation with ID %s was not updated", id)));
    }

    @ApiResponse(code = 204, message = "Successfully deleted a reservation")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") String id){
        // check if user has permission to do that
        if(dontDoThisOperation(id))
            throw new IllegalStateException("You are not authorized to delete reservation with ID " + id);

        this.reservationService.deleteById(id);
    }

    /**
     * Method that checks if the present user can perform this operation by checking these permissions from their token
     * @param id reservationId to modify or delete
     * @return boolean
     * */
    private boolean dontDoThisOperation(String  id) {
        // get headers informations
        String token = jwtTokenVerifier.extractJwtToken(request);
        DecodedJWT decodeJWTToken = jwtTokenVerifier.decodeJWT(token, jwtConfig.getSecretKey());

        // Decode a token
        String username = decodeJWTToken.getSubject();
        UserDetailsDTO userDetails = (UserDetailsDTO) userService.loadUserByUsername(username);
        int idUserWhoSentRequest = userDetails.getCredentials().getId(); // id User

        // check if the user has this reservation
        ReservationDTO reservationDTO = this.reservationService.findById(id).get();

        // retrieve the owner reservation
        int idOwnerReservation = reservationDTO.getClient().getId();

        boolean isOwnerReservation = idOwnerReservation == idUserWhoSentRequest;

        // retrieve claims "authorities" from payload of token
        List<Map> authorities = decodeJWTToken.getClaims().get("authorities").asList(Map.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("authority").toString()))
                .collect(Collectors.toSet());

        // Check if the user has the required authorization for this request and if user is the owner of reservation
        return !grantedAuthorities.contains(new SimpleGrantedAuthority("reservation:write")) || !isOwnerReservation;
    }

}
