package by.hospital.command.sicklist;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.SickList;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 11.05.2017.
 */
public class NewSickList implements Command {
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

        int patientID = Integer.parseInt(request.getParameter(PARAM_PATIENT_ID));
        Patient patient = new Patient();
        patient.setPrimaryKey(patientID);
        patient = ServiceLocator.getService(PatientService.class).returnPatientFull(patient);
        Date dateIn = java.sql.Date.valueOf(LocalDate.now());
        SickList sickList = new SickList();
        sickList.setPatient(patient);
        sickList.setDateIN(dateIn);
        List<Diagnose> alldiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        request.setAttribute("alldiagnose",alldiagnose);
        request.setAttribute("sickList", sickList);
        page = ConfigurationManager.getProperty("PAGE_SICKLIST");

        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
