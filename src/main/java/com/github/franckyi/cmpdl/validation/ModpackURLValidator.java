package com.github.franckyi.cmpdl.validation;

import com.github.franckyi.cmpdl.CMPDL;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;

import java.net.MalformedURLException;
import java.net.URL;

public class ModpackURLValidator extends ValidatorBase {

    public ModpackURLValidator() {
        super();
        setIcon(CMPDL.ICON_ERROR);
    }

    @Override
    protected void eval() {
        TextInputControl input = (TextInputControl) srcControl.get();
        try {
            URL url = new URL(input.getText());
            String host = url.getHost();
            if ("minecraft.curseforge.com".equalsIgnoreCase(host) || "www.curseforge.com".equalsIgnoreCase(host) || "www.feed-the-beast.com".equalsIgnoreCase(host)) {
                hasErrors.set(false);
            } else {
                setMessage("The URL host is invalid.");
                hasErrors.set(true);
            }
        } catch (MalformedURLException e) {
            setMessage("The URL is malformed.");
            hasErrors.set(true);
        }
    }
}