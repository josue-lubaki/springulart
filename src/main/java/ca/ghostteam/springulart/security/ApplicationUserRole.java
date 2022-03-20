package ca.ghostteam.springulart.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static ca.ghostteam.springulart.security.ApplicationUserPermission.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 */
@RequiredArgsConstructor
@Getter
public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(
            RESERVATION_READ,
            RESERVATION_WRITE,
            USER_READ,
            USER_WRITE,
            HAIRCUT_READ
    )),
    ADMIN(Sets.newHashSet(
            RESERVATION_READ,
            RESERVATION_WRITE,
            USER_READ,
            USER_WRITE,
            HAIRCUT_READ,
            HAIRCUT_WRITE)
    ),
    BARBER(Sets.newHashSet(
            RESERVATION_READ,
            RESERVATION_WRITE,
            USER_READ,
            HAIRCUT_READ
    ));

    private final Set<ApplicationUserPermission> permissions;

    /**
     * Method that collects all the permissions granted to the role of the selected user
     * @return Set<SimpleGrantedAuthority>
     * */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
