package by.hospital.domain;

import by.hospital.domain.enumeration.Gender;

import java.util.Date;

/**
 * Created by Pasha on 10.04.2017.
 */
public class Patient implements Entity<Integer> {
    private int patientID;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Gender sex;
    private String address;
    private String passportNumber;


    @Override
    public Integer getPrimaryKey() {
        return patientID;
    }

    public void setPrimaryKey(int primaryKey){
        patientID=primaryKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = Gender.valueOf(sex);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient[").append(patientID).append("]").append("\n");
        stringBuilder.append("First Name ").append(firstName).append("\n");
        stringBuilder.append("Last Name ").append(lastName).append("\n");
        stringBuilder.append("Middle Name ").append(middleName).append("\n");
        stringBuilder.append("Birthday ").append(birthday).append("\n");
        stringBuilder.append("Gender ").append(sex.getName()).append("\n");
        stringBuilder.append("Address ").append(address).append("\n");
        return stringBuilder.toString();
    }
}
