package by.hospital.listener;

import by.hospital.domain.Staff;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Admin on 21.05.2017.
 */
public class HospitalSessionListener implements HttpSessionAttributeListener {
    static Logger logger = Logger.getLogger(HospitalSessionListener.class);
    private String userAttr = "user";

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        String currAttrName = se.getName();
        if (currAttrName.equals(userAttr)) {
            Staff user = (Staff) se.getValue();
            logger.info("The user " + user.getFullName() + " " + user.getLogin() + " is logged on");
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        String currAttrName = se.getName();
        if (currAttrName.equals(userAttr)) {
            Staff user = (Staff) se.getValue();
            logger.info("The user " + user.getFullName() + " " + user.getLogin() + " is logged off");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        String currAttrName = se.getName();
        if (currAttrName.equals(userAttr)) {
            Staff user = (Staff) se.getValue();
            logger.info("The user " + user.getFullName() + " " + user.getLogin() + "is changed his data");
        }
    }

}