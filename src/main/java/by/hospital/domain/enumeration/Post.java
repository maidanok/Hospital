package by.hospital.domain.enumeration;

/**
 * Created by Pasha on 12.04.2017.
 */
public enum Post {

    ADMINISTRATOR("Администратор"), DOCTOR("Врач"), NURSE("Медсестра/Медбрат");

    private String name;

    private Post(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}