package by.hospital.command.Hospital;

import by.hospital.command.Command;
import by.hospital.domain.*;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class OpenHospital implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        List<Prescription> prescriptionList = ServiceLocator.getService(PrescriptionService.class).getAllNotDone();
        request.setAttribute("sickLists",sickLists);
        request.setAttribute("prescriptionList",prescriptionList);
        page = ConfigurationManager.getProperty("PAGE_HOSPITAL");
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return null;
    }
}
