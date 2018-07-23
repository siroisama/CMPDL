package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.CMPDL;
import javafx.event.ActionEvent;

public class NavigationController {

    public void actionPrevious(ActionEvent event) {
        if (CMPDL.current != null && CMPDL.current.getController() != null) {
            CMPDL.current.getController().actionPrevious(event);
        }
    }

    public void actionNext(ActionEvent event) {
        if (CMPDL.current != null && CMPDL.current.getController() != null) {
            CMPDL.current.getController().actionNext(event);
        }
    }

}
