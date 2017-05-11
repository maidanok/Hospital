package by.hospital.command.diagnose;

import by.hospital.command.Command;
import by.hospital.domain.Diagnose;
import by.hospital.domain.enumeration.Post;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.DiagnoseService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Pasha on 10.05.2017.
 */
public class DeleteDiagnose implements Command {
    private static final String PARAM_DIAGNOSE_ID = "id";
    private static Set<Post> roles =new HashSet<>();
    static {
        roles.add(Post.ADMINISTRATOR);
        roles.add(Post.DOCTOR);
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int id = Integer.parseInt(request.getParameter(PARAM_DIAGNOSE_ID));
        Diagnose diagnose = new Diagnose();
        diagnose.setPrimaryKey(id);
        ServiceLocator.getService(DiagnoseService.class).deleteDiagnose(diagnose);

        List<Diagnose> allDiagnose = ServiceLocator.getService(DiagnoseService.class).getAll();
        request.setAttribute("allDiagnose", allDiagnose);
        request.setAttribute("isRedirect", true);
        page = "directories.html";

        return page;
    }

    @Override
    public Set<Post> getAllowPosts() {
        return roles;
    }
}
