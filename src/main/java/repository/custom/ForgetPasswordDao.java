package repository.custom;

import java.sql.SQLException;

public interface ForgetPasswordDao {
    boolean addOtp(String username,Integer otp) throws SQLException;
    boolean verifyOtp(String username,Integer otp) throws SQLException;
}
