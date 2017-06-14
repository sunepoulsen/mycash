package dk.sunepoulsen.mycash.ui.model.api;

import javafx.collections.ObservableList;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
public interface NavigatorNode {
    String displayText();

    boolean isLeaf();
    ObservableList<NavigatorNode> getChildren();
}
