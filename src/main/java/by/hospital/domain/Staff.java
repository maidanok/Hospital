package by.hospital.domain;

import by.hospital.domain.enumeration.Post;

/**
 * Created by Pasha on 12.04.2017.
 */
public class Staff extends Person implements Entity<Integer> {
    private int staffID;
    private Post post;
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

    public void setPost(String post) {
        this.post =Post.valueOf(post);
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
        stringBuilder.append("Post ").append(getPost().toString()).append("\n");
        stringBuilder.append("First Name ").append(getFirstName()).append("\n");
        stringBuilder.append("Last Name ").append(getLastName()).append("\n");
        stringBuilder.append("Middle Name ").append(getMiddleName()).append("\n");
        stringBuilder.append("Birthday ").append(getBirthday()).append("\n");
        stringBuilder.append("Gender ").append(getSex().getName()).append("\n");
        stringBuilder.append("Address ").append(getAddress()).append("\n");
        stringBuilder.append("Passport ").append(getPassportNumber()).append("\n");
        stringBuilder.append("Fired ").append(isFired() ? "уволен" : "не уволен").append("\n");
        stringBuilder.append("Login ").append(getLogin()).append("\n");
        stringBuilder.append("Passw ").append(getPassword()).append("\n");
        return stringBuilder.toString();
    }
}
