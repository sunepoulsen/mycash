package dk.sunepoulsen.mycash.ui.topcomponents.toppanel;

import dk.sunepoulsen.mycash.registry.Registry;
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
public class TopPanel extends AnchorPane {
    public TopPanel() {
        Registry registry = Registry.getDefault();

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "toppanel.fxml" ) );
        fxmlLoader.setRoot( this );
        fxmlLoader.setController( this );
        fxmlLoader.setResources( registry.getBundle( getClass() ) );

        try {
            fxmlLoader.load();
        }
        catch( IOException exception ) {
            throw new RuntimeException( exception );
        }
    }

    @FXML
    public void initialize() {
        log.info( "Initializing TopPanel custom control" );
    }
}
