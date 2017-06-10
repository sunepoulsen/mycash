package dk.sunepoulsen.mycash.ui.mainwindow;

import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.tasks.CloseAccountingProjectTask;
import dk.sunepoulsen.mycash.ui.tasks.CreateOrOpenAccountingProjectTask;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class ActionPanel extends AnchorPane implements Initializable {
    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem closeProjectMenuItem;

    Registry registry;
    private ResourceBundle bundle;

    private Stage stage;

    @Setter
    private Consumer<Task> onTaskCreated;

    public ActionPanel() {
        this.registry = Registry.getDefault();
        this.stage = this.registry.getUiRegistry().getStage();

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

        closeProjectMenuItem.disableProperty().bind( registry.getNoCurrentProjectProperty() );
    }

    //-------------------------------------------------------------------------
    //              Event handlers
    //-------------------------------------------------------------------------

    /**
     * Handle action related to "File|New project" menu item.
     *
     * @param event Event on "File|New project" menu item.
     */
    @FXML
    private void newProject( final ActionEvent event ) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle( "Select an empty directory for a new Accounting Project" );
        File directory = chooser.showDialog( stage );

        if( directory != null && onTaskCreated != null ) {
            Task task = new CreateOrOpenAccountingProjectTask( directory );
            onTaskCreated.accept( task );
        }
    }

    /**
     * Handle action related to "File|Open project" menu item.
     *
     * @param event Event on "File|Open project" menu item.
     */
    @FXML
    private void openProject( final ActionEvent event ) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle( "Select a directory that contains a Accounting Project" );
        File directory = chooser.showDialog( stage );

        if( directory != null && onTaskCreated != null ) {
            Task task = new CreateOrOpenAccountingProjectTask( directory );
            onTaskCreated.accept( task );
        }
    }

    /**
     * Handle action related to "File|Close project" menu item.
     *
     * @param event Event on "File|Close project" menu item.
     */
    @FXML
    private void closeProject( final ActionEvent event ) {
        if( onTaskCreated != null ) {
            onTaskCreated.accept( new CloseAccountingProjectTask() );
        }
    }
}
