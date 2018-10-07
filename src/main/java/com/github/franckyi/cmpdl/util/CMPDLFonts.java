package com.github.franckyi.cmpdl.util;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public final class CMPDLFonts {

    private static final ObservableValue<Font> defaultFont = new Builder().build();

    public static ObservableValue<Font> defaultFont() {
        return defaultFont;
    }

    public static class Builder {
        private String family = "Roboto";
        private FontWeight weight = FontWeight.NORMAL;
        private FontPosture posture = FontPosture.REGULAR;
        private double size = 14;
        private Paint color = Color.BLACK;

        public Builder family(String family) {
            this.family = family;
            return this;
        }

        public Builder weight(FontWeight weight) {
            this.weight = weight;
            return this;
        }

        public Builder posture(FontPosture posture) {
            this.posture = posture;
            return this;
        }

        public Builder size(double size) {
            this.size = size;
            return this;
        }

        public ObservableValue<Font> build() {
            return new SimpleObjectProperty<>(Font.font(family, weight, posture, size));
        }
    }

}
