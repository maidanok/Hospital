package by.hospital.domain.enumeration;

/**
 * Created by Pasha on 12.04.2017.
 */
public enum TypePrescription {
    PROCEDURE("Процедура"), MEDICATION("Медикаменты"), SURGERY("Операция"), DISCHARGE("Выписка");

    private String name;
    private TypePrescription(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
}
