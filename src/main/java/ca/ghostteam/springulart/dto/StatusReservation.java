package ca.ghostteam.springulart.dto;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-04-10
 */
public enum StatusReservation {
    PENDING("Non Traitée"),
    ACCEPTED("Acceptée"),
    FINISHED("Terminée");

    StatusReservation(String etat) {
        this.etat = etat;
    }

    private String etat;

    public String getEtat() {
        return etat;
    }
}
