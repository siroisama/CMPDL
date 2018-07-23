package com.github.franckyi.cmpdl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ControllerView<T> {

    private T controller;
    private Parent view;

    private ControllerView() {
    }

    public static <T> ControllerView<T> of(String str) throws IOException {
        FXMLLoader loader = new FXMLLoader(CMPDL.class.getResource(str));
        ControllerView<T> controllerView = new ControllerView<>();
        controllerView.setView(loader.load());
        controllerView.setController(loader.getController());
        return controllerView;
    }

    public T getController() {
        return controller;
    }

    private void setController(T controller) {
        this.controller = controller;
    }

    public Parent getView() {
        return view;
    }

    private void setView(Parent view) {
        this.view = view;
    }

}
