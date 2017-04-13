package by.hospital.domain.enumeration;

/**
 * Created by Pasha on 12.04.2017.
 */
public enum Gender {
    MALE("мужчина"), FEMALE("женщина");
    private String name;

    private Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
