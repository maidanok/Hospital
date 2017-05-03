package by.hospital.controllers;

import by.hospital.domain.Diagnose;
import by.hospital.domain.Patient;
import by.hospital.domain.Staff;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.DiagnoseService;
import by.hospital.service.api.PatientService;
import by.hospital.service.api.StaffService;
import by.hospital.service.impl.DiagnoseServiceImpl;
import by.hospital.service.impl.PatientServiceImpl;
import by.hospital.service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30.04.2017.
 */
@WebServlet(urlPatterns = "/directories")
public class DirectoriesController extends HttpServlet {
    PatientService patientService = new PatientServiceImpl();
    StaffService staffService = new StaffServiceImpl();
    DiagnoseService diagnoseService = new DiagnoseServiceImpl();
    List<Patient> allPatient = new ArrayList<>();
    List<Staff> allStaff = new ArrayList<>();
    List<Diagnose> allDiagnose = new ArrayList<>();

    public DirectoriesController() throws PersistentException {
        super();
    }

    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            allPatient = patientService.getALLPatients();
            allStaff = staffService.getAllStaff();
            allDiagnose = diagnoseService.getAll();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        request.setAttribute("allPatient",allPatient);
        request.setAttribute("allStaff",allStaff);
        request.setAttribute("allDiagnose",allDiagnose);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/directories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName()
                + ", using the POST method");
    }

    public void destroy() {
        super.destroy();
    }


}
