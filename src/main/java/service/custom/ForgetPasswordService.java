package service.custom;

import java.sql.SQLException;

public interface ForgetPasswordService {
    boolean sendOtp(String username) throws SQLException;
    boolean verifyOtp(String username,Integer otp) throws SQLException;
}
