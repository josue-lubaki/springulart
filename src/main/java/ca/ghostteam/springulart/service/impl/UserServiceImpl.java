package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserRepository;
import ca.ghostteam.springulart.service.AddressService;
import ca.ghostteam.springulart.service.CredentialService;
import ca.ghostteam.springulart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressService addressService;
    private final CredentialService credentialService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AddressService addressService,
            CredentialService credentialService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.credentialService = credentialService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserModelByEmail(username)
                .map(this::converterUserModelToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        UserModel oldUserModel = this.userRepository.findById(id).get();
        // update information
        oldUserModel.setEmail(userDTO.getEmail());
        oldUserModel.setFname(userDTO.getFname());
        oldUserModel.setLname(userDTO.getLname());
        oldUserModel.setPhone(userDTO.getPhone());
        oldUserModel.setImageURL(userDTO.getImageURL());

        return Optional.of(
                converterUserModelToUserDTO(this.userRepository.save(oldUserModel))
        );
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(this::converterUserModelToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> saveUser(SignupDTO signupDTO) {

        // extract AddressModel and save it
        AddressModel addressModel = extractAddressModel(signupDTO);
        AddressDTO addressSaved =
                addressService
                .saveAddress(addressModel)
                .orElse(null);

        // id id_address into UserModel Object
        assert addressSaved != null;
        signupDTO.getAddress().setId(addressSaved.getId());

        // created user
        UserDTO userDTO = converterUserModelToUserDTO(
                userRepository
                .save(convertSignupDtoToUserModel(signupDTO))
        );

        // extract CredentialModel and save it
        // set id_user into UserModel Object
        CredentialModel credentialModel = extractCredentialModel(signupDTO);
        credentialModel.setId_user(userDTO.getId());
        credentialService.saveCredential(credentialModel).get();

        return Optional.of(userDTO);
    }

    private CredentialModel extractCredentialModel(SignupDTO signupDTO) {
        CredentialModel credentialModel = new CredentialModel();
        credentialModel.setId_user(null);
        credentialModel.setUsername(signupDTO.getEmail());
        credentialModel.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        credentialModel.setGrantedAuthority(signupDTO.getRole());

        return credentialModel;
    }

    private AddressModel extractAddressModel(SignupDTO signupDTO) {
        AddressModel addressModel = new AddressModel();
        addressModel.setId(null);
        addressModel.setApartement(signupDTO.getAddress().getApartement());
        addressModel.setStreet(signupDTO.getAddress().getStreet());
        addressModel.setZip(signupDTO.getAddress().getZip());
        addressModel.setCity(signupDTO.getAddress().getCity());
        addressModel.setState(signupDTO.getAddress().getState());

        return addressModel;
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return this.userRepository
                .findById(id)
                .map(this::converterUserModelToUserDTO);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUserById(Long id) {
            userRepository.deleteById(id);
    }

    /**
     * Method to convert SignupDTO to UserModel
     * @param signupDTO SignupDTO object to convert
     * @return UserModel
     **/
    private UserModel convertSignupDtoToUserModel(SignupDTO signupDTO) {
        // create userModel
        UserModel userModel = new UserModel();
        userModel.setId(signupDTO.getId());
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
        userModel.setId_address(signupDTO.getAddress().getId());
        userModel.setRole(signupDTO.getRole());
        return userModel;
    }

    /**
     * Method to convert UserModel to UserDTO
     * @param userModel the UserModel to convert
     * @return UserDTO
     * */
    private UserDTO converterUserModelToUserDTO(UserModel userModel) {
        // retrieve Address Information
        AddressModel addressModel =
                addressService
                .findAddressModelUserById(userModel.getId_address())
                .orElse(null);

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
     *  Method to convert UserModel to UserDetailsDTO
     *  @param userModel the UserModel to convert
     *  @return UserDetailsDTO
     * */
    private UserDetailsDTO converterUserModelToUserDetailsDTO(UserModel userModel) {
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
