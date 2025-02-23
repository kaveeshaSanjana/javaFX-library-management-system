package controller.dahsboard;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import service.custom.FineService;
import util.AppModuler;

import java.io.IOException;
import java.sql.SQLException;

public class FineBodyController
{
    public AnchorPane finebookAnchorPanBody;
    public TableView tblFineBook;
    public TableColumn colPaymentID;
    public TableColumn colPayment;
    public TableColumn colDate;
    public TableColumn colNic;
    public TextField searchField;

    @Inject
    private FineService fineService;

    @FXML
    void initialize(){
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("fine"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        loadTable();
    }
    public void btnFineOnAction(ActionEvent actionEvent) throws IOException {
        finebookAnchorPanBody.getChildren().clear();
        Injector injector = Guice.createInjector(AppModuler.getInstance());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fine-management/payFineForm.fxml"));
        loader.setControllerFactory(injector::getInstance);
        finebookAnchorPanBody.getChildren().add(loader.load());
    }
    void loadTable(){
        try {
            tblFineBook.setItems(fineService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOnType(KeyEvent keyEvent) {
        try {
            if(searchField.getText().isEmpty()){
                loadTable();
                return;
            }
            tblFineBook.setItems(fineService.getSearchAll(searchField.getText()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
