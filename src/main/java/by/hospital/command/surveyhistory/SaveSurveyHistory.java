package by.hospital.command.surveyhistory;

import by.hospital.command.Command;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pasha on 12.05.2017.
 */
public class SaveSurveyHistory implements Command {
    Logger logger = Logger.getLogger(SaveSurveyHistory.class);
    private static final String PARAM_SURVEY_HISTORY_ID = "id";
    private static final String PARAM_SURVEY_HISTORY_DATE = "surveydate";
    private static final String PARAM_SURVEY_HISTORY_DESCRIPTION = "description";
    private static final String PARAM_DISCHARGE = "isdischarge";
    private static final String PARAM_SICK_LIST_ID = "sickListid";
    private static final String PARAN_STAFF_ID = "staffid";


    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int surveyID = Integer.parseInt(request.getParameter(PARAM_SURVEY_HISTORY_ID));
        int sickListID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_ID));
        int staffID = Integer.parseInt(request.getParameter(PARAN_STAFF_ID));
        String date = request.getParameter(PARAM_SURVEY_HISTORY_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date surveydate = null;
        try {
            surveydate=formatter.parse(date);
        } catch (ParseException e){
            logger.error("Error date "+e.getLocalizedMessage());
        }
        String description = request.getParameter(PARAM_SURVEY_HISTORY_DESCRIPTION);
        Boolean isDischarge = Boolean.getBoolean(request.getParameter(PARAM_DISCHARGE));
        SurveyHistory surveyHistory= new SurveyHistory();
        surveyHistory.setPrimaryKey(surveyID);
        surveyHistory.getSickList().setPrimaryKey(sickListID);
        surveyHistory.getStaff().setPrimaryKey(staffID);
        surveyHistory.setSurveyDate(surveydate);
        surveyHistory.setDescription(description);


        return null;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
