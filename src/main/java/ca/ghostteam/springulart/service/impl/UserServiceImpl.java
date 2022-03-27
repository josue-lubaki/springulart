package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.SignupDTO;
import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
import ca.ghostteam.springulart.model.CredentialModel;
import ca.ghostteam.springulart.model.UserModel;
import ca.ghostteam.springulart.repository.UserDao;
import ca.ghostteam.springulart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Service("user-service-fake")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            @Qualifier("fake-repository-users") UserDao userDao,
            PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao
                .selectUserByUsername(username)
                .map(this::converterUserModelToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public Optional<UserDTO> updateUser(Integer id, UserDTO userDTO) {
        return this.userDao
                .update(id, convertUserDtoToUserModel(userDTO))
                .map(this::converterUserModelToUserDTO);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userDao
                .findAllUsers()
                .stream()
                .map(this::converterUserModelToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> saveUser(SignupDTO signupDTO) throws Exception {
        return userDao
                .save(convertSignupDtoToUserModel(signupDTO))
                .map(this::converterUserModelToUserDTO);
    }

    @Override
    public Optional<UserDTO> findUserById(Integer id) {
        return this.userDao
                .findUserById(id)
                .map(this::converterUserModelToUserDTO);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public void deleteUserById(Integer id) {
            userDao.deleteById(id);
    }

    /**
     * Method to convert SignupDTO to UserModel
     * @param signupDTO SignupDTO object to convert
     * @return UserModel
     **/
    private UserModel convertSignupDtoToUserModel(SignupDTO signupDTO) {
        // create userModel
        UserModel userModel = new UserModel();
        userModel.setId(findAllUsers().size() + 1);
        userModel.setEmail(signupDTO.getEmail());
        userModel.setFname(signupDTO.getFname());
        userModel.setLname(signupDTO.getLname());
        userModel.setImageURL(signupDTO.getImageURL());
        userModel.setEmail(signupDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        userModel.setPhone(signupDTO.getPhone());
        userModel.setDob(LocalDate.parse(signupDTO.getDob()));
        userModel.setCreated(LocalDate.now());
        userModel.setUpdated(LocalDate.now());
        userModel.setAddress(signupDTO.getAddress());
        userModel.setRole(signupDTO.getRole());
        return userModel;
    }

    /**
     * Method to convert UserDTO to UserModel
     * @param userDTO userDTO object to convert
     * @return UserModel
     **/
    private UserModel convertUserDtoToUserModel(UserDTO userDTO) {
        // create userModel
        UserModel userModel = new UserModel();
        userModel.setId(findAllUsers().size() + 1);
        userModel.setEmail(userDTO.getEmail());
        userModel.setFname(userDTO.getFname());
        userModel.setLname(userDTO.getLname());
        userModel.setImageURL(userDTO.getImageURL());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPhone(userDTO.getPhone());
        userModel.setDob(userDTO.getDob());
        userModel.setCreated(LocalDate.now());
        userModel.setUpdated(LocalDate.now());
        userModel.setAddress(userDTO.getAddress());
        userModel.setRole(userDTO.getRole());
        return userModel;
    }

    /**
     * Method to convert UserDetailsDTO to UserDTO
     * @param userDetailsDTO the UserDetailsDTO to convert
     * @return UserDTO
     * */
    private UserDTO converterUserDetailsDtoToUserDto(UserDetailsDTO userDetailsDTO){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userDetailsDTO.getCredentials().getId());
        userDTO.setFname(userDetailsDTO.getUserModel().getFname());
        userDTO.setLname(userDetailsDTO.getUserModel().getLname());
        userDTO.setEmail(userDetailsDTO.getUserModel().getEmail());
        userDTO.setImageURL(userDetailsDTO.getUserModel().getImageURL());
        userDTO.setPhone(userDetailsDTO.getUserModel().getPhone());
        userDTO.setDob(userDetailsDTO.getUserModel().getDob());
        userDTO.setAddress(userDetailsDTO.getUserModel().getAddress());
        userDTO.setRole(userDetailsDTO.getCredentials().getGrantedAuthority());
        userDTO.setCreated(userDetailsDTO.getCredentials().getCreated());
        userDTO.setUpdated(userDetailsDTO.getCredentials().getUpdated());
        userDTO.setDeleted(userDetailsDTO.getUserModel().isDeleted());

        return userDTO;
    }

    /**
     * Method to convert UserModel to UserDTO
     * @param userModel the UserModel to convert
     * @return UserDTO
     * */
    private UserDTO converterUserModelToUserDTO(UserModel userModel) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setFname(userModel.getFname());
        userDTO.setLname(userModel.getLname());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setImageURL(userModel.getImageURL());
        userDTO.setPhone(userModel.getPhone());
        userDTO.setDob(userModel.getDob());
        userDTO.setAddress(userModel.getAddress());
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
