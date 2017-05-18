package by.hospital.command.surveyhistory;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Prescription;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SurveyHistoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 12.05.2017.
 */
public class EditSurveyHistory implements Command {
    private static final String PARAM_SURVEY_HISTORY_ID = "id";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int surveyID = Integer.parseInt(request.getParameter(PARAM_SURVEY_HISTORY_ID));

        SurveyHistory surveyHistory = new SurveyHistory();
        surveyHistory.setPrimaryKey(surveyID);
        surveyHistory = ServiceLocator.getService(SurveyHistoryService.class).getSurveyHistory(surveyHistory);

        List<Diagnose> alldiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).findBySurveyHistory(surveyHistory);
        request.setAttribute("surveyHistory", surveyHistory);
        request.setAttribute("prescriptionList", prescriptionList);
        request.setAttribute("alldiagnose", alldiagnose);
        page = ConfigurationManager.getProperty("PAGE_SURVEY");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
