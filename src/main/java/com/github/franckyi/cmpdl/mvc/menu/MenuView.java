package com.github.franckyi.cmpdl.mvc.menu;

import com.github.franckyi.mvcjfx.View;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.scene.layout.VBox;

import static com.github.franckyi.builderfx.BuilderFX.*;
import static com.github.franckyi.builderfx.BuilderFontAwesomeFX.fontAwesomeIcon;
import static com.github.franckyi.cmpdl.util.CMPDLNodes.*;

public class MenuView extends View<MenuModel, VBox> {

    public MenuView(MenuModel model) {
        super(model);
    }

    @Override
    protected VBox buildRoot() {
        return vBox(10).addChildren(
            vBox(5).addChildren(
                label("CMPDL").font("", BOLD, 48),
                label("Curse Modpack Downloader").font("", BOLD, 24),
                label("v3.0.0").font("", BOLD, 16),
                label("by Franckyi").font("", BOLD, 16)
            ).align(CENTER).prefH(150),
            vBox(30).addChildren(
                vBox(10).addChildren(
                    label(" Search").font("", ITALIC, 24).graphic(
                        fontAwesomeIcon(FontAwesomeIcon.SEARCH).glyphSize(24)
                    ),
                    vBox(15).addChildren(
                        buttonText(" Search for modpacks").prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.SEARCH).glyphSize(16)
                        )
                    ).align(CENTER)
                ), vBox(10).addChildren(
                    label(" Import").font("", ITALIC, 24).graphic(
                        fontAwesomeIcon(FontAwesomeIcon.DOWNLOAD).glyphSize(24)
                    ),
                    vBox(15).addChildren(
                        buttonOutlined(" Import from URL").prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.LINK).glyphSize(16)
                        ),
                        buttonContained(" Import from ID").prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.DOWNLOAD).glyphSize(16)
                        ),
                        button(" Import from .zip file").font(16).prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.FILE_ZIP_ALT).glyphSize(16)
                        )
                    ).align(CENTER)
                ), vBox(10).addChildren(
                    label(" Settings").font("", ITALIC, 24).graphic(
                        fontAwesomeIcon(FontAwesomeIcon.COGS).glyphSize(24)
                    ),
                    vBox(15).addChildren(
                        button(" Settings").font(16).prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.COG).glyphSize(16)
                        ),
                        button(" About CMPDL").font(16).prefW(300).graphic(
                            fontAwesomeIcon(FontAwesomeIcon.INFO_CIRCLE).glyphSize(16)
                        )
                    ).align(CENTER)
                )
            )
        ).prefW(500).padding(20);
    }
}
