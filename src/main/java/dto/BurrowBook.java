package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BurrowBook {
    private String isbn;
    private String nic;
    private Date date;
@Override
public String toString(){
    return isbn;
}
}
