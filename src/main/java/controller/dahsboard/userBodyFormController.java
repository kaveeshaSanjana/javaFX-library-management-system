package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.custom.UserService;
import util.AppModuler;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class userBodyFormController {


    public AnchorPane userAnchorPanBody;
    public TableView tblUser;
    public TableColumn colName;
    public TableColumn colNic;
    public TableColumn colDate;
    public TableColumn colTele;

    Injector injector = Guice.createInjector(AppModuler.getInstance());
    @Inject
    private UserService userService;

    @FXML
    void initialize(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTele.setCellValueFactory(new PropertyValueFactory<>("tel"));
        loadTable();
    }

    private void loadTable() {
        try {
            tblUser.setItems(userService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddUserOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/addUserForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        userAnchorPanBody.getChildren().add(loader.load());
    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/updateAndDeleteUserForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        userAnchorPanBody.getChildren().add(loader.load());
    }

    public void btnDeleteUserOnAction(ActionEvent actionEvent) {
        try {
            if(userService.delete(((User)tblUser.getSelectionModel().getSelectedItem()).getNic())){
                JOptionPane.showMessageDialog(null,"User Deleted");
            }else{
                JOptionPane.showMessageDialog(null,"User Delete Failed");
            }

            loadTable();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"User Delete Failed");
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null,"Please Select User to Delete");
        }
    }
}
