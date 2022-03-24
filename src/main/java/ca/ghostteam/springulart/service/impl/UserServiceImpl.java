package ca.ghostteam.springulart.service.impl;

import ca.ghostteam.springulart.dto.UserDTO;
import ca.ghostteam.springulart.dto.UserDetailsDTO;
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
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userDao
                .findAllUsers()
                .stream()
                .map(this::converterUserDetailsDtoToUserDto)
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
}
