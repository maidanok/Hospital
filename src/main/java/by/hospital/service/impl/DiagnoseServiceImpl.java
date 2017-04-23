package by.hospital.service.impl;

import by.hospital.DAO.mysql.MySqlDaoFactory;
import by.hospital.DAO.mysql.MySqlDiagnoseDao;
import by.hospital.domain.Diagnose;
import by.hospital.exception.PersistentException;
import by.hospital.service.api.DiagnoseService;

import java.util.List;

/**
 * Created by Admin on 23.04.2017.
 */
public class DiagnoseServiceImpl implements DiagnoseService {
   private MySqlDaoFactory mySqlDaoFactory = new MySqlDaoFactory();
   private MySqlDiagnoseDao diagnoseDao = (MySqlDiagnoseDao) mySqlDaoFactory.getDao(mySqlDaoFactory.getContext(),Diagnose.class);

    public DiagnoseServiceImpl() throws PersistentException {
    }

    @Override
    public Diagnose createNewDiagnose(String name, String therapy) {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseName(name);
        diagnose.setTherapy(therapy);
        try {
            diagnose = diagnoseDao.persist(diagnose);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return diagnose;
    }

    @Override
    public Diagnose getDiagmose(int id) throws PersistentException {
        return diagnoseDao.getByPrimaryKey(id);
    }

    @Override
    public List<Diagnose> getAll() throws PersistentException {
        return diagnoseDao.getAll();
    }

    @Override
    public boolean deleteDiagnose(int id) {
        return false;
    }//TODO
}
