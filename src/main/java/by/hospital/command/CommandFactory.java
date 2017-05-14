package by.hospital.command;

import by.hospital.command.hospital.OpenHospital;
import by.hospital.command.diagnose.*;
import by.hospital.command.directories.OpenDirectoriesPage;
import by.hospital.command.login.*;
import by.hospital.command.patient.*;
import by.hospital.command.sicklist.*;
import by.hospital.command.staff.*;
import by.hospital.command.surveyhistory.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
        commands.put("FindSickListBy",new FindSickListByCondition());

        commands.put("EditPatient",new EditPatient());
        commands.put("SavePatient",new SavePatient());
        commands.put("DeletePatient",new DeletePatient());
        commands.put("FindPatientByLastName",new FindPatientByLastName());

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

        commands.put("EditSurveyHistory",new EditSurveyHistory());
        commands.put("NewSurveyHistory",new NewSurveyHistory());
        commands.put("SaveSurveyHistory",new SaveSurveyHistory());
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
