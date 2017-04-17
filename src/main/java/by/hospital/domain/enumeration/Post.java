package by.hospital.domain.enumeration;

/**
 * Created by Pasha on 12.04.2017.
 */
public enum Post {

    ADMINISTRATOR("Администратор", 7), DOCTOR("Врач", 8), NURSE("Медсестра/Медбрат", 6);

    private String name;
    private int id;

    private Post(String name, int i) {
        this.name = name;
        this.id = i;
    }

    public String getName() {
        return name;
    }

    public int getID(){
        return id;
    }
}
