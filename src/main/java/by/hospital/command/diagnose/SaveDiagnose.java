package by.hospital.command.diagnose;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.Staff;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class SaveDiagnose implements Command {
    private static final String PARAM_DIAGNOSE_ID = "id";
    private static final String PARAM_DIAGNOSE_NAME = "diagnosename";
    private static final String PARAM_DIAGNOSE_THERAPY = "therapy";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int id = Integer.parseInt(request.getParameter(PARAM_DIAGNOSE_ID));
        String diagnoseName = request.getParameter(PARAM_DIAGNOSE_NAME);
        String therapy = request.getParameter(PARAM_DIAGNOSE_THERAPY);
        Diagnose diagnose = new Diagnose();
        diagnose.setPrimaryKey(id);
        diagnose.setDiagnoseName(diagnoseName);
        diagnose.setTherapy(therapy);
        ServiceLocator.getService(DiagnoseService.class).saveDiagnose(diagnose);

        HttpSession session = request.getSession(true);
        List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();

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
