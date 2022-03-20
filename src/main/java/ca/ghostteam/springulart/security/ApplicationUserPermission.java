package ca.ghostteam.springulart.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-19
 *
 * Class containing the different permissions offered by the application.
 * Example : RESERVATION_READ (User with this permission can read reservations)
 */
@RequiredArgsConstructor
@Getter
public enum ApplicationUserPermission {
    USER_READ("client:read"),
    USER_WRITE("client:write"),
    HAIRCUT_READ("haircut:read"),
    HAIRCUT_WRITE("haircut:write"),
    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write");

    private final String permission;
}
