package by.hospital.command.login;

import by.hospital.command.Command;
import by.hospital.domain.*;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Admin on 07.05.2017.
 */
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        //извлечение логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        // извлечение из БД пользователя с таким логином и паролем
        Staff findStaff=new Staff();
        findStaff.setLogin(login);
        findStaff.setPassword(password);
        Staff staff = ServiceLocator.getService(StaffService.class).findByLogPass(findStaff);
            if (staff!=null){
            //начало сессии
            HttpSession session = request.getSession(true);
            session.setAttribute("user",staff);
            // в зависимости от роли определение странички пользователя
            if ((staff.getPost()== Post.DOCTOR)||(staff.getPost()==Post.NURSE)){
                List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
                List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
                request.setAttribute("sickLists",sickLists);
                request.setAttribute("prescriptionList",prescriptionList);
                page= ConfigurationManager.getProperty("PAGE_HOSPITAL");
            } else {
                if (staff.getPost()==Post.ADMINISTRATOR){
                    List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
                    List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();
                    List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
                    request.setAttribute("allPatient",allPatient);
                    request.setAttribute("allStaff",allStaff);
                    request.setAttribute("allDiagnose",allDiagnose);
                    page=ConfigurationManager.getProperty("PAGE_DIRECTORIES");
                }
            }
        } if (staff==null){
            page=ConfigurationManager.getProperty("PAGE_LOGIN");
        }
        return page;
    }
}
