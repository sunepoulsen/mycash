package dk.sunepoulsen.mycash.ui.model;

import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by sunepoulsen on 16/06/2017.
 */
public abstract class LeafNavigatorNode implements NavigatorNode {
    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public ObservableList<NavigatorNode> getChildren() {
        return FXCollections.emptyObservableList();
    }
}
