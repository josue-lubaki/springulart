package ca.ghostteam.springulart.controller.reservation;

import ca.ghostteam.springulart.bean.JwtConfig;
import ca.ghostteam.springulart.dto.ReservationDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.security.ApplicationUserRole;
import ca.ghostteam.springulart.security.jwt.filter.JwtTokenVerifier;
import ca.ghostteam.springulart.service.ReservationService;
import ca.ghostteam.springulart.service.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @ApiResponse(code = 200, message = "Successfully retrieved all reservations of User")
    @GetMapping()
    public List<ReservationDTO> getAllReservations(){
        return this.reservationService.findAll();
    }

    @ApiResponse(code = 200, message = "Successfully retrieved a reservation by id")
    @GetMapping("/{id}")
    public ReservationDTO getReservationById(@PathVariable("id") Long id){
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
    public ReservationDTO updateReservation(@PathVariable("id") Long id, @RequestBody ReservationDTO reservation){
        // check if user has permission to do that
        if(dontDoThisOperation(id))
            throw new IllegalStateException("You are not authorized to update reservation with ID " + id);

        return this.reservationService
                .update(id, reservation)
                .orElseThrow(() -> new IllegalStateException(String.format("the reservation with ID %s was not updated", id)));
    }

    @ApiResponse(code = 204, message = "Successfully deleted a reservation")
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable("id") Long id){
        // check if user has permission to do that
        if(dontDoThisOperation(id))
            throw new IllegalStateException("You are not authorized to delete reservation with ID " + id);

        this.reservationService.deleteReservationById(id);
    }

    @ApiResponse(code = 200, message = "Successfully accepted a reservation")
    @PatchMapping("accept/{id}")
    @PreAuthorize("hasRole('ROLE_BARBER')")
    public ReservationDTO acceptReservation(@PathVariable("id") Long id, @RequestBody ReservationDTO reservation){
        // get headers informations
        UserDetailsDTO userDetails = getUserDetailsDTOForUserSentRequest();
        long idUserWhoSentRequest = userDetails.getCredentials().getId(); // id User

        if(idUserWhoSentRequest == reservation.getClient().getId())
            throw new IllegalStateException("you cannot accept your own reservation");

        // update reservation -> set barber
        Optional<UserDTO> barber = this.userService.findUserById(idUserWhoSentRequest);
        reservation.setBarber(barber.get());

        // modify status
        if(reservation.getStatus().matches("Non Traitée"))
            reservation.setStatus("Acceptée");

        return this.reservationService
                .update(id, reservation)
                .orElseThrow(() -> new IllegalStateException(String.format("the reservation with ID %s was not accepted", id)));
    }

    /**
     * Method that checks if the present user can perform this operation by checking these permissions from their token
     * @param id reservationId to modify or delete
     * @return boolean
     * */
    private boolean dontDoThisOperation(Long  id) {
        // get headers informations
        String token = jwtTokenVerifier.extractJwtToken(request);
        DecodedJWT decodeJWTToken = jwtTokenVerifier.decodeJWT(token, jwtConfig.getSecretKey());

        // Decode a token
        String username = decodeJWTToken.getSubject();
        UserDetailsDTO userDetails = (UserDetailsDTO) userService.loadUserByUsername(username);
        long idUserWhoSentRequest = userDetails.getCredentials().getId(); // id User

        // check if the user has this reservation
        ReservationDTO reservationDTO = this.reservationService.findById(id).get();

        // retrieve the owner reservation
        long idOwnerReservation = reservationDTO.getClient().getId();

        boolean isOwnerReservation = idOwnerReservation == idUserWhoSentRequest;

        // retrieve claims "authorities" from payload of token
        List<Map> authorities = decodeJWTToken.getClaims().get("authorities").asList(Map.class);
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.get("authority").toString()))
                .collect(Collectors.toSet());

        // Check if the user has the required authorization for this request and if user is the owner of reservation
        return !grantedAuthorities.contains(new SimpleGrantedAuthority("reservation:write")) || !isOwnerReservation;
    }

    /**
     * Method that returns ID of the user who sent the request
     * @return Long
     **/
    private Long getIdUserCurrent(){
        UserDetailsDTO userDetails = getUserDetailsDTOForUserSentRequest();
        return userDetails.getCredentials().getId();
    }

    /**
     * Method to extract the Role of user sent request
     * @return String
     * */
    private String getRoleUserSentRequest() {
        UserDetailsDTO userDetails = getUserDetailsDTOForUserSentRequest();
        return userDetails.getCredentials().getGrantedAuthority();
    }

    /**
     * Method to extract the UserDetailsDTO of user sent request
     * @return UserDetailsDTO
     * @throws IllegalStateException if the user is not found
     * */
    private UserDetailsDTO getUserDetailsDTOForUserSentRequest() {
        // get headers informations
        String token = jwtTokenVerifier.extractJwtToken(request);
        DecodedJWT decodeJWTToken = jwtTokenVerifier.decodeJWT(token, jwtConfig.getSecretKey());

        // Decode a token
        String username = decodeJWTToken.getSubject();
        return (UserDetailsDTO) userService.loadUserByUsername(username);
    }

}
