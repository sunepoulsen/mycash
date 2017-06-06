package dk.sunepoulsen.mycash.ui.topcomponents.contentpanes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class ContentPanes extends AnchorPane {
    public ContentPanes() {
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
    }
}
