package by.hospital.domain;

/**
 * Created by Admin on 08.04.2017.
 */
public interface Entity <PrimaryKey extends Integer> {

    PrimaryKey getPrimaryKey();

    void setPrimaryKey(int primaryKey);
}
