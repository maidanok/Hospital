package by.hospital.DAO.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class LoginAndPassword extends Condition {
    private String log, pass;

    public LoginAndPassword(String log,String pass){
        super();
        this.log=log;
        this.pass=pass;

    }
    @Override
    public String getValue() {
        return " WHERE login='"+log+"' AND password='"+pass+"';";
    }
}
