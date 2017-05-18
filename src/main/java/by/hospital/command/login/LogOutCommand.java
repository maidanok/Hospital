package by.hospital.command.login;

import by.hospital.command.Command;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

/**
 * Created by Admin on 08.05.2017.
 */
public class LogOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(true);
        session.invalidate();
        page = ConfigurationManager.getProperty("PAGE_LOGIN");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
