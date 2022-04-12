package ca.ghostteam.springulart.dto;

import lombok.Data;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-29
 */
@Data
public class ReservationTimeDTO {
    private long id;
    private Integer hours;
    private Integer minutes;

    @Override
    public String toString() {
        return hours + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

}
