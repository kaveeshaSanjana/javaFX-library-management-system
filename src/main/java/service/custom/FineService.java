package service.custom;

import dto.Fine;

import java.sql.SQLException;

public interface FineService {
    boolean pay(Fine fine) throws SQLException;

}
