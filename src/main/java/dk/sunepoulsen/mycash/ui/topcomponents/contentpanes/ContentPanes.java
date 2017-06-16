package dk.sunepoulsen.mycash.ui.topcomponents.contentpanes;

import dk.sunepoulsen.mycash.ui.model.api.ContentPaneNode;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class ContentPanes extends AnchorPane {
    @Getter
    private SimpleObjectProperty<ContentPaneNode> activatedContentPaneNodeProperty;

    @FXML
    private TabPane panes;

    public ContentPanes() {
        this.activatedContentPaneNodeProperty = new SimpleObjectProperty<>();

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "contentpanes.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );

        try {
            fxmlLoader.load();
        }
        catch( IOException exception ) {
            throw new RuntimeException( exception );
        }
    }

    @FXML
    public void initialize() {
        log.info( "Initializing ContentPanes custom control" );

        activatedContentPaneNodeProperty.addListener( ( observable, oldValue, newValue ) -> {
            if( newValue != null ) {
                activateOrCreateContentPane( newValue );
            }
        } );
    }

    private void activateOrCreateContentPane( ContentPaneNode contentPaneNode ) {
        log.debug( "Activated contentPaneNode: {}", contentPaneNode.getId() );

        Optional<Tab> existingTab = panes.getTabs().stream().filter( tab -> tab.getId().equals( contentPaneNode.getId() ) ).findFirst();

        Tab activeTab;
        if( existingTab.isPresent() ) {
            activeTab = existingTab.get();
        }
        else {
            activeTab = contentPaneNode.createTab();
            activeTab.setId( contentPaneNode.getId() );

            panes.getTabs().add( activeTab );
        }

        panes.getSelectionModel().select( activeTab );
        activeTab.getContent().requestFocus();
    }
}
