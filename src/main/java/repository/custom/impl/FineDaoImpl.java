package repository.custom.impl;

import dbConnection.DBConnection;
import entity.FineEntity;
import repository.custom.FineDao;
import util.CrudUtil;
import util.LogedDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineDaoImpl implements FineDao {
    @Override
    public boolean save(FineEntity fineEntity) throws SQLException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            if(Boolean.TRUE.equals((Boolean)CrudUtil.execute("INSERT INTO fine(retrunId,fine,date,libraryId) VALUES (?,?,?,?) ",fineEntity.getReturnId(),fineEntity.getFine(),fineEntity.getDate(), LogedDetails.getInstance().getLibraryID()))){
               DBConnection.getInstance().getConnection().commit();
               return true;
            }else{
                DBConnection.getInstance().getConnection().rollback();
                return false;
            }

        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

    @Override
    public boolean delete(String bookId) throws SQLException {
        return false;
    }

    @Override
    public boolean update(FineEntity fineEntity) throws SQLException {
        return false;
    }

    @Override
    public FineEntity searchById(String s) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<FineEntity> getAll() throws SQLException {
        return null;
    }
}
