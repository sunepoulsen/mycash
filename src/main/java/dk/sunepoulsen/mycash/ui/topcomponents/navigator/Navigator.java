package dk.sunepoulsen.mycash.ui.topcomponents.navigator;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class Navigator extends AnchorPane {
    private BackendConnection backendConnection;

    @FXML
    private TreeView<NavigatorNode> treeView;

    public Navigator() {
        this.backendConnection = null;

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

        Registry.getDefault().getCurrentBackendConnectionProperty().addListener( this::reload );

        treeView.setCellFactory( view -> new NavigatorTreeCell() );
        treeView.setShowRoot( false );
        treeView.setEditable( false );
        treeView.getSelectionModel().setSelectionMode( SelectionMode.SINGLE );

        reload();
    }

    public void reload() {
        if( backendConnection != null ) {
            treeView.setRoot( new RootTreeItem( backendConnection.servicesFactory().newAccountingService() ) );
        }
        else {
            treeView.setRoot( null );
        }
    }

    private void reload( ObservableValue<? extends BackendConnection> observable, BackendConnection oldValue, BackendConnection newValue ) {
        backendConnection = newValue;
        reload();
    }

}
