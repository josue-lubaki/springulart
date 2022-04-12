package ca.ghostteam.springulart.service.reservation.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.*;
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.reservation.ReservationService;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-27
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final HaircutService haircutService;
    private final ReservationTimeService reservationTimeService;
    private final LocationService locationService;
    private final UtilsReservation utils;
    private final MailService mailService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserService userService,
                                  HaircutService haircutService,
                                  ReservationTimeService reservationTimeService,
                                  LocationService locationService,
                                  UtilsReservation utils,
                                  MailService mailService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.haircutService = haircutService;
        this.reservationTimeService = reservationTimeService;
        this.locationService = locationService;
        this.utils = utils;
        this.mailService = mailService;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository
                .findAll()
                .stream()
                .map(utils::converterModelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        return reservationRepository
                .findById(id)
                .map(utils::converterModelToDTO);
    }

    @Override
    public Optional<ReservationDTO> save(ReservationDTO reservation) {
        // verify a client
        UserDTO client = userService.findUserByEmail(reservation.getClient().getEmail()).get();
        boolean existClient = userService.existsUserByEmail(client.getEmail());

        // verify a haircut
        HaircutDTO haircut = reservation.getHaircut();
        boolean existHaircutDTO = haircutService.existsHaircutById(haircut.getId());

        if(!existClient || !existHaircutDTO)
            throw new IllegalStateException("haircut not found");

        // convert userDTO to userModel and haircutDTO to haircutModel
        UserModel userModel = utils.converterEntityDtoTOEntityModel(client);
        HaircutModel haircutModel = utils.converterHaircutDtoToHaircutModel(haircut);

        // create time Reservation and Save it
        ReservationTimeDTO reservationTimeDTO = reservation.getReservationTime();
        ReservationTimeDTO reservationTimeSaved = reservationTimeService.save(reservationTimeDTO).get();
        ReservationTimeModel reservationTime = utils.converterReservationTimeDtoToReservationTimeModel(reservationTimeSaved);

        // create Location and save it
        LocationDTO locationModel = reservation.getLocation();
        LocationDTO locationSaved = locationService.save(locationModel).get();
        LocationModel location = utils.converterLocationDtoToLocationModel(locationSaved);


        UserModel barber = null;
        if(Objects.nonNull(reservation.getBarber())) {
                barber = utils.converterEntityDtoTOEntityModel(reservation.getBarber());
        }

        // create ReservationModel
        ReservationModel reservationModelToSave = new ReservationModel();
        reservationModelToSave.setId(null);
        reservationModelToSave.setBarber(barber);
        reservationModelToSave.setClient(userModel);
        reservationModelToSave.setHaircut(haircutModel);
        reservationModelToSave.setReservationTime(reservationTime);
        reservationModelToSave.setLocation(location);

        // set foreign keys values
        reservationTime.setReservationModel(Collections.singletonList(reservationModelToSave));
        location.setReservationModel(reservationModelToSave);
        haircutModel.setReservationModel(Collections.singletonList(reservationModelToSave));
        userModel.setReservationModelClient(Collections.singletonList(reservationModelToSave));

        if (Objects.nonNull(barber)) {
            barber.setReservationModelBarber(Collections.singletonList(reservationModelToSave));
        }

        ReservationModel reservationModelSaved = reservationRepository.save(reservationModelToSave);
        ReservationDTO reservationDTO = utils.converterModelToDTO(reservationModelSaved);

        return Optional.of(reservationDTO);
    }

    @Transactional
    @Override
    public Optional<ReservationDTO> update(Long id, ReservationDTO reservation) {
        // get reservation
        ReservationDTO reservationModelToUpdate =
                utils.converterModelToDTO(
                reservationRepository
                .findById(id)
                .orElseThrow(
                    () -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id))
                )
        );

        // check if hour and minutes of ReservationTime is changed
        if(reservationModelToUpdate.getBarber() != null && (!Objects.equals(reservation.getReservationTime().getHours(), reservationModelToUpdate.getReservationTime().getHours()) ||
                !Objects.equals(reservation.getReservationTime().getMinutes(), reservationModelToUpdate.getReservationTime().getMinutes()))) {
            // send Mail modification Reservation
            String fullNameBarber = reservationModelToUpdate.getBarber().getFullName();
            String fullNameClient = reservationModelToUpdate.getClient().getFullName();
            String emailBarber = reservationModelToUpdate.getBarber().getEmail();
            String timeReservation = reservationModelToUpdate.getReservationTime().toString();
            String dateReservation = String.valueOf(reservationModelToUpdate.getReservationDate());
            mailService.notificationModificationReservation(emailBarber, fullNameBarber, fullNameClient, dateReservation, timeReservation);
        }

        // Modify reservationTime and Location of reservation
        ReservationTimeDTO reservationTimeDTO = reservation.getReservationTime();
        reservationTimeDTO.setId(reservationModelToUpdate.getReservationTime().getId());

        UserModel barberModel = null;
        // set barber if exists
        if(Objects.nonNull(reservation.getBarber())) {
            reservationModelToUpdate.setBarber(reservation.getBarber());

            // change status of reservation
            reservation.setStatus(StatusReservation.ACCEPTED.getEtat());

            // convert UserModel to UserDTO
            barberModel = utils.converterEntityDtoTOEntityModel(reservation.getBarber());

        }

        reservationTimeService.update(id, reservationTimeDTO).get();


        LocationDTO locationDTO = reservation.getLocation();
        locationDTO.setId(reservationModelToUpdate.getLocation().getId());
        locationService.update(id, locationDTO).get();

        // update reservationDate
        reservationModelToUpdate.setReservationDate(reservation.getReservationDate());
        reservationRepository.update(id, reservation.getReservationDate(), barberModel, reservation.getStatus());

        // retrieve again reservation with new values
        reservationModelToUpdate = utils.converterModelToDTO(
                reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id))
                )
        );

        // return reservation with new values
        return Optional.of(reservationModelToUpdate);
    }


    @Override
    public void deleteReservationById(Long id) {
        // get reservation
        ReservationDTO reservationModelToUpdate = utils.converterModelToDTO(
                reservationRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id)))
        );

        // send Mail deleted Reservation to Barber if exists
        if(Objects.nonNull(reservationModelToUpdate.getBarber())) {
            String fullNameBarber = reservationModelToUpdate.getBarber().getFullName();
            String fullNameClient = reservationModelToUpdate.getClient().getFullName();
            String emailBarber = reservationModelToUpdate.getBarber().getEmail();
            String timeReservation = reservationModelToUpdate.getReservationTime().toString();
            String dateReservation = String.valueOf(reservationModelToUpdate.getReservationDate());
            mailService.notificationDeletedReservation(emailBarber, fullNameBarber, fullNameClient, dateReservation, timeReservation);
        }

        reservationRepository.deleteById(id);
    }

    @Override
    public long count() {
        return reservationRepository.findAll().size();
    }

}
