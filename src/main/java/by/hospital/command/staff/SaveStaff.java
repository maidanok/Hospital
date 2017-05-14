package by.hospital.command.staff;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Gender;
import by.hospital.domain.enumeration.Post;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PatientService;
import by.hospital.service.api.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class SaveStaff implements Command {
    private static final String PARAM_STAFF_ID = "id";
    private static final String PARAM_STAFF_LASTNAME = "lastname";
    private static final String PARAM_STAFF_FIRSNAME = "firstname";
    private static final String PARAM_STAFF_MIDDLENAME = "middlename";
    private static final String PARAM_STAFF_BIRTDAY = "birthday";
    private static final String PARAM_STAFF_GENDER = "gender";
    private static final String PARAM_STAFF_ADDRESS = "address";
    private static final String PARAM_STAFF_PASSPORT = "passport";
    private static final String PARAM_STAFF_LOGIN = "login";
    private static final String PARAM_STAFF_PASSWORD = "password";
    private static final String PARAM_STAFF_FIRED = "fired";
    private static final String PARAM_STAFF_POST = "post";

    //TODO ошибка в SQL создание нового сотрудника
    private static Set<Post> roles = new HashSet<>();

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
        String address = request.getParameter(PARAM_STAFF_ADDRESS);
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
        Post post = Post.valueOf(request.getParameter(PARAM_STAFF_POST));
        Staff staff = new Staff();

        if (id != 0) {
            staff.setPrimaryKey(id);
            staff = ServiceLocator.getService(StaffService.class).returnStaffFull(staff);
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
        staff.setPost(post.toString());

        ServiceLocator.getService(StaffService.class).saveStaff(staff);
        HttpSession session = request.getSession(true);

        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();
        List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();

        session.setAttribute("allPatient", allPatient);
        session.setAttribute("allStaff", allStaff);
        session.setAttribute("allDiagnose", allDiagnose);

        request.setAttribute("isRedirect", true);
        page = "directories.html";
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
