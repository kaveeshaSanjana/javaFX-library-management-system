package controller.user_management;

import com.google.inject.Inject;
import dto.User;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.custom.UserService;

import java.sql.SQLException;
import java.util.Date;

public class AddUserFormController {
    public AnchorPane anchorPaneAddBook ;
    public TextField txtTel ;
    public TextField txtNic ;
    public TextField txtName ;

    @Inject
    private UserService userService;

    public void btnAddUserOnAction(ActionEvent actionEvent) {
        try {
            userService.add(new User(txtName.getText(),txtNic.getText(),txtTel.getText(),new Date()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
