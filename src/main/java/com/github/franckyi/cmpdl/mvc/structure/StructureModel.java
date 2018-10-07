package com.github.franckyi.cmpdl.mvc.structure;

import com.github.franckyi.mvcjfx.IModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

public class StructureModel implements IModel {

    private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

    public StructureModel() {
    }

    public Node getContent() {
        return content.get();
    }

    public void setContent(Node content) {
        this.content.set(content);
    }

    public ObjectProperty<Node> contentProperty() {
        return content;
    }
}
