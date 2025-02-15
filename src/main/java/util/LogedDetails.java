package util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LogedDetails {
    private  int userID;
    private  int libraryID = 1;
    private  String userName;
    private  static LogedDetails instance;

    private LogedDetails(){}

    public static LogedDetails getInstance(){return instance==null?instance=new LogedDetails():instance;}
}
