package repository.custom.impl;

import repository.CrudRepository;
import repository.custom.ForgetPasswordDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordDaoImpl implements ForgetPasswordDao {

    @Override
    public boolean addOtp(String username, Integer otp) throws SQLException {
        return (Boolean) CrudUtil.execute("INSERT INTO user_otp VALUES (?,?)",username,otp);
    }

    @Override
    public boolean verifyOtp(String username, Integer otp) throws SQLException {
        ResultSet resultSet = (ResultSet) CrudUtil.execute("SELECT * FROM user_otp WHERE username = ? AND otp = ?", username,otp);
        return resultSet.next();
    }
}
