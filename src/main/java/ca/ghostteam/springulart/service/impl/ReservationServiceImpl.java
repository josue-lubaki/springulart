package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.LocationModel;
import ca.ghostteam.springulart.model.ReservationModel;
import ca.ghostteam.springulart.model.ReservationTimeModel;
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserService userService,
                                  HaircutService haircutService,
                                  ReservationTimeService reservationTimeService,
                                  LocationService locationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.haircutService = haircutService;
        this.reservationTimeService = reservationTimeService;
        this.locationService = locationService;
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository
                .findAll()
                .stream()
                .map(this::converterModelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationDTO> findById(String id) {
        return reservationRepository
                .findById(id)
                .map(this::converterModelToDTO);
    }

    @Override
    public Optional<ReservationDTO> save(ReservationDTO reservation) {
        // create a client and save it
        UserDTO client = reservation.getClient();
        boolean existClient = userService.existsUserByEmail(client.getEmail());

        if(!existClient)
            throw new IllegalStateException();

        // create time Reservation and Save it
        ReservationTimeDTO reservationTimeDTO = reservation.getReservationTime();
        ReservationTimeModel reservationTimeSaved = reservationTimeService.save(reservationTimeDTO).get();

        // create Location and save it
        LocationDTO locationModel = reservation.getLocation();
        LocationModel locationSaved = locationService.save(locationModel).get();

        // set foreign keys
        ReservationModel reservationModelToSave = converterDtoToModel(reservation);
        reservationModelToSave.setReservationTime(reservationTimeSaved.getId());
        reservationModelToSave.setLocation(locationSaved.getId());

        ReservationModel reservationModelSaved = reservationRepository.save(reservationModelToSave);
        reservation.setId(reservationModelSaved.getId());

        return Optional.of(reservation);
    }

    @Override
    public Optional<ReservationDTO> update(String id, ReservationDTO reservation) {
        // get reservation
        ReservationDTO reservationModelToUpdate =
                converterModelToDTO(
                        Objects.requireNonNull(
                                reservationRepository
                                .findById(id)
                                .orElse(null)
                        )
                );

        //  and updated information
        reservationModelToUpdate.setReservationTime(reservation.getReservationTime());
        reservationModelToUpdate.setReservationDate(reservation.getReservationDate());
        reservationModelToUpdate.setClient(reservation.getClient());
        reservationModelToUpdate.setStatus(reservation.getStatus());
        reservationModelToUpdate.setHaircut(reservation.getHaircut());
        reservationModelToUpdate.setBarber(reservation.getBarber());
        reservationModelToUpdate.setLocation(reservation.getLocation());

        reservation.setId(reservationModelToUpdate.getId());

        // save modification
        reservationRepository.save(converterDtoToModel(reservationModelToUpdate));

        return Optional.of(reservation);
    }

    @Override
    public void deleteById(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public long count() {
        return reservationRepository.findAll().size();
    }

    /**
     * Method to convert a reservationModel to a reservationDTO
     * @param reservationModel the reservationModel to convert
     * @return ReservationDTO
     */
    private ReservationDTO converterModelToDTO(ReservationModel reservationModel) {
        ReservationDTO reservationDTO = new ReservationDTO();
        // retrieve ReservationTime
        ReservationTimeDTO reservationTimeDTO = reservationTimeService.findById(reservationModel.getReservationTime()).get();

        // retrieve Haircut information
        String haircut = reservationModel.getHaircut();
        Optional<HaircutDTO> haircutDTO = haircutService.findAllHaircuts()
                .stream().filter(h -> h.getId().equals(haircut))
                .findFirst();

        // get Location information
        LocationDTO location = locationService.findById(reservationModel.getLocation()).get();

        // retrieve Client & barber information
        UserDTO client = userService.findUserById(reservationModel.getClient()).get();
        Optional<UserDTO> barberOptional = userService.findUserById(reservationModel.getBarber());
        UserDTO barber = null;

        if(barberOptional.isPresent())
            barber = barberOptional.get();

        reservationDTO.setId(reservationModel.getId());
        reservationDTO.setReservationDate(reservationModel.getReservationDate());
        reservationDTO.setReservationTime(reservationTimeDTO);
        reservationDTO.setHaircut(haircutDTO.get());
        reservationDTO.setStatus(reservationModel.getStatus());
        reservationDTO.setClient(client);
        reservationDTO.setBarber(barber);
        reservationDTO.setLocation(location);

        return reservationDTO;
    }

    /**
     * Method to convert a ReservationDTO to a ReservationModel
     * @param reservationDTO the reservationDTO to convert
     * @return ReservationModel
     */
    private ReservationModel converterDtoToModel(ReservationDTO reservationDTO) {
        ReservationModel reservationModel = new ReservationModel();

        Long idBarber = null;

        if(reservationDTO.getBarber() != null && reservationDTO.getBarber().getId() != null)
            idBarber = reservationDTO.getBarber().getId();

        reservationModel.setReservationDate(reservationDTO.getReservationDate());
        reservationModel.setReservationTime(reservationDTO.getReservationTime().getId());
        reservationModel.setHaircut(reservationDTO.getHaircut().getId());
        reservationModel.setStatus(reservationDTO.getStatus());
        reservationModel.setClient(reservationDTO.getClient().getId());
        reservationModel.setBarber(idBarber);
        reservationModel.setLocation(reservationDTO.getLocation().getId());

        return reservationModel;
    }
}
