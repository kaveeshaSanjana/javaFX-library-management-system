package controller.user_management;

import com.google.inject.Inject;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import service.custom.UserService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Date;

public class UdateAndDeletePageController {
    public ComboBox comboUserName;
    public TextField txtName;
    public TextField txtId;
    public TextField txtTel;
    public TextField txtMembership;

    @Inject
    private UserService userService;

    @FXML
    public void initialize(){
        loadUseNames();
    }

    public void btnUpdateUserOnAction(ActionEvent actionEvent) {
        try {
            if(userService.updateUser(new User(txtName.getText(),txtId.getText(),txtTel.getText(),new Date()))){
                JOptionPane.showMessageDialog(null,"User Updated Success");
                loadUseNames();
                setTxtNull();
            }else {
                JOptionPane.showMessageDialog(null,"User Updated Failed");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"User Updated Failed");
        }
    }

    public void btnDeleteUserOnAction(ActionEvent actionEvent) {
        try {
            if(userService.delete(txtId.getText())){
                JOptionPane.showMessageDialog(null,"User Delete Success");
                loadUseNames();
                setTxtNull();
            }else{
                JOptionPane.showMessageDialog(null,"User Delete Failed");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"User Delete Failed");
        }
    }

    private void setTxtNull() {
        txtMembership.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtId.setText("");
    }

    private void loadUseNames(){
        try {
            comboUserName.setItems(userService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbUserNamesOnAction(ActionEvent actionEvent) {
        try {
            User selectedUser = (User) comboUserName.getSelectionModel().getSelectedItem();
            if(selectedUser!=null){
                userService.get(selectedUser.getNic());
                setDetail(selectedUser);
            }
        } catch (SQLException e) {

        }
    }

    private void setDetail(User user){
        txtId.setText(user.getNic());
        txtName.setText(user.getName());
        txtTel.setText(user.getTel());
        txtMembership.setText(""+user.getDate());
    }
}
