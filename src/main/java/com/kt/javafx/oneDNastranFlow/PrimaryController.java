package com.kt.javafx.oneDNastranFlow;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
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

    MainController mainController = new MainController(new BDFReader(), new PlotelManipulator(), new PropertiesCreator());


    @FXML
    public void initialize() {
        input_choice_form_type.getItems().removeAll(input_choice_form_type.getItems());
        input_choice_form_type.getItems().addAll("0","1");

        input_choice_flag.getItems().removeAll(input_choice_flag.getItems());
        input_choice_flag.getItems().addAll("0","1");

        input_choice_surface_type.getItems().removeAll(input_choice_surface_type.getItems());
        input_choice_surface_type.getItems().addAll("FTUBE");

        input_choice_mat.getItems().removeAll(input_choice_mat.getItems());
        input_choice_mat.getItems().addAll("WATER 20C", "AIR 20C");

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
    private void clickSelectBdfButton() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Nastran Files", "*.bdf"));
        File file = fileChooser.showOpenDialog(stage);
        path_to_bdf.setText(file.getName());
    }

    @FXML
    private void clickRunButton() throws IOException {
        mainController.processFiles(input_offset.getText(),
                input_mass_flow_node_id.getText(),
                input_phbdy_property_id.getText(),
                input_diameter1.getText(),
                input_diameter2.getText(),
                input_pconv_property_id.getText(),
                input_mat_id.getText(),
                input_choice_form_type.getValue().toString(),
                input_choice_flag.getValue().toString());

        mainController.clear();
    }

}
