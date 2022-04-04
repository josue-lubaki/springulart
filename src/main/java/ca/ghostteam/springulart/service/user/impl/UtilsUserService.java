package ca.ghostteam.springulart.service.user.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-04
 */
@Component
public class UtilsUserService {

    private final PasswordEncoder passwordEncoder;

    public UtilsUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Method to convert CredentialModel from CredentialDTO
     * @param credentialDTO credentialDTO to extract
     * @return CredentialModel
     */
    public CredentialModel converterCredentialDtoToCredentialModel(CredentialDTO credentialDTO) {
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setId(credentialDTO.getId());
        credentialModel.setUsername(credentialDTO.getUsername());
        credentialModel.setPassword(passwordEncoder.encode(credentialDTO.getPassword()));
        credentialModel.setGrantedAuthority(credentialDTO.getGrantedAuthority());
        credentialModel.setId(credentialDTO.getId());
        return credentialModel;
    }

    /**
     * Method to extract CredentialModel from SignupDTO
     * @param signupDTO SignupDTO to extract
     * @return CredentialModel
     * */
    public CredentialModel extractCredentialModel(SignupDTO signupDTO) {
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setUser(null);
        credentialModel.setUsername(signupDTO.getEmail());
        credentialModel.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        credentialModel.setGrantedAuthority(signupDTO.getRole());

        return credentialModel;
    }

    /**
     * Method to extract AddressModel from SignupDTO
     * @param signupDTO SignupDTO to extract address
     * @return AddressModel
     * */
    public AddressModel extractAddressModel(SignupDTO signupDTO) {
        AddressModel addressModel = new AddressModel();
        addressModel.setId(null);
        addressModel.setApartement(signupDTO.getApartment());
        addressModel.setStreet(signupDTO.getStreet());
        addressModel.setZip(signupDTO.getZip());
        addressModel.setCity(signupDTO.getCity());
        addressModel.setState(signupDTO.getState());

        return addressModel;
    }

    /**
     * Method to convert SignupDTO to UserModel
     * @param signupDTO SignupDTO object to convert
     * @return UserModel
     **/
    public UserModel extractUserModelToSignUp(SignupDTO signupDTO) {
        // create userModel
        UserModel userModel = new UserModel();
        //userModel.setId(signupDTO.getId());
        userModel.setEmail(signupDTO.getEmail());
        userModel.setFname(signupDTO.getFname());
        userModel.setLname(signupDTO.getLname());
        userModel.setImageURL(signupDTO.getImageURL());
        userModel.setEmail(signupDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        userModel.setPhone(signupDTO.getPhone());
        userModel.setDob(signupDTO.getDob());
        userModel.setCreated(LocalDate.now());
        userModel.setUpdated(LocalDate.now());

        // create AddressModel
        AddressModel addressModel = extractAddressModel(signupDTO);


        userModel.setAddress(addressModel);
        userModel.setRole(signupDTO.getRole());
        return userModel;
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
     * Method to convert UserModel to UserDTO
     * @param userModel the UserModel to convert
     * @return UserDTO
     * */
    public UserDTO converterUserModelToUserDTO(UserModel userModel) {
        AddressDTO addressModel = convertAddressModelToAddressDTO(userModel.getAddress());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setFname(userModel.getFname());
        userDTO.setLname(userModel.getLname());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setImageURL(userModel.getImageURL());
        userDTO.setPhone(userModel.getPhone());
        userDTO.setDob(userModel.getDob());
        userDTO.setAddress(addressModel);
        userDTO.setRole(userModel.getRole());
        userDTO.setCreated(userModel.getCreated());
        userDTO.setUpdated(userModel.getUpdated());
        userDTO.setDeleted(userModel.isDeleted());

        return userDTO;
    }

    /**
     * Method to convert AddressModel to AddressDTO
     * @param address the AddressModel to convert
     * @return AddressDTO
     * */
    public AddressDTO convertAddressModelToAddressDTO(AddressModel address) {
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
     *  Method to convert UserModel to UserDetailsDTO
     *  @param userModel the UserModel to convert
     *  @return UserDetailsDTO
     * */
    public UserDetailsDTO converterUserModelToUserDetailsDTO(UserModel userModel) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        CredentialModel credential = new CredentialModel();
        credential.setId(userModel.getId());
        credential.setUsername(userModel.getEmail());
        credential.setPassword(userModel.getPassword());
        credential.setGrantedAuthority(userModel.getRole());
        credential.setCreated(userModel.getCreated());
        credential.setUpdated(userModel.getUpdated());
        userDetailsDTO.setCredentials(credential);
        userDetailsDTO.setUserModel(userModel);

        return userDetailsDTO;
    }
}
