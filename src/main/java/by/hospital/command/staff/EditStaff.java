package by.hospital.command.staff;

import by.hospital.command.Command;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.StaffService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 08.05.2017.
 */
public class EditStaff implements Command {
    private static final String PARAM_STAFF_ID = "id";
    private static Set<Post> roles =new HashSet<>();
    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int staffID = Integer.valueOf(request.getParameter(PARAM_STAFF_ID));
        Staff staff = new Staff();
        staff.setPrimaryKey(staffID);
        if (staffID!=0) {
            staff = ServiceLocator.getService(StaffService.class).returnStaffFull(staff);
        }
        request.setAttribute("staff",staff);
        request.setAttribute("posts", Post.values());
        page= ConfigurationManager.getProperty("PAGE_STAFF");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
