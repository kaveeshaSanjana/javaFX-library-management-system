package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReturnBookEntity {
    private String isbn;
    private String userNic;
    private String date;
}
