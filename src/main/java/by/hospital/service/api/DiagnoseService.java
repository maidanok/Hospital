package by.hospital.service.api;

import by.hospital.domain.Diagnose;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface DiagnoseService {

    Diagnose createNewDiagnose(Diagnose diagnose);

    Diagnose getDiagnose(Diagnose diagnose);

    List<Diagnose> getAll();

    boolean deleteDiagnose(Diagnose diagnose);

    void saveDiagnose(Diagnose diagnose);

}
