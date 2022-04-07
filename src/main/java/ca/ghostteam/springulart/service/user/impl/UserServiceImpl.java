package ca.ghostteam.springulart.service.user.impl;

import ca.ghostteam.springulart.dto.*;
import ca.ghostteam.springulart.model.AddressModel;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserRepository;
import ca.ghostteam.springulart.service.file.FileService;
import ca.ghostteam.springulart.service.address.AddressService;
import ca.ghostteam.springulart.service.credential.CredentialService;
import ca.ghostteam.springulart.service.mail.MailService;
import ca.ghostteam.springulart.service.user.UserService;
import ca.ghostteam.springulart.tools.UtilsUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final MailService mailService;
    private final FileService fileService;
    private final UtilsUserConverter utilsUserConverter;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AddressService addressService,
            CredentialService credentialService,
            MailService mailService,
            FileService fileService,
            UtilsUserConverter utilsUserConverter) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.credentialService = credentialService;
        this.mailService = mailService;
        this.fileService = fileService;
        this.utilsUserConverter = utilsUserConverter;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserModelByEmail(username)
                .map(utilsUserConverter::converterUserModelToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public Optional<UserDTO> updateUser(Long id, UserDTO userDTO) {
        UserModel oldUserModel = this.userRepository.findById(id).get();
        updatePersonalInfo(oldUserModel, userDTO);
        updateAddressUser(oldUserModel, userDTO);

        return Optional.of(
                utilsUserConverter.converterUserModelToUserDTO(
                        this.userRepository.save(oldUserModel)
                )
        );
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return Optional.of(
                utilsUserConverter.converterUserModelToUserDTO(
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
                .map(utilsUserConverter::converterUserModelToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> saveUser(SignupDTO signupDTO) {
        // created user
        UserModel user = utilsUserConverter.extractUserModelToSignUp(signupDTO);
        CredentialModel credentialModel = utilsUserConverter.extractCredentialModel(signupDTO);
        AddressModel addressModel = utilsUserConverter.extractAddressModel(signupDTO);

        AddressDTO addressSaved = addressService.saveAddressModel(addressModel).get();

        // id id_address into UserModel Object
        user.getAddress().setId(addressModel.getId());

        credentialModel.setUser(user);
        // extract CredentialModel and save it, and set id_user into UserModel Object
        CredentialDTO credentialSaved = credentialService.saveCredential(credentialModel).get();
        CredentialModel credentiaModelSaved = utilsUserConverter.converterCredentialDtoToCredentialModel(credentialSaved);

        user.setCredential(credentiaModelSaved);
        UserModel userModelSaved = userRepository.save(user);

        UserDTO userDTO = utilsUserConverter.converterUserModelToUserDTO(userModelSaved);
        userDTO.getAddress().setId(addressSaved.getId());

        // send email
        mailService.welcomeMessage(userDTO.getEmail(), userDTO.getFullName());

        return Optional.of(userDTO);
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return this.userRepository
                .findById(id)
                .map(utilsUserConverter::converterUserModelToUserDTO);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        // delete imageURL of user to Amazon S3
        String imageURL = this.userRepository.findById(id).get().getImageURL();
        this.userRepository.deleteById(id);
        this.fileService.deleteImage(imageURL);
    }

    /**
     * Method to update the address information of the user
     * @param oldUserModel UserModel to update
     * @param userDTO UserDTO to update
     * */
    private void updateAddressUser(UserModel oldUserModel, UserDTO userDTO) {
        // update address
        if(userDTO.getAddress() != null) {
            // get old address of user
            AddressModel oldAddressModel = oldUserModel.getAddress();
            if (oldAddressModel.getApartement() != null)
                oldAddressModel.setApartement(userDTO.getAddress().getApartement());
            if (oldAddressModel.getCity() != null)
                oldAddressModel.setCity(userDTO.getAddress().getCity());
            if (oldAddressModel.getState() != null)
                oldAddressModel.setState(userDTO.getAddress().getState());
            if (oldAddressModel.getStreet() != null)
                oldAddressModel.setStreet(userDTO.getAddress().getStreet());
            if (oldAddressModel.getZip() != null)
                oldAddressModel.setZip(userDTO.getAddress().getZip());
            // set new address
            oldUserModel.setAddress(oldAddressModel);
        }
    }

    /**
     * Update personal info of user
     * @param oldUserModel old user model to update
     * @param userDTO new user data
     */
    private void updatePersonalInfo(UserModel oldUserModel, UserDTO userDTO) {
        // update information
        if(userDTO.getFname() != null)
            oldUserModel.setFname(userDTO.getFname());
        if(userDTO.getLname() != null)
            oldUserModel.setLname(userDTO.getLname());
        if(userDTO.getPhone() != null)
            oldUserModel.setPhone(userDTO.getPhone());

    }
}
