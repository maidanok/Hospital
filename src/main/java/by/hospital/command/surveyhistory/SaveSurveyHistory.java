package by.hospital.command.surveyhistory;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
    private static final String PARAM_DIAGNOSE_ID="diagnose";


    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int surveyID = Integer.valueOf(request.getParameter(PARAM_SURVEY_HISTORY_ID));
        int sickListID = Integer.valueOf(request.getParameter(PARAM_SICK_LIST_ID));
        int staffID = Integer.valueOf(request.getParameter(PARAN_STAFF_ID));
        int diagnoseID=Integer.valueOf(request.getParameter(PARAM_DIAGNOSE_ID));
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
        surveyHistory.getDiagnose().setPrimaryKey(diagnoseID);
        surveyHistory.setSurveyDate(surveydate);
        surveyHistory.setDescription(description);
        surveyHistory=ServiceLocator.getService(SurveyHistoryService.class).saveSurveyHistory(surveyHistory);
        if (isDischarge){
            Prescription prescription = new Prescription();
            prescription.setSurveyHistory(surveyHistory);
            prescription.setQuantity(1);
            prescription.setPrescriptionType(PrescriptionType.DISCHARGE.toString());
            ServiceLocator.getService(PrescriptionService.class).createNewPrescription(prescription);
        }

        List<SurveyHistory> surveyHistoryList = ServiceLocator.getService(SurveyHistoryService.class).
                getAllbySickList(surveyHistory.getSickList());
        List <Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).
                findBySickList(surveyHistory.getSickList());



        request.setAttribute("id",surveyHistory.getPrimaryKey());
        request.setAttribute("surveyHistoryList",surveyHistoryList);
        request.setAttribute("sickList",surveyHistory.getSickList());
        request.setAttribute("prescriptionList",prescriptionList);


        page= ConfigurationManager.getProperty("PAGE_SICKLIST");


        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
