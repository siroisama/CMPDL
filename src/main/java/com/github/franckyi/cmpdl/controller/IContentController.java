package com.github.franckyi.cmpdl.controller;

import com.github.franckyi.cmpdl.ControllerView;
import javafx.event.ActionEvent;

public interface IContentController {

    void actionPrevious(ActionEvent event);

    void actionNext(ActionEvent event);

    default void previous() {
        StructureController.get().previous();
    }

    default void next() {
        StructureController.get().next();
    }

    default void next(ControllerView<? extends IContentController> content) {
        StructureController.get().next(content);
    }

}
