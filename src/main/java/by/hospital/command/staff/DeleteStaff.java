package by.hospital.command.staff;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PatientService;
import by.hospital.service.api.StaffService;

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
public class DeleteStaff implements Command {
    private static final String PARAM_STAFF_ID = "id";
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
    }
//TODO ошибка в SQL
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int staffID = Integer.valueOf(request.getParameter(PARAM_STAFF_ID));
        Staff staff = new Staff();
        staff.setPrimaryKey(staffID);

        ServiceLocator.getService(StaffService.class).deleteStaff(staff);
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        List<Staff> allStaff = ServiceLocator.getService(StaffService.class).getAllStaff();
        List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        request.setAttribute("allPatient", allPatient);
        request.setAttribute("allStaff", allStaff);
        request.setAttribute("allDiagnose", allDiagnose);
        page = ConfigurationManager.getProperty("PAGE_DIRECTORIES");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
