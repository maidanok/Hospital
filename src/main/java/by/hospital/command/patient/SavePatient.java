package by.hospital.command.patient;

import by.hospital.command.Command;
import by.hospital.domain.Patient;
import by.hospital.domain.enumeration.Gender;
import by.hospital.prop_managers.ConfigurationManager;
import by.hospital.service.ServiceLocator;
import by.hospital.service.api.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Admin on 08.05.2017.
 */
public class SavePatient implements Command {
    private static final String PARAM_PATIENT_ID = "id";
    private static final String PARAM_PATIENT_LASTNAME="lastname";
    private static final String PARAM_PATIENT_FIRSNAME="firstname";
    private static final String PARAM_PATIENT_MIDDLENAME="middlename";
    private static final String PARAM_PATIENT_BIRTDAY="birthday";
    private static final String PARAM_PATIENT_GENDER="gender";
    private static final String PARAM_PATIENT_ADDRESS="address";
    private static final String PARAM_PATIENT_PASSPORT="passport";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        int id = Integer.parseInt(request.getParameter(PARAM_PATIENT_ID));
        String lastName = request.getParameter(PARAM_PATIENT_LASTNAME);
        String firstName = request.getParameter(PARAM_PATIENT_FIRSNAME);
        String middleName = request.getParameter(PARAM_PATIENT_MIDDLENAME);
        String address= request.getParameter(PARAM_PATIENT_ADDRESS);
        String passport = request.getParameter(PARAM_PATIENT_PASSPORT);
        Gender gender = Gender.valueOf(request.getParameter(PARAM_PATIENT_GENDER));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String getDate = request.getParameter(PARAM_PATIENT_BIRTDAY);
        Date birthday = null;
        try {
            birthday = formatter.parse(getDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Patient patient = new Patient();
        patient.setPrimaryKey(id);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setMiddleName(middleName);
        patient.setSex(gender.toString());
        patient.setAddress(address);
        patient.setBirthday(birthday);
        patient.setPassportNumber(passport);

        ServiceLocator.getService(PatientService.class).savePatient(patient);
        List<Patient> allPatient = ServiceLocator.getService(PatientService.class).getALLPatients();
        request.setAttribute("allPatient",allPatient);
        request.setAttribute("isRedirect", true);

        page= ConfigurationManager.getProperty("PAGE_DIRECTORIES");



        return page;
    }
}
