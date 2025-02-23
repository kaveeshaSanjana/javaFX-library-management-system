package service.custom.impl;

import com.google.inject.Inject;
import repository.custom.ForgetPasswordDao;
import service.custom.ForgetPasswordService;
import util.GmailSender;

import java.sql.SQLException;
import java.util.Random;

public class ForgetPasswordServiceImpl implements ForgetPasswordService {
    @Inject
    private ForgetPasswordDao forgetPasswordDao;

    @Override
    public boolean sendOtp(String username) throws SQLException {
        int otp = (int) (Math.random() * 90000) + 10000;
        forgetPasswordDao.addOtp(username,otp);
        GmailSender.sendEmail("Forget Password OTP",otp+"");
        return true;
    }

    @Override
    public boolean verifyOtp(String username,Integer otp) throws SQLException {
        return forgetPasswordDao.verifyOtp(username,otp);
    }
}
