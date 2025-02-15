package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fine {
    private String nic;
    private String isbn;
    private Double fine;
    private String date;
}
