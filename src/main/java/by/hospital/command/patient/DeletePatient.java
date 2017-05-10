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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class DeletePatient implements Command {
    private static final String PARAM_PATIENT_ID = "id";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.NURSE);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int patientID = Integer.valueOf(request.getParameter(PARAM_PATIENT_ID));
        Patient patient = new Patient();
        patient.setPrimaryKey(patientID);
        ServiceLocator.getService(PatientService.class).deletePatient(patient);
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        request.setAttribute("allPatient", allPatient);
        request.setAttribute("isRedirect", true);
        page = ConfigurationManager.getProperty("PAGE_DIRECTORIES");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
