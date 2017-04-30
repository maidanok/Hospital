package by.hospital.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/hospital")
public class HospitalController extends HttpServlet {
    public HospitalController(){
        super();
    }
    public void init() throws ServletException{
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/hospital.jsp").forward(request,response);
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
