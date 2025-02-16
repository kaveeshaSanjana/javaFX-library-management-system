package service.custom.impl;

import dbConnection.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import util.LogedDetails;

import java.sql.SQLException;
import java.util.HashMap;

public class ReportServiceImpl implements ReportService{
    @Override
    public void generateAllUserReport() throws JRException, SQLException {
            JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/report/libraryUser.jrxml");
            HashMap<String, Object> param = new HashMap<>();
            param.put("LIBRARY_ID", LogedDetails.getInstance().getLibraryID());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint,false);
    }

    @Override
    public void generateUserDetailReport(String userNic)  throws JRException, SQLException{
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/report/UserDetailReport.jrxml");
        HashMap<String, Object> param = new HashMap<>();
        param.put("library_id", LogedDetails.getInstance().getLibraryID());
        param.put("user_nic", userNic);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
