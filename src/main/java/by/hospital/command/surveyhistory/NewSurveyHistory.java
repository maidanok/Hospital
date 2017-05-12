package by.hospital.command.surveyhistory;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import by.hospital.service.api.SurveyHistoryService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 12.05.2017.
 */
public class NewSurveyHistory implements Command {
    Logger logger = Logger.getLogger(NewSurveyHistory.class);
    private static final String PARAM_SICK_LIST_ID = "sickListid";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.NURSE);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int sickListID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_ID));
        HttpSession session = request.getSession(true);



        Staff staff = (Staff) session.getAttribute("user");

        if (staff==null){
            page=ConfigurationManager.getProperty("PAGE_LOGIN");
            return page;
        }
        SurveyHistory surveyHistory = new SurveyHistory();
        SickList sickList = new SickList();
        sickList.setPrimaryKey(sickListID);
        sickList = ServiceLocator.getService(SickListService.class).findById(sickList);
        surveyHistory.setSickList(sickList);
        surveyHistory.setStaff(staff);
        Date date = java.sql.Date.valueOf(LocalDate.now());
        surveyHistory.setSurveyDate(date);

        request.setAttribute("surveyHistory",surveyHistory);
        page = ConfigurationManager.getProperty("PAGE_SURVEY");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
