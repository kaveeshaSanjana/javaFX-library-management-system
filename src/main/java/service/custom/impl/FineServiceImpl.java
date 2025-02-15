package service.custom.impl;

import com.google.inject.Inject;
import controller.model.FineTrans;
import dto.Fine;
import entity.FineEntity;
import org.modelmapper.ModelMapper;
import repository.custom.BookBurrowDao;
import repository.custom.FineDao;
import repository.custom.ReturnBookDao;
import repository.custom.impl.ReturnBookDaoImpl;
import service.custom.BookBurrowService;
import service.custom.FineService;
import util.LogedDetails;

import java.sql.SQLException;

public class FineServiceImpl implements FineService {
    @Inject
    private FineDao fineDao;
    @Inject
    private ReturnBookDao returnBookDao;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean pay(Fine fine) throws SQLException {

        return fineDao.save(new FineEntity(1,returnBookDao.getRIdByIds(fine.getNic(),fine.getIsbn()),fine.getFine(),fine.getDate(), LogedDetails.getInstance().getLibraryID()));
    }
}
