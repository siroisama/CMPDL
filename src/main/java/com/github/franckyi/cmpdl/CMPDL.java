package com.github.franckyi.cmpdl;

import com.github.franckyi.cmpdl.controller.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class CMPDL extends Application {

    public static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    public static final Gson GSON = new GsonBuilder().create();
    public static final FontAwesomeIconView ICON_ERROR = new FontAwesomeIconView(FontAwesomeIcon.EXCLAMATION_TRIANGLE);

    private double xOffset, yOffset;

    public static Stage stage;
    public static Scene scene;

    public static ControllerView<MenuController> menu;
    public static ControllerView<ImportURLController> importURL;
    public static ControllerView<ImportIDController> importID;
    public static ControllerView<ImportFileController> importFile;

    public static ControllerView<NavigationController> navigation;
    public static ControllerView<StructureController> structure;

    public static ControllerView<? extends IContentController> current;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        menu = ControllerView.of("/fxml/Menu.fxml");
        importURL = ControllerView.of("/fxml/ImportURL.fxml");
        importID = ControllerView.of("/fxml/ImportID.fxml");
        importFile = ControllerView.of("/fxml/ImportFile.fxml");
        navigation = ControllerView.of("/fxml/Navigation.fxml");
        structure = ControllerView.of("/fxml/Structure.fxml");
        current = menu;
        scene = new Scene(structure.getView());
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        //scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto:400,700");
        scene.setOnMousePressed(event -> {
            xOffset = stage.getX() - event.getScreenX();
            yOffset = stage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        });
        stage.setScene(scene);
        stage.setTitle("CMPDL");
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @Override
    public void stop() {
        EXECUTOR.shutdown();
    }

    public static JFXAlert<Void> alert(String header, String body) {
        JFXDialogLayout layout = new JFXDialogLayout();
        Label h = new Label(header);
        h.setFont(Font.font("Roboto", FontWeight.BOLD, 18));
        Label b = new Label(body);
        b.setFont(Font.font("Roboto", 16));
        JFXButton button = new JFXButton("CLOSE");
        button.setFont(Font.font("Roboto", 16));
        button.getStyleClass().add("btn-text-primary");
        layout.setHeading(h);
        layout.setBody(b);
        layout.setActions(button);
        JFXAlert<Void> alert = new JFXAlert<>(CMPDL.stage);
        alert.setOverlayClose(false);
        alert.setAnimation(JFXAlertAnimation.CENTER_ANIMATION);
        alert.setContent(layout);
        alert.initModality(Modality.NONE);
        button.setOnMouseClicked(event1 -> alert.hideWithAnimation());
        return alert;
    }

    public static JFXAlert<Void> alert(Throwable t) {
        return alert("Error : " + t.getClass().getName(), "Sorry, but an error has occured.\n\nMessage : " + t.getLocalizedMessage());
    }
}
