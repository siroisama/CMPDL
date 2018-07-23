package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.CMPDL;
import com.github.franckyi.cmpdl.ControllerView;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StructureController implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    private JFXTabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab tab = new Tab();
        tab.setContent(CMPDL.menu.getView());
        tabPane.getTabs().add(tab);
    }

    public static StructureController get() {
        return CMPDL.structure.getController();
    }

    public void clickMinimize(MouseEvent event) {
        CMPDL.stage.setIconified(true);
    }

    public void clickClose(MouseEvent event) {
        Platform.exit();
    }

    public void next(ControllerView<? extends IContentController> content) {
        int currentTab = tabPane.getSelectionModel().getSelectedIndex();
        Tab tab = new Tab();
        tab.setContent(content.getView());
        if (tabPane.getTabs().size() == currentTab + 1) {
            tabPane.getTabs().add(tab);
        } else {
            tabPane.getTabs().set(currentTab + 1, tab);
        }
        CMPDL.current = content;
        next();
    }

    public void next() {
        tabPane.getSelectionModel().selectNext();
    }

    public void previous() {
        int currentTab = tabPane.getSelectionModel().getSelectedIndex();
        if (currentTab == tabPane.getTabs().size() - 2) {
            tabPane.getTabs().remove(currentTab + 1);
        }
        tabPane.getSelectionModel().selectPrevious();
    }

}
