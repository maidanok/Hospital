package by.hospital.command.sicklist;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.domain.enumeration.Post;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import by.hospital.service.api.SurveyHistoryService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class EditSickList implements Command {
    private static final String PARAM_SICK_LIST_ID = "id";
    private static Set<Post> roles =new HashSet<>();
    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.NURSE);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int sicklistID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_ID));
        SickList sickList = new SickList();
        sickList.setPrimaryKey(sicklistID);
        sickList = ServiceLocator.getService(SickListService.class).findById(sickList);

        List <SurveyHistory> surveyHistoryList = ServiceLocator.getService(SurveyHistoryService.class).getAllbySickList(sickList);
        List <Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).findBySickList(sickList);

        request.setAttribute("sickList",sickList);
        request.setAttribute("surveyHistoryList",surveyHistoryList);
        request.setAttribute("prescriptionList",prescriptionList);

        page= by.hospital.prop_managers.ConfigurationManager.getProperty("PAGE_SICKLIST");

        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
