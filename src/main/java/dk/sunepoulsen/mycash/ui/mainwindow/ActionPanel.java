package dk.sunepoulsen.mycash.ui.mainwindow;

import dk.sunepoulsen.mycash.registry.Registry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class ActionPanel extends AnchorPane implements Initializable {
    @FXML
    private MenuBar menuBar;

    private ResourceBundle bundle;

    public ActionPanel() {
        Registry registry = Registry.getDefault();

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "actionpanel.fxml" ) );
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

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        log.info( "Initializing ActionPanel custom control" );

        bundle = resources;
        menuBar.setUseSystemMenuBar( true );
    }

    //-------------------------------------------------------------------------
    //              Event handlers
    //-------------------------------------------------------------------------

    /**
     * Handle action related to "File|Import" menu item.
     *
     * @param event Event on "File|Import" menu item.
     */
    @FXML
    private void newProject( final ActionEvent event ) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION, bundle.getString( "action.not.implemented" ) );
        alert.setHeaderText( bundle.getString( "action.file.new-project" ) );
        alert.show();
    }
}
