package by.hospital.command.prescription;

import by.hospital.command.Command;
import by.hospital.domain.Patient;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 15.05.2017.
 */
public class FindPressriptionByPatientLastName implements Command {

    private static final String PARAM_FIRSTNAME = "firstname";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Patient patient = new Patient();
        String firstName = request.getParameter(PARAM_FIRSTNAME);
        patient.setFirstName(firstName);
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).findByPatientFirstName(patient);
        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        request.setAttribute("sickLists", sickLists);
        request.setAttribute("prescriptionList", prescriptionList);
        page = ConfigurationManager.getProperty("PAGE_HOSPITAL");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
