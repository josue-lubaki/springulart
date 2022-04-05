package ca.ghostteam.springulart.service.reservation.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.*;
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.haircut.HaircutService;
import ca.ghostteam.springulart.service.location.LocationService;
import ca.ghostteam.springulart.service.reservation.ReservationService;
import ca.ghostteam.springulart.service.reservationtime.ReservationTimeService;
import ca.ghostteam.springulart.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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
    private final UtilsReservationService utils;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserService userService,
                                  HaircutService haircutService,
                                  ReservationTimeService reservationTimeService,
                                  LocationService locationService,
                                  UtilsReservationService utils) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.haircutService = haircutService;
        this.reservationTimeService = reservationTimeService;
        this.locationService = locationService;
        this.utils = utils;
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
        reservationTime.setReservationModel(Set.of(reservationModelToSave));
        location.setReservationModel(reservationModelToSave);
        haircutModel.setReservationModel(Set.of(reservationModelToSave));
        userModel.setReservationModelClient(Set.of(reservationModelToSave));

        if (Objects.nonNull(barber)) {
            barber.setReservationModelBarber(Set.of(reservationModelToSave));
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

        // Modify reservationTime and Location of reservation
        ReservationTimeDTO reservationTimeDTO = reservation.getReservationTime();
        reservationTimeDTO.setId(reservationModelToUpdate.getReservationTime().getId());
        reservationTimeService.update(id, reservationTimeDTO).get();

        LocationDTO locationDTO = reservation.getLocation();
        locationDTO.setId(reservationModelToUpdate.getLocation().getId());
        locationService.update(id, locationDTO).get();

        // retrieve again reservation with new values
        reservationModelToUpdate = utils.converterModelToDTO(
                reservationRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException(String.format("Reservation with ID %s cannot found", id))
                )
        );

        // return reservation with new values
        return Optional.of(reservationModelToUpdate);
    }


    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public long count() {
        return reservationRepository.findAll().size();
    }

}