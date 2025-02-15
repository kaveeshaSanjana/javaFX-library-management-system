package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private int stock;
    private int rackNumber;

}
