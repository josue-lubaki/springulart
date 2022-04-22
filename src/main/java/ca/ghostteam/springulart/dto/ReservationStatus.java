package ca.ghostteam.springulart.dto;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-10
 */
public enum ReservationStatus {
    PENDING("Non Traitée"),
    ACCEPTED("Acceptée"),
    FINISHED("Terminée");

    ReservationStatus(String etat) {
        this.etat = etat;
    }

    private String etat;

    public String getEtat() {
        return etat;
    }
}
