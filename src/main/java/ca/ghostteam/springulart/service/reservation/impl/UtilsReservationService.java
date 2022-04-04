package ca.ghostteam.springulart.service.reservation.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.*;
import org.springframework.stereotype.Component;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsReservationService {

    /**
     * Method to convert a LocationDTO to a LocationModel
     * @param locationDTO LocationDTO to convert
     * @return LocationDTO
     * */
    public LocationModel converterLocationDtoToLocationModel(LocationDTO locationDTO) {
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
    public LocationDTO converterLocationModelToLocationDTO(LocationModel locationModel) {
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
    public ReservationTimeModel converterReservationTimeDtoToReservationTimeModel(ReservationTimeDTO reservationTimeDTO) {
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
    public ReservationTimeDTO converterReservationTimeModelToReservationTimeDTO(ReservationTimeModel reservationTimeModel) {
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
    public AddressModel convertAddressDtoTOAddressModel(AddressDTO address) {
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
    public AddressDTO convertAddressModelTOAddressDTO(AddressModel address) {
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
    public UserModel converterEntityDtoTOEntityModel(UserDTO user) {
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
    public UserDTO converterEntityModelTOEntityDto(UserModel user) {
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
    public HaircutModel converterHaircutDtoToHaircutModel(HaircutDTO haircutDTO) {
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
    public HaircutDTO converterHaircutModelToHaircutDTO(HaircutModel haircutModel) {
        HaircutDTO haircutDTO = new HaircutDTO();
        haircutDTO.setId(haircutModel.getId());
        haircutDTO.setTitle(haircutModel.getTitle());
        haircutDTO.setDescription(haircutModel.getDescription());
        haircutDTO.setPrice(haircutModel.getPrice());
        haircutDTO.setImageURL(haircutModel.getImageURL());
        haircutDTO.setEstimatedTime(haircutModel.getEstimatedTime());
        return haircutDTO;
    }

    /**
     * Method to convert a reservationModel to a reservationDTO
     * @param reservationModel the reservationModel to convert
     * @return ReservationDTO
     */
    public ReservationDTO converterModelToDTO(ReservationModel reservationModel) {
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
    public ReservationModel converterDtoToModel(ReservationDTO reservationDTO) {
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
