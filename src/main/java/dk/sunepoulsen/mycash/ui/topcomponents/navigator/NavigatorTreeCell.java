package dk.sunepoulsen.mycash.ui.topcomponents.navigator;

import dk.sunepoulsen.mycash.ui.model.api.ContentPaneNode;
import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.scene.control.TreeCell;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.XSlf4j;

/**
 * Created by sunepoulsen on 14/05/2017.
 */
@XSlf4j
public class NavigatorTreeCell extends TreeCell<NavigatorNode> {
    private final Navigator navigator;

    public NavigatorTreeCell( Navigator navigator ) {
        this.navigator = navigator;

        setOnMouseClicked( this::activateCell );
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

    private void activateCell( MouseEvent mouseEvent ) {
        if( mouseEvent.getClickCount() == 2 ) {
            log.info( "Handle doubleClick on NavigatorTreeCell" );

            NavigatorTreeCell cell = (NavigatorTreeCell)mouseEvent.getSource();
            if( cell.getItem() instanceof ContentPaneNode ) {
                ContentPaneNode node = (ContentPaneNode)cell.getItem();

                navigator.getActivatedContentPaneNodeProperty().setValue( null );
                navigator.getActivatedContentPaneNodeProperty().setValue( node );
            }
        }
    }
}
