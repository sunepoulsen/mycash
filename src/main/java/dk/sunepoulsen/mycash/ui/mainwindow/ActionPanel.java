package dk.sunepoulsen.mycash.ui.mainwindow;

import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.dialogs.AccountingDialog;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import dk.sunepoulsen.mycash.ui.tasks.CloseAccountingProjectTask;
import dk.sunepoulsen.mycash.ui.tasks.CreateAccountingTask;
import dk.sunepoulsen.mycash.ui.tasks.CreateOrOpenAccountingProjectTask;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/**
 * Created by sunepoulsen on 09/05/2017.
 */
@XSlf4j
public class ActionPanel extends AnchorPane implements Initializable {
    @FXML
    private MenuBar menuBar = null;

    @FXML
    private MenuItem closeFileMenuItem = null;

    @FXML
    private MenuItem newAccountingMenuItem = null;

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

        closeFileMenuItem.disableProperty().bind( registry.getNoBackendConnectionProperty() );
        newAccountingMenuItem.disableProperty().bind( registry.getNoBackendConnectionProperty() );
    }

    //-------------------------------------------------------------------------
    //              Event handlers
    //-------------------------------------------------------------------------

    /**
     * Handle action related to "File|New" menu item.
     *
     * @param event Event on "File|New" menu item.
     */
    @FXML
    private void newFile( final ActionEvent event ) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle( "Select an empty directory for a new Accounting Project" );
        File directory = chooser.showDialog( stage );

        if( directory != null && onTaskCreated != null ) {
            Task task = new CreateOrOpenAccountingProjectTask( directory );
            onTaskCreated.accept( task );
        }
    }

    /**
     * Handle action related to "File|Open" menu item.
     *
     * @param event Event on "File|Open" menu item.
     */
    @FXML
    private void openFile( final ActionEvent event ) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle( "Select a directory that contains a Accounting Project" );
        File directory = chooser.showDialog( stage );

        if( directory != null && onTaskCreated != null ) {
            Task task = new CreateOrOpenAccountingProjectTask( directory );
            onTaskCreated.accept( task );
        }
    }

    /**
     * Handle action related to "File|Close" menu item.
     *
     * @param event Event on "File|Close" menu item.
     */
    @FXML
    private void closeFile( final ActionEvent event ) {
        if( onTaskCreated != null ) {
            onTaskCreated.accept( new CloseAccountingProjectTask() );
        }
    }

    /**
     * Handle action related to "File|New" menu item.
     *
     * @param event Event on "File|New" menu item.
     */
    @FXML
    private void newAccounting( final ActionEvent event ) {
        AccountingDialog dialog = new AccountingDialog();
        Optional<Accounting> model = dialog.showAndWait();

        if( model.isPresent() && onTaskCreated != null ) {
            CreateAccountingTask task = new CreateAccountingTask( registry.getCurrentBackendConnectionProperty().get(), model.get() );
            task.setOnFailed( e -> {
                Alert alert = new Alert( Alert.AlertType.INFORMATION, task.getException().getMessage() );
                alert.setHeaderText( bundle.getString( "mainmenu.file" ) + "|" + bundle.getString( "mainmenu.file.new-accounting" ) );
                alert.show();
            } );

            onTaskCreated.accept( task );
        }
    }
}
