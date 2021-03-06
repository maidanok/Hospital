package by.hospital.command;

import by.hospital.command.diagnose.DeleteDiagnose;
import by.hospital.command.diagnose.EditDiagnose;
import by.hospital.command.diagnose.SaveDiagnose;
import by.hospital.command.directories.OpenDirectoriesPage;
import by.hospital.command.hospital.OpenHospital;
import by.hospital.command.language.SetLanguage;
import by.hospital.command.login.LogOutCommand;
import by.hospital.command.login.LoginCommand;
import by.hospital.command.patient.DeletePatient;
import by.hospital.command.patient.EditPatient;
import by.hospital.command.patient.FindPatientByLastName;
import by.hospital.command.patient.SavePatient;
import by.hospital.command.prescription.*;
import by.hospital.command.sicklist.*;
import by.hospital.command.staff.DeleteStaff;
import by.hospital.command.staff.EditStaff;
import by.hospital.command.staff.SaveStaff;
import by.hospital.command.surveyhistory.DeleteSurveyHistory;
import by.hospital.command.surveyhistory.EditSurveyHistory;
import by.hospital.command.surveyhistory.NewSurveyHistory;
import by.hospital.command.surveyhistory.SaveSurveyHistory;
import by.hospital.command.user.EditUser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * Created by Admin on 07.05.2017.
 */
public class CommandFactory {
    Logger logger = Logger.getLogger(CommandFactory.class);
    private static CommandFactory instance = null;
    HashMap<String, Command> commands = new HashMap<String, Command>();


    private CommandFactory() {
        commands.put("Login", new LoginCommand());
        commands.put("Logout", new LogOutCommand());
        commands.put("OpenDirectories", new OpenDirectoriesPage());
        commands.put("OpenHospital", new OpenHospital());
        commands.put("FindSickListBy", new FindSickListByCondition());
        commands.put("EditUser", new EditUser());
        commands.put("SetLanguage", new SetLanguage());

        commands.put("EditPatient", new EditPatient());
        commands.put("SavePatient", new SavePatient());
        commands.put("DeletePatient", new DeletePatient());
        commands.put("FindPatientByLastName", new FindPatientByLastName());

        commands.put("EditStaff", new EditStaff());
        commands.put("SaveStaff", new SaveStaff());
        commands.put("DeleteStaff", new DeleteStaff());

        commands.put("EditDiagnose", new EditDiagnose());
        commands.put("SaveDiagnose", new SaveDiagnose());
        commands.put("DeleteDiagnose", new DeleteDiagnose());

        commands.put("NewSickList", new NewSickList());
        commands.put("EditSickList", new EditSickList());
        commands.put("SaveSickList", new SaveSickList());
        commands.put("DeleteSickList", new DeleteSickList());

        commands.put("EditSurveyHistory", new EditSurveyHistory());
        commands.put("NewSurveyHistory", new NewSurveyHistory());
        commands.put("SaveSurveyHistory", new SaveSurveyHistory());
        commands.put("DeleteSurveyHistory", new DeleteSurveyHistory());

        commands.put("EditPrescription", new EditPrescription());
        commands.put("SavePrescription", new SavePrescription());
        commands.put("DeletePrescription", new DeletePrescription());
        commands.put("ExecutePrescriptionHosp", new ExecutePrescriptionHosp());
        commands.put("ExecutePrescriptionList", new ExecutePrescriptionList());
        commands.put("ExecutePrescriptionSurvey", new ExecutePrescriptionSurvey());
        commands.put("FindPressriptionByPatientLastName", new FindPressriptionByPatientLastName());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("COMMAND");
        Command command = commands.get(action);
        if (command == null) {
            logger.info("console.command==null");
            command = new NoCommand();
        }
        return command;
    }

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

}
