package by.hospital.command.surveyhistory;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
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
 * Created by Admin on 14.05.2017.
 */
public class DeleteSurveyHistory implements Command {
    private static final String PARAM_SURVEY_HISTORY_ID = "id";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int surveyID = Integer.parseInt(request.getParameter(PARAM_SURVEY_HISTORY_ID));
        SurveyHistory surveyHistory = new SurveyHistory();
        surveyHistory.setPrimaryKey(surveyID);
        surveyHistory = ServiceLocator.getService(SurveyHistoryService.class).getSurveyHistory(surveyHistory);
        ServiceLocator.getService(SurveyHistoryService.class).deleteSurveyHistory(surveyHistory);
        List<SurveyHistory> surveyHistoryList = ServiceLocator.getService(SurveyHistoryService.class).getAllbySickList(surveyHistory.getSickList());
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).
                findBySickList(surveyHistory.getSickList());

        request.setAttribute("sickList", surveyHistory.getSickList());
        request.setAttribute("surveyHistoryList", surveyHistoryList);
        request.setAttribute("prescriptionList", prescriptionList);
        page = ConfigurationManager.getProperty("PAGE_SICKLIST");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
