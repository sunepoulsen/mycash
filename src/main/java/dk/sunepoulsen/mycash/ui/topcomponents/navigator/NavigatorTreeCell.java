package dk.sunepoulsen.mycash.ui.topcomponents.navigator;

import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.scene.control.TreeCell;
import lombok.extern.slf4j.XSlf4j;

/**
 * Created by sunepoulsen on 14/05/2017.
 */
@XSlf4j
public class NavigatorTreeCell extends TreeCell<NavigatorNode> {
    public NavigatorTreeCell() {
    }

    @Override
    protected void updateItem( final NavigatorNode item, final boolean empty ) {
        super.updateItem( item, empty );

        if( empty || item == null ) {
            setText( null );
            setGraphic( null );
        }
        else {
            setText( item.displayText() );
        }
    }
}
