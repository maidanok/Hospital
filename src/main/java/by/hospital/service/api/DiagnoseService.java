package by.hospital.service.api;

import by.hospital.domain.Diagnose;
import by.hospital.exception.PersistentException;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface DiagnoseService {

    Diagnose createNewDiagnose(String name, String therapy);

    Diagnose getDiagmose(Diagnose diagnose) throws PersistentException;

    List<Diagnose> getAll();

    boolean deleteDiagnose(Diagnose diagnose);

}
