package by.hospital.command.prescription;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Prescription;
import by.hospital.domain.enumeration.Post;
import by.hospital.domain.enumeration.PrescriptionType;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PrescriptionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 15.05.2017.
 */
public class SavePrescription implements Command {
    private static final String PARAM_PRESCRIPTION_ID = "id";
    private static final String PARAM_PRESCRIPTION_SURVEY_ID = "shid";
    private static final String PARAM_PRESCRIPTION_TYPE = "prescriptiontype";
    private static final String PARAM_PRESCRIPTION_QUANTITY = "quantity";
    private static final String PARAM_PRESCRIPTION_DESCRIPTION = "description";

    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int prescriptionID = Integer.parseInt(request.getParameter(PARAM_PRESCRIPTION_ID));
        int surveyID = Integer.parseInt(request.getParameter(PARAM_PRESCRIPTION_SURVEY_ID));
        String type = request.getParameter(PARAM_PRESCRIPTION_TYPE);
        String description = request.getParameter(PARAM_PRESCRIPTION_DESCRIPTION);
        int quantity = Integer.parseInt(request.getParameter(PARAM_PRESCRIPTION_QUANTITY));


        Prescription prescription = new Prescription();
        prescription.setPrimaryKey(prescriptionID);
        prescription.getSurveyHistory().setPrimaryKey(surveyID);
        prescription.setPrescriptionType(type);
        prescription.setDescription(description);
        prescription.setQuantity(quantity);


        prescription=ServiceLocator.getService(PrescriptionService.class).savePrescription(prescription);

        List<Diagnose> alldiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        List<Prescription> prescriptionList =ServiceLocator.getService(PrescriptionService.class).
                findBySurveyHistory(prescription.getSurveyHistory());
        HttpSession session = request.getSession(false);
        session.setAttribute("surveyHistory",prescription.getSurveyHistory());
        session.setAttribute("alldiagnose",alldiagnose);
        session.setAttribute("prescriptionList",prescriptionList);

        request.setAttribute("isRedirect", true);
        page= "survey.html";
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
