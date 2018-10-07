package com.github.franckyi.cmpdl.mvc.structure;

import com.github.franckyi.builderfx.impl.de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconViewDecorator;
import com.github.franckyi.cmpdl.CMPDLApp;
import com.github.franckyi.cmpdl.util.CMPDLFonts;
import com.github.franckyi.cmpdl.util.CMPDLNodes;
import com.github.franckyi.mvcjfx.View;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.function.Consumer;

import static com.github.franckyi.builderfx.BuilderFX.*;
import static com.github.franckyi.builderfx.BuilderFontAwesomeFX.icon;

public class StructureView extends View<StructureModel, BorderPane> {

    private static ObservableValue<Font> title = new CMPDLFonts.Builder().size(20).build();
    private static ObservableValue<Paint> titleFill = new SimpleObjectProperty<>(Color.WHITE);
    private static Consumer<MaterialDesignIconViewDecorator> iconTemplate = icon -> icon.glyphSize(24).fill(Color.WHITE).cursor(Cursor.HAND);

    public StructureView(StructureModel model) {
        super(model);
    }

    @Override
    protected BorderPane buildRoot() {
        return borderPane().top(
            borderPane().left(
                hBox(10).addChildren(
                    icon(MaterialDesignIcon.DOWNLOAD).glyphSize(32).fill(Color.WHITE),
                    label("CMPDL v3.0.0").bindFontTo(title).bindTextFillTo(titleFill)
                ).align(Pos.CENTER).padding(10)
            ).right(
                hBox(10).addChildren(
                    icon(MaterialDesignIcon.WINDOW_MINIMIZE).also(iconTemplate).onMouseClicked(event -> CMPDLApp.getInstance().getPrimaryStage().setIconified(true)),
                    icon(MaterialDesignIcon.WINDOW_CLOSE).also(iconTemplate).glyphSize(24).fill(Color.WHITE).cursor(Cursor.HAND).onMouseClicked(event -> Platform.exit())
                ).align(Pos.CENTER).padding(10)
            ).prefH(50).background(new Background(new BackgroundFill(CMPDLNodes.PRIMARY_COLOR, CornerRadii.EMPTY, Insets.EMPTY)))
        );
    }

}
