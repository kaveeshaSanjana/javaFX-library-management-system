package service.custom.impl;

import com.google.inject.Inject;
import controller.model.FineTrans;
import dto.Fine;
import entity.FineEntity;
import entity.ReturnBookEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.custom.BookBurrowDao;
import repository.custom.FineDao;
import repository.custom.ReturnBookDao;
import repository.custom.impl.ReturnBookDaoImpl;
import service.custom.BookBurrowService;
import service.custom.FineService;
import util.LogedDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class FineServiceImpl implements FineService {
    @Inject
    private FineDao fineDao;
    @Inject
    private ReturnBookDao returnBookDao;
    @Inject
    BookBurrowDao bookBurrowDao;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean pay(Fine fine) throws SQLException {
        return fineDao.save(new FineEntity(1,returnBookDao.getRIdByIds(fine.getNic(),fine.getIsbn()),fine.getFine(),fine.getDate(), LogedDetails.getInstance().getLibraryID()));
    }

    @Override
    public ObservableList<Fine> getAll() throws SQLException {
        ObservableList<Fine> fines = FXCollections.observableArrayList();
        ArrayList<FineEntity> all = fineDao.getAll();
        all.forEach(fineEntity -> {
            ReturnBookEntity returnBookEntity;
            try {
                returnBookEntity = returnBookDao.searchById(fineEntity.getReturnId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fines.add(new Fine(returnBookEntity.getUserNic(),fineEntity.getPaymentId()+"",fineEntity.getFine(),fineEntity.getDate()));
        });
        return fines;
    }

    @Override
    public ObservableList<Fine> getSearchAll(String paymentId) throws SQLException {
        ObservableList<Fine> fines = FXCollections.observableArrayList();
        ArrayList<FineEntity> all = fineDao.searchAllById(paymentId);
        all.forEach(fineEntity -> {
            ReturnBookEntity returnBookEntity;
            try {
                returnBookEntity = returnBookDao.searchById(fineEntity.getReturnId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fines.add(new Fine(returnBookEntity.getUserNic(),fineEntity.getPaymentId()+"",fineEntity.getFine(),fineEntity.getDate()));
        });
        return fines;
    }
}
