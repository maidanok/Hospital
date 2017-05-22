package by.hospital.dao.conditions;

/**
 * Created by Admin on 07.05.2017.
 */
public class SurveyHistoryID extends Condition {

    public SurveyHistoryID(int value) {
        super(value);
    }

    @Override
    public String getValue() {
        return " WHERE survey_history.survey_history_id = " + value + ";";
    }
}
