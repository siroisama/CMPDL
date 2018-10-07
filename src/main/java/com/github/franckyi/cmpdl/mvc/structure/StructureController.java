package com.github.franckyi.cmpdl.mvc.structure;

import com.github.franckyi.mvcjfx.Controller;

public class StructureController extends Controller<StructureModel, StructureView> {

    public StructureController(StructureModel model, StructureView view) {
        super(model, view);
    }

    @Override
    protected void init() {
        getModel().contentProperty().bindBidirectional(getView().getRoot().centerProperty());
    }
}
