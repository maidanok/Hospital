package by.hospital.domain.comparator;

import by.hospital.domain.Prescription;

import java.util.Comparator;

/**
 * Created by Admin on 20.05.2017.
 */
public class SortPrescriptionByPatient implements Comparator<Prescription> {
    @Override
    public int compare(Prescription prescription1, Prescription prescription2) {
        return prescription1.getSurveyHistory().getSickList().getPatient().getFirstName()
                .compareTo(prescription2.getSurveyHistory().getSickList().getPatient().getFirstName());
    }
}
