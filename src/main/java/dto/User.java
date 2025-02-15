package dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String nic;
    private String tel;
    private Date date;

    @Override
    public String toString(){
        return name;
    }
}
