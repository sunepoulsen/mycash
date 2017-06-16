package dk.sunepoulsen.mycash.ui.model.api;

import javafx.scene.control.Tab;

/**
 * Definition of model types that can be viewed in a pane in the content viewer.
 */
public interface ContentPaneNode extends NavigatorNode {
    /**
     * Unique id of this content pane node.
     */
    String getId();

    /**
     * Creates a new Tab for a TabPane that contains the content of this node.
     *
     * @return The Tab that will be inserted in the ContentPanes.
     */
    Tab createTab();
}
