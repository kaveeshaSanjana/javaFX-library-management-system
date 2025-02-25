package controller.dahsboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.LogedDetails;

public class HomebodyController {
    public Label txtLibraryName;
    @FXML
    public void initialize(){//LogedDetails.getInstance().getLibraryID()
        txtLibraryName.setText("Nanasa "+"Library");
    }
}
