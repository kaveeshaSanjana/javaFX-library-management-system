package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FineEntity {
    private int paymentId;
    private int returnId;
    private Double fine;
    private String date;
    private int libraryId;
}
