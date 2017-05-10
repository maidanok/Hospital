package by.hospital.command.staff;

import by.hospital.command.Command;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Gender;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pasha on 10.05.2017.
 */
public class SaveStaff implements Command {
    private static final String PARAM_STAFF_ID = "id";
    private static final String PARAM_STAFF_LASTNAME="lastname";
    private static final String PARAM_STAFF_FIRSNAME="firstname";
    private static final String PARAM_STAFF_MIDDLENAME="middlename";
    private static final String PARAM_STAFF_BIRTDAY="birthday";
    private static final String PARAM_STAFF_GENDER="gender";
    private static final String PARAM_STAFF_ADDRESS="address";
    private static final String PARAM_STAFF_PASSPORT="passport";
    private static final String PARAM_STAFF_LOGIN="login";
    private static final String PARAM_STAFF_PASSWORD = "password";
    private static final String PARAM_STAFF_FIRED = "fired";

    //TODO ошибка в SQL создание нового сотрудника
    private static Set<Post> roles =new HashSet<>();
    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int id = Integer.parseInt(request.getParameter(PARAM_STAFF_ID));
        String lastName = request.getParameter(PARAM_STAFF_LASTNAME);
        String firstName = request.getParameter(PARAM_STAFF_FIRSNAME);
        String middleName = request.getParameter(PARAM_STAFF_MIDDLENAME);
        String address= request.getParameter(PARAM_STAFF_ADDRESS);
        String passport = request.getParameter(PARAM_STAFF_PASSPORT);
        Gender gender = Gender.valueOf(request.getParameter(PARAM_STAFF_GENDER));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String getDate = request.getParameter(PARAM_STAFF_BIRTDAY);
        Date birthday = null;
        try {
            birthday = formatter.parse(getDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String login = request.getParameter(PARAM_STAFF_LOGIN);
        String password = request.getParameter(PARAM_STAFF_PASSWORD);
        Boolean fierd = Boolean.getBoolean(request.getParameter(PARAM_STAFF_FIRED));
        Staff staff = new Staff();

        if (id!=0){
            staff.setPrimaryKey(id);
            staff= ServiceLocator.getService(StaffService.class).returnStaffFull(staff);
        }
        staff.setAddress(address);
        staff.setSex(gender.toString());
        staff.setLastName(lastName);
        staff.setMiddleName(middleName);
        staff.setFirstName(firstName);
        staff.setPassword(password);
        staff.setLogin(login);
        staff.setPassportNumber(passport);
        staff.setBirthday(birthday);
        staff.setFired(fierd);
        staff.setPost(Post.ADMINISTRATOR.toString());

        ServiceLocator.getService(StaffService.class).saveStaff(staff);
        List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();
        request.setAttribute("allStaff",allStaff);
        request.setAttribute("isRedirect", true);

        page= ConfigurationManager.getProperty("PAGE_DIRECTORIES");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
