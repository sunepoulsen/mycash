package dk.sunepoulsen.mycash.ui.topcomponents.navigator;

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
public class Navigator extends AnchorPane {
    @FXML
    private TreeView<String> treeView;

    public Navigator() {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "navigator.fxml" ) );
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
        log.info( "Initializing Navigator custom control" );
    }
}
