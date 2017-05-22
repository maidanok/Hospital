package by.hospital.domain.enumeration;

/**
 * Created by Pasha on 12.04.2017.
 */
public enum PrescriptionType {
    PROCEDURE("Процедура", 1), MEDICATION("Медикаменты", 2), SURGERY("Операция", 3), DISCHARGE("Выписка", 4);

    private String name;
    private int id;

    private PrescriptionType(String name, int i) {
        this.name = name;
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
