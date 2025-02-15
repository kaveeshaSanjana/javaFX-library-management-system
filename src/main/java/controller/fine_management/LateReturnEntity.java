package controller.fine_management;

import dto.Book;
import dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LateReturnEntity {
    private String userNic;
    private String userName;
    private String bookIsbn;
    private String bookName;
}
