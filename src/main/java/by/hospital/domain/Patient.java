package by.hospital.domain;

/**
 * Created by Pasha on 10.04.2017.
 */
public class Patient extends Person implements Entity<Integer> {
    private int patientID = 0;


    @Override
    public Integer getPrimaryKey() {
        return patientID;
    }

    public void setPrimaryKey(int primaryKey) {
        patientID = primaryKey;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient[").append(patientID).append("]").append("\n");
        stringBuilder.append("First Name ").append(getFirstName()).append("\n");
        stringBuilder.append("Last Name ").append(getLastName()).append("\n");
        stringBuilder.append("Middle Name ").append(getMiddleName()).append("\n");
        stringBuilder.append("Birthday ").append(getBirthday()).append("\n");
        stringBuilder.append("Gender ").append(getSex().getName()).append("\n");
        stringBuilder.append("Address ").append(getAddress()).append("\n");
        stringBuilder.append("Passport ").append(getPassportNumber()).append("\n");
        return stringBuilder.toString();
    }
}
