package com.github.franckyi.cmpdl;

import com.github.franckyi.cmpdl.mvc.menu.MenuController;
import com.github.franckyi.cmpdl.mvc.menu.MenuModel;
import com.github.franckyi.cmpdl.mvc.menu.MenuView;
import com.github.franckyi.cmpdl.mvc.structure.StructureController;
import com.github.franckyi.cmpdl.mvc.structure.StructureModel;
import com.github.franckyi.cmpdl.mvc.structure.StructureView;
import com.github.franckyi.mvcjfx.MVCJFXApp;
import com.github.franckyi.mvcjfx.Module;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CMPDLApp extends MVCJFXApp {

    private double xOffset = 0;
    private double yOffset = 0;

    private Scene scene;

    private Module<MenuModel, MenuView, MenuController> menu;
    private Module<StructureModel, StructureView, StructureController> structure;

    public static CMPDLApp getInstance() {
        return (CMPDLApp) MVCJFXApp.getInstance();
    }

    @Override
    public void init() throws Exception {
        super.init();
        structure = buildModule(new StructureModel(), StructureView::new, StructureController::new);
        menu = buildModule(new MenuModel(), MenuView::new, MenuController::new);
        structure.getModel().setContent(menu.getView().getRoot());
        structure.getView().getRoot().getTop().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        structure.getView().getRoot().getTop().setOnMouseDragged(event -> {
            getPrimaryStage().setX(event.getScreenX() - xOffset);
            getPrimaryStage().setY(event.getScreenY() - yOffset);
        });
    }

    @Override
    protected void initStage(Stage stage) {
        scene = new Scene(structure.getView().getRoot());
        scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Roboto:300,400,700");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("CMPDL");
        stage.setScene(scene);

        stage.show();
    }

}
