package by.hospital.command.sicklist;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.DateConvertor;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by Pasha on 11.05.2017.
 */
public class FindSickListByCondition implements Command {
    Logger logger = Logger.getLogger(FindSickListByCondition.class);
    private static final String PARAM_PATIENT_FIRSTNAME = "firstname";
    private static final String PARAM_SICK_LIST_DATE_IN = "dateIn";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String firstName = request.getParameter(PARAM_PATIENT_FIRSTNAME);
        if (firstName == "") {
            firstName = null;
        }
        String date = request.getParameter(PARAM_SICK_LIST_DATE_IN);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateIN = null;
        if (date != "") {
            try {
                dateIN = formatter.parse(date);
            } catch (ParseException e) {
                logger.error("Date Parse error" + date + "not parse");
                logger.error(e.getLocalizedMessage());
            }
        }

        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).
                findByPatientAndDAte(firstName, DateConvertor.getInstanse().convert(dateIN));
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
        request.setAttribute("prescriptionList", prescriptionList);
        request.setAttribute("sickLists", sickLists);

        page = ConfigurationManager.getProperty("PAGE_HOSPITAL");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
