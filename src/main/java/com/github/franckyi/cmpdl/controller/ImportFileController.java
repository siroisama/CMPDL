package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.CMPDL;
import com.github.franckyi.cmpdl.tasks.validation.ValidationUtils;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportFileController implements Initializable, IContentController {

    @FXML
    private Label fileLabel;

    @FXML
    private JFXTextField fileField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileLabel.setLabelFor(fileField);
        fileField.setValidators(ValidationUtils.requiredFieldValidator());
    }

    @Override
    public void actionPrevious(ActionEvent event) {
        fileField.setText("");
        fileField.resetValidation();
        previous();
    }

    @Override
    public void actionNext(ActionEvent event) {

    }

    public void actionChoose(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Modpack ZIP file", "*.zip"));
        chooser.setTitle("Choose a modpack ZIP file :");
        File file = chooser.showOpenDialog(CMPDL.stage);
        if (file != null) {
            fileField.setText(file.getAbsolutePath());
        }
    }
}
