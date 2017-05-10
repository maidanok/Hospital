package by.hospital.service.api;

import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;
import by.hospital.exception.PersistentException;

import java.sql.Date;
import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface SurveyHistoryService {

    List<SurveyHistory> getAllbySickList(SickList sickList) throws PersistentException;

    SurveyHistory returnSurveyHistoru(SurveyHistory surveyHistory) throws PersistentException;

    SurveyHistory createNewSurveyHistory(int sickID, int diagnoseID, int staffID, Date date, String description) throws PersistentException;

    boolean deleteSurveyHistory(SurveyHistory surveyHistory);

    List<SurveyHistory> findByDiagnoseID(Diagnose diagnose);
}
