package com.kt.javafx.oneDNastranFlow.view;

import com.kt.javafx.oneDNastranFlow.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ResultDialogController {

    @FXML
    TextField number_plotel_elements;
    @FXML
    TextField consistency;
    @FXML
    TextField number_first_chbdyp;
    @FXML
    TextField number_chbdyp_elements;
    @FXML
    TextField number_first_convm;
    @FXML
    TextField number_convm_elements;
    @FXML
    private Stage stage;

    @FXML
    public void prepareResultWindow() {

        number_plotel_elements.setText(String.valueOf(ResultInformation.getNumberPlotelElements()));
        consistency.setText(String.valueOf(ResultInformation.isConsistency()));
        number_first_chbdyp.setText(String.valueOf(ResultInformation.getNumberFirstChbdyp()));
        number_chbdyp_elements.setText(String.valueOf(ResultInformation.getNumberChbdypElements()));
        number_first_convm.setText(String.valueOf(ResultInformation.getNumberFirstConvm()));
        number_convm_elements.setText(String.valueOf(ResultInformation.getNumberConvmElements()));

    }
}
