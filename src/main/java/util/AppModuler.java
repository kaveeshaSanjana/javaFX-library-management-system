package util;

import com.google.inject.AbstractModule;
import com.google.inject.Module;
import repository.custom.BookBurrowDao;
import repository.custom.FineDao;
import repository.custom.ReturnBookDao;
import repository.custom.UserDao;
import repository.custom.impl.BookBurrowDaoImpl;
import repository.custom.impl.FineDaoImpl;
import repository.custom.impl.ReturnBookDaoImpl;
import repository.custom.impl.UserDaoImpl;
import service.custom.*;
import service.custom.impl.*;

public class AppModuler extends AbstractModule {
    private static AppModuler appModuler;
    private AppModuler(){}
    public static AppModuler getInstance() {
        return appModuler==null?appModuler = new AppModuler():appModuler;
    }

    @Override
    protected void configure() {
        bind(BookService.class).to(BookServiceImpl.class);
        bind(BookBurrowService.class).to(BookBurrowServiceImpl.class);
        bind(BookBurrowDao.class).to(BookBurrowDaoImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(UserDao.class).to(UserDaoImpl.class);
        bind(BookReturnService.class).to(BookReturnServiceImpl.class);
        bind(ReturnBookDao.class).to(ReturnBookDaoImpl.class);
        bind(FineService.class).to(FineServiceImpl.class);
        bind(FineDao.class).to(FineDaoImpl.class);
    }
}
