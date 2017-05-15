package by.hospital.command.prescription;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import by.hospital.domain.enumeration.PrescriptionType;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SurveyHistoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 14.05.2017.
 */
public class EditPrescription implements Command {
    Logger logger = Logger.getLogger(EditPrescription.class);
    private static final String PARAM_PRESCRIPTION_ID = "id";
    private static final String PARAM_PRESCRIPTION_SURVEY_ID = "shid";


    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int prescriptionID = Integer.valueOf(request.getParameter(PARAM_PRESCRIPTION_ID));
        Prescription prescription = new Prescription();
        if (prescriptionID == 0) {
            int surveyID = Integer.valueOf(request.getParameter(PARAM_PRESCRIPTION_SURVEY_ID));
            SurveyHistory surveyHistory = new SurveyHistory();
            surveyHistory.setPrimaryKey(surveyID);
            surveyHistory = ServiceLocator.getService(SurveyHistoryService.class).returnSurveyHistoru(surveyHistory);
            prescription.setSurveyHistory(surveyHistory);
        }else {
            prescription.setPrimaryKey(prescriptionID);
            prescription=ServiceLocator.getService(PrescriptionService.class).returnPrescription(prescription);
        }

        request.setAttribute("prescription",prescription);
        request.setAttribute("types", PrescriptionType.values());
        page= ConfigurationManager.getProperty("PAGE_PRESCRIPTION");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
