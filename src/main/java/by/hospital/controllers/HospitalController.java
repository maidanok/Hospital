package by.hospital.controllers;

import by.hospital.command.Command;
import by.hospital.command.CommandFactory;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceInitializer;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Admin on 07.05.2017.
 */
//@WebServlet("/controller")
public class HospitalController extends HttpServlet {

    Logger logger = Logger.getLogger(HospitalController.class);
    CommandFactory commandFactory = CommandFactory.getInstance();

    public HospitalController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String page = null;
        Command command = commandFactory.getCommand(request);
        page = command.execute(request,response);

        boolean isRedirect =(request.getAttribute("isRedirect") != null) ? (boolean) request.getAttribute("isRedirect") : false;
        if (page != null && isRedirect) {
            response.sendRedirect( request.getContextPath() + "/index.jsp");
        } else {
            if (page != null) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            } else {
                //page = ConfigurationManager.getProperty("path.page.error");
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }
    }

    public void init() throws ServletException {
        ServiceInitializer.init();
        super.init();
        logger.info("Hospitall_Controller start");

    }


}
