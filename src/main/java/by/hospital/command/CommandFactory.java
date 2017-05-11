package by.hospital.command;

import by.hospital.command.Hospital.OpenHospital;
import by.hospital.command.diagnose.DeleteDiagnose;
import by.hospital.command.diagnose.EditDiagnose;
import by.hospital.command.diagnose.SaveDiagnose;
import by.hospital.command.directories.OpenDirectoriesPage;
import by.hospital.command.login.LogOutCommand;
import by.hospital.command.login.LoginCommand;
import by.hospital.command.patient.DeletePatient;
import by.hospital.command.patient.EditPatient;
import by.hospital.command.patient.SavePatient;
import by.hospital.command.sicklist.DeleteSickList;
import by.hospital.command.sicklist.EditSickList;
import by.hospital.command.sicklist.NewSickList;
import by.hospital.command.sicklist.SaveSickList;
import by.hospital.command.staff.DeleteStaff;
import by.hospital.command.staff.EditStaff;
import by.hospital.command.staff.SaveStaff;
import by.hospital.domain.enumeration.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 07.05.2017.
 */
public class CommandFactory {
    private static CommandFactory instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();


    private CommandFactory(){
        commands.put("Login",new LoginCommand());
        commands.put("Logout",new LogOutCommand());
        commands.put("OpenDirectories", new OpenDirectoriesPage());
        commands.put("OpenHospital", new OpenHospital());

        commands.put("EditPatient",new EditPatient());
        commands.put("SavePatient",new SavePatient());
        commands.put("DeletePatient",new DeletePatient());

        commands.put("EditStaff", new EditStaff());
        commands.put("SaveStaff", new SaveStaff());
        commands.put("DeleteStaff", new DeleteStaff());

        commands.put("EditDiagnose",new EditDiagnose());
        commands.put("SaveDiagnose", new SaveDiagnose());
        commands.put("DeleteDiagnose", new DeleteDiagnose());

        commands.put("NewSickList", new NewSickList());
        commands.put("EditSickList", new EditSickList());
        commands.put("SaveSickList", new SaveSickList());
        commands.put("DeleteSickList", new DeleteSickList());
    }

    public Command getCommand(HttpServletRequest request){
        String action = request.getParameter("COMMAND");
        Command command = commands.get(action);
        if (command == null) {
            System.out.println("console.command==null");
            command = new NoCommand();
        }
        return command;
    }

    public static CommandFactory getInstance(){
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

}
