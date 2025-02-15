package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private int stock;
    private int rackNumber;

    @Override
    public String toString(){
        return title;
    }
}
