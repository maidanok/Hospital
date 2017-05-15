package by.hospital.dao.mysql;

import by.hospital.dao.AbstractJDBCDao;
import by.hospital.domain.Diagnose;
import by.hospital.exception.PersistentException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 12.04.2017.
 */
public class MySqlDiagnoseDao extends AbstractJDBCDao <Diagnose, Integer> {
    Logger logger = Logger.getLogger(MySqlDiagnoseDao.class);

    private class PersistDiagnose extends Diagnose{
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    public MySqlDiagnoseDao(Connection connection) {
        super(connection);
    }

    @Override
    public Diagnose create() throws PersistentException {
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "diagnose_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT diagnose_id, diagnose_name, therapy \n" +
                "FROM diagnose";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO diagnose (diagnose_name, therapy)\n" +
                "VALUES (?, ?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE diagnose SET diagnose_name = ?, therapy = ? \n" +
                "WHERE diagnose_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM diagnose WHERE diagnose_id = ?;";
    }

    @Override
    protected List<Diagnose> parseResultSet(ResultSet resultSet) throws PersistentException {
        List <Diagnose> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                PersistDiagnose diagnose = new PersistDiagnose();
                diagnose.setPrimaryKey(resultSet.getInt("diagnose_id"));
                diagnose.setDiagnoseName(resultSet.getString("diagnose_name"));
                diagnose.setTherapy(resultSet.getString("therapy"));
                result.add(diagnose);
            }
        } catch (Exception e) {
            logger.error("error parse "+e.getLocalizedMessage());
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Diagnose object) throws PersistentException {
        try {
            statement.setString(1, object.getDiagnoseName());
            statement.setString(2, object.getTherapy());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new PersistentException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Diagnose object) throws PersistentException {
        try {
            statement.setString(1, object.getDiagnoseName());
            statement.setString(2, object.getTherapy());
            statement.setInt(3,object.getPrimaryKey());
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            throw new PersistentException(e);
        }
    }
}
