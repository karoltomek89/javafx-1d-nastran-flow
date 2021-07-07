package com.kt.javafx.oneDNastranFlow.view;

import java.io.File;
import java.io.IOException;

import com.kt.javafx.oneDNastranFlow.App;
import com.kt.javafx.oneDNastranFlow.controller.MainController;
import com.kt.javafx.oneDNastranFlow.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    TextField input_offset;
    @FXML
    TextField input_phbdy_property_id;
    @FXML
    TextField input_diameter1;
    @FXML
    TextField input_diameter2;
    @FXML
    TextField input_pconv_property_id;
    @FXML
    TextField input_mat_id;
    @FXML
    TextField input_mass_flow_node_id;
    @FXML
    ChoiceBox input_choice_form_type;
    @FXML
    ChoiceBox input_choice_flag;
    @FXML
    ChoiceBox input_choice_surface_type;
    @FXML
    ChoiceBox input_choice_mat;
    @FXML
    private Stage stage;
    @FXML
    private Label path_to_bdf;
    @FXML
    private CheckBox check_box_ordering;

    MainController mainController = new MainController(new BDFReader(), new PlotelManipulator(),
            new PropertiesCreator(), new ElementsCreator());

    @FXML
    public void initialize() {
        input_choice_form_type.getItems().removeAll(input_choice_form_type.getItems());
        input_choice_form_type.getItems().addAll("0", "1");

        input_choice_flag.getItems().removeAll(input_choice_flag.getItems());
        input_choice_flag.getItems().addAll("0", "1");

        input_choice_surface_type.getItems().removeAll(input_choice_surface_type.getItems());
        input_choice_surface_type.getItems().addAll("FTUBE");

        input_choice_mat.getItems().removeAll(input_choice_mat.getItems());
        input_choice_mat.getItems().addAll("AIR_20C_T_MM_S", "WATER_20C_T_MM_S", "AIR_20C_KG_M_S", "WATER_20C_KG_M_S" );

        input_offset.setText("100000");
        input_phbdy_property_id.setText("888");
        input_diameter1.setText("10");
        input_diameter2.setText("10");
        input_pconv_property_id.setText("999");
        input_mat_id.setText("99");
        input_mass_flow_node_id.setText("9999999");
        input_choice_form_type.getSelectionModel().select(1);
        input_choice_flag.getSelectionModel().select(1);
        input_choice_surface_type.getSelectionModel().select(0);
        input_choice_mat.getSelectionModel().select(0);


    }

    @FXML
    private void clickSelectBdfButton() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Nastran Files", "*.bdf"));
        File file = fileChooser.showOpenDialog(stage);
        path_to_bdf.setText(file.getName());
    }

    @FXML
    private void clickRunButton() {
        mainController.processFiles(input_offset.getText(),
                input_mass_flow_node_id.getText(),
                input_phbdy_property_id.getText(),
                input_diameter1.getText(),
                input_diameter2.getText(),
                input_pconv_property_id.getText(),
                input_mat_id.getText(),
                input_choice_form_type.getValue().toString(),
                input_choice_flag.getValue().toString(),
                path_to_bdf.getText(),
                input_choice_mat.getValue().toString(),
                check_box_ordering.isSelected(),
                input_choice_surface_type.getValue().toString());

        openResultDialog();
        mainController.clear();
        path_to_bdf.setText("BDF name");
        ResultInformation.clear();
    }

    @FXML
    private void openResultDialog() {
        FXMLLoader myFxmlLoader = null;
        Parent myParent = null;
        try {
            myFxmlLoader = new FXMLLoader(App.class.getResource("result.fxml"));
            myParent = myFxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultDialogController resultDialogController = myFxmlLoader.getController();
        resultDialogController.prepareResultWindow();
        Scene scene = new Scene(myParent, 550, 320);
        Stage stage = new Stage();
        stage.setTitle("1D Flow Nastran Creator");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
