package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.CMPDL;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable, IContentController {

    @FXML
    private VBox root;

    public void initialize(URL location, ResourceBundle resources) {

    }

    public static MenuController get() {
        return CMPDL.menu.getController();
    }

    public void actionAbout(ActionEvent event) {
        CMPDL.alert("About", "Created by Franckyi").show();
    }

    public void actionClose(ActionEvent event) {
        Platform.exit();
    }

    public void actionImportURL(ActionEvent event) {
        next(CMPDL.importURL);
    }

    public void actionImportID(ActionEvent event) {
        next(CMPDL.importID);
    }

    public void actionImportFile(ActionEvent event) {
        next(CMPDL.importFile);
    }

    @Override
    public void actionPrevious(ActionEvent event) {
    }

    @Override
    public void actionNext(ActionEvent event) {
    }
}
