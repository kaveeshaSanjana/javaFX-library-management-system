package service.custom;

import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public interface ReportService {
    void generateAllUserReport() throws JRException, SQLException;
    void generateUserDetailReport(String userNic) throws JRException, SQLException;
}
