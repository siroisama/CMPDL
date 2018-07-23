package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.tasks.validation.ValidationUtils;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ImportIDController implements Initializable, IContentController {

    @FXML
    private Label idLabel;

    @FXML
    private JFXTextField idField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idLabel.setLabelFor(idField);
        idField.setValidators(ValidationUtils.requiredFieldValidator(), ValidationUtils.numberValidator());
    }

    @Override
    public void actionPrevious(ActionEvent event) {
        idField.setText("");
        idField.resetValidation();
        previous();
    }

    @Override
    public void actionNext(ActionEvent event) {

    }

    public void actionVerify(ActionEvent event) {
        idField.validate();
    }
}
