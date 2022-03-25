package ca.ghostteam.springulart.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@Service("user-service-fake")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("fake-repository") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao
                .selectUserByUsername(username)
                .map(this::converterUserModelToUserDetailsDTO)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userDao
                .findAllUsers()
                .stream()
                .map(this::converterUserModelToUserDTO)
                .collect(Collectors.toList());
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
