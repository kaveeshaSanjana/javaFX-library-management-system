package repository.custom.impl;

import dbConnection.DBConnection;
import entity.FineEntity;
import repository.custom.FineDao;
import util.CrudUtil;
import util.LogedDetails;

import java.sql.ResultSet;
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
    public ArrayList<FineEntity> searchAllById(String paymentId) throws SQLException {
        ArrayList<FineEntity> fineEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM fine WHERE libraryId = ? AND paymentId LIKE ?", LogedDetails.getInstance().getLibraryID(),"%"+paymentId+"%");
        while (rst.next()){
            fineEntities.add(new FineEntity(rst.getInt("paymentId"),rst.getInt("retrunId"),rst.getDouble("Fine"),rst.getString("date"),rst.getInt("libraryId")));
        }
        return fineEntities;
    }

    @Override
    public ArrayList<FineEntity> getAll() throws SQLException {
        ArrayList<FineEntity> fineEntities = new ArrayList<>();
        ResultSet rst = (ResultSet) CrudUtil.execute("SELECT * FROM fine WHERE libraryId = ? ", LogedDetails.getInstance().getLibraryID());
        while (rst.next()){
            fineEntities.add(new FineEntity(rst.getInt("paymentId"),rst.getInt("retrunId"),rst.getDouble("Fine"),rst.getString("date"),rst.getInt("libraryId")));
        }
        return fineEntities;
    }
}
