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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
    private final UtilsUserConverter utils;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AddressService addressService,
            CredentialService credentialService,
            MailService mailService,
            FileService fileService,
            UtilsUserConverter utils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressService = addressService;
        this.credentialService = credentialService;
        this.mailService = mailService;
        this.fileService = fileService;
        this.utils = utils;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserModelByEmail(username)
                .map(utils::converterUserModelToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public Optional<UserDTO> updateUser(Long id, UserUpdateDTO userUpdatedDTO) {
        UserModel oldUserModel = this.userRepository.findById(id).get();
        try {
            oldUserModel.setImageURL(updateImageUser(oldUserModel, userUpdatedDTO).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        updatePersonalInfo(oldUserModel, userUpdatedDTO);
        updateAddressUser(oldUserModel, userUpdatedDTO);

        return Optional.of(
                utils.converterUserModelToUserDTO(
                        this.userRepository.save(oldUserModel)
                )
        );
    }

    /**
     * Method to update the image of the user
     * @param oldUserModel UserModel to update the image
     * @param userUpdateDTO UserDTO to get the image
     * @return CompletableFuture<String>
     * */
    @Async
    public CompletableFuture<String> updateImageUser(UserModel oldUserModel, UserUpdateDTO userUpdateDTO) {
        if (Objects.nonNull(userUpdateDTO.getImageURL())) {
            // Delete the old image
            this.fileService.deleteImage(oldUserModel.getImageURL(), "users");

            // save the new image
            String imageURL = this.fileService.uploadImage(userUpdateDTO.getImageURL(), "users");

            // Update the imageURL
            return CompletableFuture.completedFuture(imageURL);
        }

        return CompletableFuture.completedFuture(oldUserModel.getImageURL());
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        return Optional.of(
                utils.converterUserModelToUserDTO(
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
                .map(utils::converterUserModelToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> saveUser(SignupDTO signupDTO) {
        // created user
        UserModel user = utils.extractUserModelToSignUp(signupDTO);
        CredentialModel credentialModel = utils.extractCredentialModel(signupDTO);
        AddressModel addressModel = utils.extractAddressModel(signupDTO);

        AddressDTO addressSaved = addressService.saveAddressModel(addressModel).get();

        // id id_address into UserModel Object
        user.getAddress().setId(addressModel.getId());

        credentialModel.setUser(user);
        // extract CredentialModel and save it, and set id_user into UserModel Object
        CredentialDTO credentialSaved = credentialService.saveCredential(credentialModel).get();
        CredentialModel credentiaModelSaved = utils.converterCredentialDtoToCredentialModel(credentialSaved);

        user.setCredential(credentiaModelSaved);
        UserModel userModelSaved = userRepository.save(user);

        UserDTO userDTO = utils.converterUserModelToUserDTO(userModelSaved);
        userDTO.getAddress().setId(addressSaved.getId());

        // send email
        mailService.welcomeMessage(userDTO.getEmail(), userDTO.getFullName());

        return Optional.of(userDTO);
    }

    @Override
    public Optional<UserDTO> findUserById(Long id) {
        return this.userRepository
                .findById(id)
                .map(utils::converterUserModelToUserDTO);
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
        this.fileService.deleteImage(imageURL, "users");
    }

    /**
     * Method to update the address information of the user
     * @param oldUserModel UserModel to update
     * @param userUpdateDTO UserUpdateDTO to update
     * */
    private void updateAddressUser(UserModel oldUserModel, UserUpdateDTO userUpdateDTO) {
        // update address
//        if(userDTO.getAddress() != null) {
            // get old address of user
            AddressModel oldAddressModel = oldUserModel.getAddress();
            if (oldAddressModel.getApartement() != null)
                oldAddressModel.setApartement(userUpdateDTO.getApartement());
            if (oldAddressModel.getCity() != null)
                oldAddressModel.setCity(userUpdateDTO.getCity());
            if (oldAddressModel.getState() != null)
                oldAddressModel.setState(userUpdateDTO.getState());
            if (oldAddressModel.getStreet() != null)
                oldAddressModel.setStreet(userUpdateDTO.getStreet());
            if (oldAddressModel.getZip() != null)
                oldAddressModel.setZip(userUpdateDTO.getZip());
            // set new address
            oldUserModel.setAddress(oldAddressModel);
        // }
    }

    /**
     * Update personal info of user
     * @param oldUserModel old user model to update
     * @param userDTO new user data
     */
    private void updatePersonalInfo(UserModel oldUserModel, UserUpdateDTO userDTO) {
        // update information
        if(userDTO.getFname() != null)
            oldUserModel.setFname(userDTO.getFname());
        if(userDTO.getLname() != null)
            oldUserModel.setLname(userDTO.getLname());
        if(userDTO.getPhone() != null)
            oldUserModel.setPhone(userDTO.getPhone());

    }
}
