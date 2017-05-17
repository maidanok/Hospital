package by.hospital.command.prescription;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Prescription;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PrescriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 15.05.2017.
 */
public class DeletePrescription implements Command {
    private static Set<Post> roles = new HashSet<>();
    private static final String PARAM_PRESCRIPTION_ID = "id";

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int prescriptionID = Integer.parseInt(request.getParameter(PARAM_PRESCRIPTION_ID));
        Prescription prescription = new Prescription();
        prescription.setPrimaryKey(prescriptionID);
        prescription= ServiceLocator.getService(PrescriptionService.class).getPrescription(prescription);
        ServiceLocator.getService(PrescriptionService.class).deletePrescription(prescription);

        List<Diagnose> alldiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        List<Prescription> prescriptionList =ServiceLocator.getService(PrescriptionService.class).
                findBySurveyHistory(prescription.getSurveyHistory());
        request.setAttribute("surveyHistory",prescription.getSurveyHistory());
        request.setAttribute("prescriptionList",prescriptionList);
        request.setAttribute("alldiagnose",alldiagnose);

        page= ConfigurationManager.getProperty("PAGE_SURVEY");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
