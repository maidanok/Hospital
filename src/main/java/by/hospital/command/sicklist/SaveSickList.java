package by.hospital.command.sicklist;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.enumeration.Post;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 11.05.2017.
 */
public class SaveSickList implements Command {
    Logger logger = Logger.getLogger(SaveSickList.class);
    private static final String PARAM_SICK_LIST_ID = "id";
    private static final String PARAM_SICK_LIST_PATIENTID = "patientid";
    private static final String PARAM_SICK_LIST_DATEIN = "datein";
    private static final String PARAM_SICK_LIST_ROOM = "room";
    private static final String PARAM_SICK_LIST_SYMPTOMS = "symptoms";
    private static final String PARAM_SICK_LIST_F_DIAGNOSE_ID = "diagnose";
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
        int patientID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_PATIENTID));
        int diagnoseID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_F_DIAGNOSE_ID));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateIn = null;
        String getDateIN = request.getParameter(PARAM_SICK_LIST_DATEIN);
        try {
            dateIn = formatter.parse(getDateIN);
        } catch (ParseException e) {
            logger.error("Error date " + e.getLocalizedMessage());
        }
        String room = request.getParameter(PARAM_SICK_LIST_ROOM);
        String symptoms = request.getParameter(PARAM_SICK_LIST_SYMPTOMS);


        Patient patient = new Patient();
        patient.setPrimaryKey(patientID);
        Diagnose diagnose = new Diagnose();
        diagnose.setPrimaryKey(diagnoseID);
        SickList sickList = new SickList();
        sickList.setPrimaryKey(sickListID);
        sickList.setPatient(patient);
        sickList.setDateIN(dateIn);
        sickList.setRoom(room);
        sickList.setSymptoms(symptoms);
        sickList.setFinalDiagnose(diagnose);

        HttpSession session = request.getSession(true);
        ServiceLocator.getService(SickListService.class).saveSickList(sickList);
        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
        session.setAttribute("sickLists", sickLists);
        session.setAttribute("prescriptionList",prescriptionList);

        request.setAttribute("isRedirect",true);
        page = "hospital.html";
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
