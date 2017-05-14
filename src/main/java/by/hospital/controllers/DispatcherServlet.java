package by.hospital.controllers;

import by.hospital.command.CommandFactory;
import by.hospital.prop_managers.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Admin on 13.05.2017.
 */
public class DispatcherServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DispatcherServlet.class);
    private static Map<String, String> links = new ConcurrentHashMap<>();
    private static Map<String,String[]> resources = new ConcurrentHashMap<>();
    private CommandFactory commandFactory = CommandFactory.getInstance();


    public DispatcherServlet() {
        super();
    }

    @Override
    public void init() {
        links.put("/login", "Login");
        links.put("/hospital", "OpenHospital");
        links.put("/directories", "OpenDirectories");

        resources.put("OpenDirectories",new String[]{"allPatient","allStaff","allDiagnose"});
        resources.put("OpenHospital", new String[]{"sickLists","prescriptionList"});
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        int beginAction = contextPath.length();
        int endAction = uri.lastIndexOf('.');
        String commandName;
        if (endAction >= 0) {
            commandName = uri.substring(beginAction, endAction);
        } else {
            commandName = uri.substring(beginAction);
        }
        String command = links.get(commandName);

        HttpSession session = request.getSession(true);
        if (command != null) {
            String []attributes = resources.get(command);
            for (String attribute : attributes) {
                request.setAttribute(attribute,session.getAttribute(attribute));
                session.removeAttribute(attribute);
            }
            String page = ConfigurationManager.getProperty(command);
            request.getServletContext().getRequestDispatcher(page).forward(request, response);
        } else {
            logger.error("Page non found");
            request.getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("PAGE_ERROR")).forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
