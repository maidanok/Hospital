package by.hospital.controllers;

import by.hospital.command.Command;
import by.hospital.command.CommandFactory;
import by.hospital.service.ServiceInitializer;

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
@WebServlet("/controller")
public class HospitalController extends HttpServlet {

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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request,response);

    }

    public void init() throws ServletException {
        ServiceInitializer.init();
        super.init();

    }


}
