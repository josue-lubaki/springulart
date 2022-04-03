package ca.ghostteam.springulart.service.user;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserRepository;
import ca.ghostteam.springulart.service.address.AddressService;
import ca.ghostteam.springulart.service.credential.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    public Optional<UserDTO> findUserByEmail(String email) {
        return Optional.of(
                converterUserModelToUserDTO(
                        this.userRepository.findUserModelByEmail(email).get()
                )
        );
    }

    @Async
    @Override
    public void updatePassword(String email, String password) {
        this.userRepository.updatePassword(email, passwordEncoder.encode(password));
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
        // created user
        UserModel user = extractUserModelToSignUp(signupDTO);
        CredentialModel credentialModel = extractCredentialModel(signupDTO);
        AddressModel addressModel = extractAddressModel(signupDTO);

        AddressDTO addressSaved = addressService.saveAddressModel(addressModel).get();

        // id id_address into UserModel Object
        user.getAddress().setId(addressModel.getId());

        credentialModel.setUser(user);
        // extract CredentialModel and save it, and set id_user into UserModel Object
        CredentialDTO credentialSaved = credentialService.saveCredential(credentialModel).get();
        CredentialModel credentiaModelSaved = converterCredentialDtoToCredentialModel(credentialSaved);

        user.setCredential(credentiaModelSaved);
        UserModel userModelSaved = userRepository.save(user);

        UserDTO userDTO = converterUserModelToUserDTO(userModelSaved);
        userDTO.getAddress().setId(addressSaved.getId());

        return Optional.of(userDTO);
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
     * Method to convert CredentialModel from CredentialDTO
     * @param credentialDTO credentialDTO to extract
     * @return CredentialModel
     */
    private CredentialModel converterCredentialDtoToCredentialModel(CredentialDTO credentialDTO) {
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
    private CredentialModel extractCredentialModel(SignupDTO signupDTO) {
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
    private AddressModel extractAddressModel(SignupDTO signupDTO) {
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
    private UserModel extractUserModelToSignUp(SignupDTO signupDTO) {
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
     * Method to convert UserModel to UserDTO
     * @param userModel the UserModel to convert
     * @return UserDTO
     * */
    private UserDTO converterUserModelToUserDTO(UserModel userModel) {
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
    private AddressDTO convertAddressModelToAddressDTO(AddressModel address) {
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
