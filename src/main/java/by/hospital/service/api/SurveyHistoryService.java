package by.hospital.service.api;

import by.hospital.domain.Diagnose;
import by.hospital.domain.SickList;
import by.hospital.domain.SurveyHistory;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public interface SurveyHistoryService {

    List<SurveyHistory> getAllBySickList(SickList sickList);

    SurveyHistory getSurveyHistory(SurveyHistory surveyHistory);

    SurveyHistory createNewSurveyHistory(SurveyHistory surveyHistory);

    boolean deleteSurveyHistory(SurveyHistory surveyHistory);

    List<SurveyHistory> findByDiagnoseID(Diagnose diagnose);

    SurveyHistory saveSurveyHistory(SurveyHistory surveyHistory);
}
