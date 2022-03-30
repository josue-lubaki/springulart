package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.*;
import ca.ghostteam.springulart.repository.ReservationRepository;
import ca.ghostteam.springulart.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
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

//    @Override
//    public List<ReservationDTO> findAll(long idUserWhoSentRequest) {
//        return reservationRepository
//                .findAll()
//                .stream()
//                .filter(r -> r.getClient().getId() == idUserWhoSentRequest)
//                .map(this::converterModelToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository
                .findAll()
                .stream()
                .map(this::converterModelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservationDTO> findById(Long id) {
        return reservationRepository
                .findById(id)
                .map(this::converterModelToDTO);
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

        // convert userDTO to userModel
        // and haircutDTO to haircutModel
        UserModel userModel = converterEntityDtoTOEntityModel(client);
        HaircutModel haircutModel = converterHaircutDtoToHaircutModel(haircut);

        // create time Reservation and Save it
        ReservationTimeDTO reservationTimeDTO = reservation.getReservationTime();
        ReservationTimeDTO reservationTimeSaved = reservationTimeService.save(reservationTimeDTO).get();
        ReservationTimeModel reservationTime = converterReservationTimeDtoToReservationTimeModel(reservationTimeSaved);

        // create Location and save it
        LocationDTO locationModel = reservation.getLocation();
        LocationDTO locationSaved = locationService.save(locationModel).get();
        LocationModel location = converterLocationDtoToLocationModel(locationSaved);


        UserModel barber = null;
        if(Objects.nonNull(reservation.getBarber())) {
                barber = converterEntityDtoTOEntityModel(reservation.getBarber());
        }

        // create ReservationModel
        ReservationModel reservationModelToSave = new ReservationModel();
        reservationModelToSave.setId(null);
        reservationModelToSave.setBarber(barber);
        reservationModelToSave.setClient(userModel);
        reservationModelToSave.setHaircut(haircutModel);
        reservationModelToSave.setReservationTime(reservationTime);
        reservationModelToSave.setLocation(location);

        ReservationModel reservationModelSaved = reservationRepository.save(reservationModelToSave);

        // set foreign keys values
        reservationTime.setReservationModel(Set.of(reservationModelToSave));
        location.setReservationModel(reservationModelToSave);

        haircutModel.setReservationModel(Set.of(reservationModelToSave));

        // convert Model to DTO
        userModel.setReservationModelClient(Set.of(reservationModelToSave));

        if (Objects.nonNull(barber)) {
            barber.setReservationModelBarber(Set.of(reservationModelToSave));
        }

        ReservationDTO reservationDTO = converterModelToDTO(reservationModelSaved);

        return Optional.of(reservationDTO);
    }

    /**
     * Method to convert a LocationDTO to a LocationModel
     * @param locationDTO LocationDTO to convert
     * @return LocationDTO
     * */
    private LocationModel converterLocationDtoToLocationModel(LocationDTO locationDTO) {
        LocationModel locationModel = new LocationModel();
        locationModel.setId(locationDTO.getId());
        locationModel.setReservationModel(null);
        locationModel.setLatitude(locationDTO.getLatitude());
        locationModel.setLongitude(locationDTO.getLongitude());
        return locationModel;
    }

    /**
     * Method to convert a LocationModel to a LocationDTO
     * @param locationModel LocationModel to convert
     * @return LocationModel
     * */
    private LocationDTO converterLocationModelToLocationDTO(LocationModel locationModel) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(locationModel.getId());
        locationDTO.setLatitude(locationModel.getLatitude());
        locationDTO.setLongitude(locationModel.getLongitude());
        return locationDTO;
    }

    /**
     * Method to convert a ReservationTimeDTO to a ReservationTimeModel
     * @param reservationTimeDTO ReservationTimeDTO to convert
     * @return ReservationTimeModel
     * */
    private ReservationTimeModel converterReservationTimeDtoToReservationTimeModel(ReservationTimeDTO reservationTimeDTO) {
        ReservationTimeModel reservationTimeModel = new ReservationTimeModel();
        reservationTimeModel.setId(reservationTimeDTO.getId());
        reservationTimeModel.setHours(reservationTimeDTO.getHours());
        reservationTimeModel.setMinutes(reservationTimeDTO.getMinutes());
        return reservationTimeModel;
    }

    /**
     * Method to convert a ReservationTimeModel to a ReservationTimeDTO
     * @param reservationTimeModel ReservationTimeModel to convert
     * @return ReservationTimeModel
     * */
    private ReservationTimeDTO converterReservationTimeModelToReservationTimeDTO(ReservationTimeModel reservationTimeModel) {
        ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
        reservationTimeDTO.setId(reservationTimeModel.getId());
        reservationTimeDTO.setHours(reservationTimeModel.getHours());
        reservationTimeDTO.setMinutes(reservationTimeModel.getMinutes());
        return reservationTimeDTO;
    }

    /**
     * Method to convert AddressDTO to AddressModel
     * @param address AddressDTO object to convert
     * @return AddressModel
     **/
    private AddressModel convertAddressDtoTOAddressModel(AddressDTO address) {
        AddressModel addressModel = new AddressModel();
        addressModel.setId(address.getId());
        addressModel.setApartement(address.getApartement());
        addressModel.setStreet(address.getStreet());
        addressModel.setZip(address.getZip());
        addressModel.setCity(address.getCity());
        addressModel.setState(address.getState());
        return addressModel;
    }

    /**
     * Method to convert AddressModel to AddressDTO
     * @param address AddressModel object to convert
     * @return AddressDTO
     **/
    private AddressDTO convertAddressModelTOAddressDTO(AddressModel address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setApartement(address.getApartement());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setZip(address.getZip());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        return addressDTO;
    }

    /**
     * Method to convert UserDTO to UserModel
     * @param user UserDTO object to convert
     * @return UserModel
     **/
    private UserModel converterEntityDtoTOEntityModel(UserDTO user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setFname(user.getFname());
        userModel.setLname(user.getLname());
        userModel.setEmail(user.getEmail());
        userModel.setPhone(user.getPhone());
        userModel.setRole(user.getRole());
        userModel.setImageURL(user.getImageURL());
        userModel.setDob(user.getDob());
        userModel.setAddress(convertAddressDtoTOAddressModel(user.getAddress()));
        return userModel;
    }

    /**
     * Method to convert UserModel to UserDTO
     * @param user UserModel object to convert
     * @return UserDTO
     **/
    private UserDTO converterEntityModelTOEntityDto(UserModel user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFname(user.getFname());
        userDTO.setLname(user.getLname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRole(user.getRole());
        userDTO.setImageURL(user.getImageURL());
        userDTO.setDob(user.getDob());
        userDTO.setAddress(convertAddressModelTOAddressDTO(user.getAddress()));
        return userDTO;
    }

    /**
     * Method to convert a haircutDTO to a haircutModel
     * @param haircutDTO the haircutDTO to convert
     * @return the haircutModel
     */
    private HaircutModel converterHaircutDtoToHaircutModel(HaircutDTO haircutDTO) {
        HaircutModel haircutModel = new HaircutModel();
        haircutModel.setId(haircutDTO.getId());
        haircutModel.setTitle(haircutDTO.getTitle());
        haircutModel.setDescription(haircutDTO.getDescription());
        haircutModel.setPrice(haircutDTO.getPrice());
        haircutModel.setImageURL(haircutDTO.getImageURL());
        haircutModel.setEstimatedTime(haircutDTO.getEstimatedTime());
        return haircutModel;
    }

    /**
     * Method to convert a haircutModel to a haircutDTO
     * @param haircutModel the haircutModel to convert
     * @return the haircutDTO
     */
    private HaircutDTO converterHaircutModelToHaircutDTO(HaircutModel haircutModel) {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setId(haircutModel.getId());
        haircutDTO.setTitle(haircutModel.getTitle());
        haircutDTO.setDescription(haircutModel.getDescription());
        haircutDTO.setPrice(haircutModel.getPrice());
        haircutDTO.setImageURL(haircutModel.getImageURL());
        haircutDTO.setEstimatedTime(haircutModel.getEstimatedTime());
        return haircutDTO;
    }

    @Override
    public Optional<ReservationDTO> update(Long id, ReservationDTO reservation) {
        // get reservation
        ReservationDTO reservationModelToUpdate =
                converterModelToDTO(
                    reservationRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new IllegalStateException(String.format("Reservation with ID %s cannot found", id)))
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

        // delete old reservation and save new one
        // deleteReservationById(id);

        // updated reservation and save it
        ReservationModel reservationModel = converterDtoToModel(reservationModelToUpdate);
        ReservationModel modelSave = reservationRepository.save(reservationModel);
//        reservationRepository.update(id, reservationModel);

        // return updated reservation
         return Optional.of(converterModelToDTO(modelSave));

//        reservationRepository.insertReservation(
//                reservationModel.getId(),
//                reservationModel.getReservationDate(),
//                reservationModel.getReservationTime().getId(),
//                reservationModel.getStatus(),
//                reservationModel.getClient().getId(),
//                reservationModel.getHaircut().getId(),
//                reservationModel.getLocation().getId()
//
//        );
//
//
//        return Optional.empty();

    }

    // convert LocalDate to Date
    private Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public void deleteReservationById(Long id) {
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
        ReservationTimeDTO reservationTime = converterReservationTimeModelToReservationTimeDTO(reservationModel.getReservationTime());
        UserDTO client = converterEntityModelTOEntityDto(reservationModel.getClient());
        UserDTO barber = null;
        if (reservationModel.getBarber() != null) {
            barber = converterEntityModelTOEntityDto(reservationModel.getBarber());
        }
        HaircutDTO haircut = converterHaircutModelToHaircutDTO(reservationModel.getHaircut());
        LocationDTO location = converterLocationModelToLocationDTO(reservationModel.getLocation());

        reservationDTO.setId(reservationModel.getId());
        reservationDTO.setReservationTime(reservationTime);
        reservationDTO.setReservationDate(reservationModel.getReservationDate());
        reservationDTO.setClient(client);
        reservationDTO.setStatus(reservationModel.getStatus());
        reservationDTO.setHaircut(haircut);
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

        ReservationTimeModel reservationTime = converterReservationTimeDtoToReservationTimeModel(reservationDTO.getReservationTime());
        HaircutModel haircut = converterHaircutDtoToHaircutModel(reservationDTO.getHaircut());
        UserModel client = converterEntityDtoTOEntityModel(reservationDTO.getClient());
        UserModel barber = null;
        if (reservationDTO.getBarber() != null) {
            barber = converterEntityDtoTOEntityModel(reservationDTO.getBarber());
        }
        LocationModel location = converterLocationDtoToLocationModel(reservationDTO.getLocation());

        reservationModel.setReservationDate(reservationDTO.getReservationDate());
        reservationModel.setReservationTime(reservationTime);
        reservationModel.setHaircut(haircut);
        reservationModel.setStatus(reservationDTO.getStatus());
        reservationModel.setClient(client);
        reservationModel.setBarber(barber);
        reservationModel.setLocation(location);

        return reservationModel;
    }
}
