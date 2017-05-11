package by.hospital.command.sicklist;

import by.hospital.command.Command;
import by.hospital.domain.SickList;
import by.hospital.domain.enumeration.Post;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.SickListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 11.05.2017.
 */
public class DeleteSickList implements Command {
    private static final String PARAM_SICK_LIST_ID = "id";

    private static Set<Post> roles = new HashSet<>();

    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.NURSE);
        roles.add(Post.DOCTOR);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int sickListID = Integer.parseInt(request.getParameter(PARAM_SICK_LIST_ID));
        SickList sickList = new SickList();
        sickList.setPrimaryKey(sickListID);
        ServiceLocator.getService(SickListService.class).deleteSickList(sickList);

        List<SickList> sickLists = ServiceLocator.getService(SickListService.class).findAllActive();
        request.setAttribute("sickLists", sickLists);
        page = "hospital.html";
        request.setAttribute("isRedirect", true);
        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
