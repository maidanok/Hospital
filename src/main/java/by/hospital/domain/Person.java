package by.hospital.domain;

import by.hospital.domain.enumeration.Gender;

import java.util.Date;

/**
 * Created by Admin on 16.04.2017.
 */
public abstract class Person implements Entity<Integer>{
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Gender sex;
    private String address;
    private String passportNumber;

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

    public String getFullName(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getFirstName()).append(" ").append(getLastName()).append(" ").append(getMiddleName());
        return stringBuilder.toString();
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
}
