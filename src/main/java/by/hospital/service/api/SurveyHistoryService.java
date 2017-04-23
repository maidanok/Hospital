package by.hospital.service.api;

import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;

import java.sql.Date;
import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface SurveyHistoryService {

    List<SurveyHistory> getAllbySickList(int sickListID) throws PersistentException;

    SurveyHistory returnSurveyHistoru(int id) throws PersistentException;

    SurveyHistory createNewSurveyHistory(int sickID, int diagnoseID, int staffID, Date date, String description) throws PersistentException;

    boolean deleteSurveyHistory(int id);

    List<SurveyHistory>findByDiagnoseID(int id);
}
