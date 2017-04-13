package by.hospital.domain;

import by.hospital.domain.enumeration.Gender;

import java.util.Date;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Staff implements Entity<Integer> {
    private int staffID;
    private Post post;
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthday;
    private Gender sex;
    private String login;
    private String password;
    private boolean fired;

    @Override
    public Integer getPrimaryKey() {
        return staffID;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        staffID = primaryKey;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFired() {
        return fired;
    }

    public void setFired(boolean fired) {
        this.fired = fired;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Staff[").append(staffID).append("]").append("\n");
        stringBuilder.append("First Name ").append(firstName).append("\n");
        stringBuilder.append("Last Name ").append(lastName).append("\n");
        stringBuilder.append("Middle Name ").append(middleName).append("\n");
        stringBuilder.append("Birthday ").append(birthday).append("\n");
        stringBuilder.append("Gender ").append(sex.getName()).append("\n");
        stringBuilder.append("Fired ").append(fired ? "уволен" : "не уволен").append("\n");
        return stringBuilder.toString();
    }
}
