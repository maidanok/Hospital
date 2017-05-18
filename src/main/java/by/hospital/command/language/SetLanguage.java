package by.hospital.command.language;

import by.hospital.command.Command;
import by.hospital.domain.*;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 16.05.2017.
 */
public class SetLanguage implements Command {

    private static final String PARAM_LANGUAGE = "language";
    private static Set<String> lang = new HashSet<>();

    static {
        lang.add("en");
        lang.add("ru");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String language = request.getParameter(PARAM_LANGUAGE);

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }

        if (lang.contains(language)) {
            session.setAttribute("language", language);
        } else {
            session.setAttribute("language", "ru");
        }
        page = ConfigurationManager.getProperty("PAGE_INDEX");
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();
        List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        request.setAttribute("allPatient", allPatient);
        request.setAttribute("allStaff", allStaff);
        request.setAttribute("allDiagnose", allDiagnose);
        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
        request.setAttribute("sickLists", sickLists);
        request.setAttribute("prescriptionList", prescriptionList);
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
