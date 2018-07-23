package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.CMPDL;
import com.github.franckyi.cmpdl.tasks.validation.ModpackURLValidationTask;
import com.github.franckyi.cmpdl.tasks.validation.ValidationUtils;
import com.github.franckyi.cmpdl.validation.ModpackURLValidator;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportURLController implements Initializable, IContentController {

    @FXML
    private Label urlLabel;

    @FXML
    private JFXTextField urlField;

    private int projectId = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        urlLabel.setLabelFor(urlField);
        urlField.setValidators(ValidationUtils.requiredFieldValidator(), new ModpackURLValidator());
        urlField.textProperty().addListener((observable, oldValue, newValue) -> projectId = -1);
    }

    public void actionLinkMcCurseForge(ActionEvent event) {
        open("https://minecraft.curseforge.com/");
    }

    public void actionLinkCurseForge(ActionEvent event) {
        open("https://www.curseforge.com/");
    }

    public void actionLinkFTB(ActionEvent event) {
        open("https://www.feed-the-beast.com/");
    }

    private void open(String s) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(s));
            } catch (Exception e) {
                e.printStackTrace();
                CMPDL.alert(e).show();
            }
        } else {
            CMPDL.alert("Error", "Hyperlinks are not supported on this platform.").show();
        }
    }

    public void actionVerify(ActionEvent event) {
        verify(false);
    }

    @Override
    public void actionPrevious(ActionEvent event) {
        urlField.setText("");
        urlField.resetValidation();
        previous();
    }

    @Override
    public void actionNext(ActionEvent event) {
        if (projectId < 0) {
            verify(true);
        } else {
            // TODO url
        }
    }

    private void verify(boolean andNext) {
        urlField.resetValidation();
        urlField.validate();
        if (urlField.getValidators().stream().noneMatch(ValidatorBase::getHasErrors)) {
            JFXAlert<Void> alert = CMPDL.alert("Please wait", "Verifying...");
            ModpackURLValidationTask task = new ModpackURLValidationTask(urlField.getText());
            task.setOnSucceeded(event1 -> {
                alert.hideWithAnimation();
                ModpackURLValidationTask.ModpackURLValidationResult res = task.getValue();
                if (res.isError()) {
                    CMPDL.alert("Error", res.getErrorMsg()).show();
                } else {
                    projectId = res.getProjectId();
                    if (andNext) {
                        actionNext(null);
                    } else {
                        CMPDL.alert("Success", "The URL is valid !").show();
                    }
                }
            });
            alert.show();
            CMPDL.EXECUTOR.submit(task);
        }
    }

    public static ImportURLController get() {
        return CMPDL.importURL.getController();
    }

}
