package by.hospital.DAO.mysql;

import by.hospital.DAO.AbstractJDBCDao;
import by.hospital.DAO.DaoFactory;
import by.hospital.domain.PrescriptionType;
import by.hospital.exception.PersistentException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pasha on 13.04.2017.
 */
public class MySqlPrescriptionTypeDao extends AbstractJDBCDao<PrescriptionType, Integer> {

    public MySqlPrescriptionTypeDao(DaoFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    private class PersistPrescriptionType extends PrescriptionType {
        public void setPrimaryKey(int id) {
            super.setPrimaryKey(id);
        }
    }

    @Override
    public PrescriptionType create() throws PersistentException {
        PrescriptionType prescriptionType = new PersistPrescriptionType();
        return prescriptionType;
    }

    @Override
    protected String getPrimaryKeyQuery() {
        return "prescription_type_id";
    }

    @Override
    protected String getSelectedQuery() {
        return "SELECT prescription_type_id, prescription_type_name FROM prescription_type";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO prescription_type (prescription_type_name) VALUES (?);";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE prescription_type SET prescription_type_name = ? WHERE prescription_type_id = ?;";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM prescription_type WHERE prescription_type_id = ?;";
    }


    @Override
    protected List<PrescriptionType> parseResultSet(ResultSet resultSet) throws PersistentException {
        List<PrescriptionType> result = new ArrayList<>();
        try {
            while (resultSet.next()){
                PrescriptionType pt = new PersistPrescriptionType();
                pt.setPrimaryKey(resultSet.getInt("prescription_type_id"));
                pt.setTypePrescripion(resultSet.getString("prescription_type_name"));
                result.add(pt);
            }
        }catch (Exception e){
            throw new PersistentException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, PrescriptionType object) throws PersistentException {
        try {
            statement.setString(1,object.getTypePrescripion().toString());
        }catch (Exception e){
            throw new PersistentException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PrescriptionType object) throws PersistentException {
        try {
            statement.setString(1,object.getTypePrescripion().toString());
            statement.setInt(2,object.getPrimaryKey());
        }catch (Exception e){
            throw new PersistentException(e);
        }
    }
}
