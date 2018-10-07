package com.github.franckyi.cmpdl.util;

import com.github.franckyi.builderfx.impl.com.jfoenix.controls.JFXButtonDecorator;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.function.Consumer;

import static com.github.franckyi.builderfx.BuilderJFX.jfxButton;

public final class CMPDLNodes {

    public static final Paint PRIMARY_COLOR = Color.web("#6200EE");
    public static final Paint PRIMARY_COLOR_VARIANT = Color.web("#3700B3");
    public static final Paint SECONDARY_COLOR = Color.web("#03DAC6");
    public static final Paint SECONDARY_COLOR_VARIANT = Color.web("#018786");
    public static final Paint ERROR_COLOR = Color.web("#C51162");

    private static final Type DEFAULT = Type.PRIMARY_VARIANT;

    private static final Border border = new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.5), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT));

    private static final Consumer<JFXButtonDecorator> uppercase = node -> node.textProperty().addListener((observable, oldValue, newValue) -> node.setText(newValue.toUpperCase()));

    private static final ObservableValue<Font> BUTTON_FONT = new CMPDLFonts.Builder().size(16).weight(FontWeight.LIGHT).build();

    private static Background containedButtonBackground(Type type) {
        return new Background(new BackgroundFill(type.paint, new CornerRadii(5), Insets.EMPTY));
    }

    public static JFXButtonDecorator buttonText() {
        return buttonText(DEFAULT);
    }

    public static JFXButtonDecorator buttonText(String text) {
        return buttonText(text, DEFAULT);
    }

    public static JFXButtonDecorator buttonText(Type type) {
        return jfxButton().bindFontTo(BUTTON_FONT).also(uppercase).textFill(type.paint);
    }

    public static JFXButtonDecorator buttonText(String text, Type type) {
        return jfxButton(text.toUpperCase()).bindFontTo(BUTTON_FONT).also(uppercase).textFill(type.paint);
    }

    public static JFXButtonDecorator buttonOutlined() {
        return buttonOutlined(DEFAULT);
    }

    public static JFXButtonDecorator buttonOutlined(String text) {
        return buttonOutlined(text, DEFAULT);
    }

    public static JFXButtonDecorator buttonOutlined(Type type) {
        return buttonText(type).border(border);
    }

    public static JFXButtonDecorator buttonOutlined(String text, Type type) {
        return buttonText(text, type).border(border);
    }

    public static JFXButtonDecorator buttonContained() {
        return buttonContained(DEFAULT);
    }

    public static JFXButtonDecorator buttonContained(String text) {
        return buttonContained(text, DEFAULT);
    }

    public static JFXButtonDecorator buttonContained(Type type) {
        return jfxButton().bindFontTo(BUTTON_FONT).also(uppercase).textFill(Color.WHITE).background(containedButtonBackground(type));
    }

    public static JFXButtonDecorator buttonContained(String text, Type type) {
        return jfxButton(text.toUpperCase()).bindFontTo(BUTTON_FONT).textFill(Color.WHITE).also(uppercase).background(containedButtonBackground(type));
    }

    public enum Type {

        PRIMARY(PRIMARY_COLOR),
        PRIMARY_VARIANT(PRIMARY_COLOR_VARIANT),
        SECONDARY(SECONDARY_COLOR),
        SECONDARY_VARIANT(SECONDARY_COLOR_VARIANT),
        ERROR(ERROR_COLOR);

        Paint paint;

        Type(Paint paint) {
            this.paint = paint;
        }

    }

}
