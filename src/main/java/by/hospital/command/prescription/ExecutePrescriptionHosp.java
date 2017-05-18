package by.hospital.command.prescription;

import by.hospital.command.Command;
import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 15.05.2017.
 */
public class ExecutePrescriptionHosp implements Command {
    private static final String PARAM_PRESCRIPTION_ID = "id";

    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.DOCTOR);
        roles.add(Post.NURSE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        int prescriptionID = Integer.parseInt(request.getParameter(PARAM_PRESCRIPTION_ID));
        HttpSession session = request.getSession(false);
        Staff staff = (Staff) session.getAttribute("user");
        Prescription prescription = new Prescription();
        prescription.setPrimaryKey(prescriptionID);
        ServiceLocator.getService(PrescriptionService.class).executePrescription(prescription, staff);
        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
        request.setAttribute("sickLists", sickLists);
        request.setAttribute("prescriptionList", prescriptionList);
        page = ConfigurationManager.getProperty("PAGE_HOSPITAL");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
