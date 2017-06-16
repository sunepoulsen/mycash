package dk.sunepoulsen.mycash.ui.model.api;

import javafx.collections.ObservableList;

/**
 * Definition of model types that can be viewed in the navigation tree.
 */
public interface NavigatorNode {
    String displayText();

    boolean isLeaf();
    ObservableList<NavigatorNode> getChildren();
}
