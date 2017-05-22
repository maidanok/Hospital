package by.hospital.command.user;

import by.hospital.command.Command;
import by.hospital.domain.Staff;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 14.05.2017.
 */
public class EditUser implements Command {
    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.DOCTOR);
        roles.add(Post.NURSE);
        roles.add(Post.ADMINISTRATOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        HttpSession session = request.getSession(false);
        Staff staff = (Staff) session.getAttribute("user");
        if (staff != null) {
            request.setAttribute("staff", staff);
            request.setAttribute("posts", Post.values());
            session.setAttribute("isUser", true);
            page = ConfigurationManager.getProperty("PAGE_STAFF");
            return page;
        } else {
            page = ConfigurationManager.getProperty("PAGE_LOGIN");
            return page;
        }
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
