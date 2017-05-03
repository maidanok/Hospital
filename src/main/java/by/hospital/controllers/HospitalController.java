package by.hospital.controllers;

import by.hospital.domain.Prescription;
import by.hospital.domain.SickList;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.PrescriptionService;
import by.hospital.service.api.SickListService;
import by.hospital.service.impl.PrescriptionServiceImpl;
import by.hospital.service.impl.SickListServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/hospital")
public class HospitalController extends HttpServlet {
    List<SickList> sickLists = new ArrayList<>();
    List<Prescription> prescriptionList = new ArrayList<>();
    SickListService sickListService = new SickListServiceImpl();
    PrescriptionService prescriptionService = new PrescriptionServiceImpl();

    public HospitalController() throws PersistentException {
        super();
    }

    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

            sickLists = sickListService.findAllActive();
        try {
            prescriptionList = prescriptionService.getAllNotDone();
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        request.setAttribute("sickLists", sickLists);
        request.setAttribute("prescriptionList",prescriptionList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/hospital.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName()
                + ", using the POST method");
    }

    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
    }
}
