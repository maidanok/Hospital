package by.hospital.command.patient;

import by.hospital.command.Command;
import by.hospital.domain.Patient;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 12.05.2017.
 */
public class FindPatientByLastName implements Command {
    private static final String PARAM_PATIENT_FIRSNAME="firstname";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String firstName=request.getParameter(PARAM_PATIENT_FIRSNAME);
        Patient patient = new Patient();
        patient.setLastName(firstName);
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).FindLastName(patient);
        request.setAttribute("allPatient",allPatient);
        page= ConfigurationManager.getProperty("PAGE_DIRECTORIES");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
