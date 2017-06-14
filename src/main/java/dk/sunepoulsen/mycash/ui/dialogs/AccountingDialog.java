package dk.sunepoulsen.mycash.ui.dialogs;

import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.ControlUtils;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by sunepoulsen on 11/06/2017.
 */
public class AccountingDialog extends GridPane implements Initializable {
    private Accounting model;

    @FXML
    private TextField nameField = null;

    @FXML
    private DatePicker startDate = null;

    @FXML
    private DatePicker endDate = null;

    private Dialog<Accounting> dialog = null;

    private Registry registry;
    private ResourceBundle bundle;

    public AccountingDialog() {
        this( new Accounting() );
    }

    public AccountingDialog( Accounting model ) {
        this.registry = Registry.getDefault();
        this.model = model;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "accountingdialog.fxml" ) );
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
        bundle = resources;

        dialog = new Dialog<>();
        dialog.setTitle( bundle.getString( "dialog.title.text" ) );
        dialog.setHeaderText( bundle.getString( "dialog.header.text" ) );

        dialog.getDialogPane().getButtonTypes().addAll( ButtonType.OK, ButtonType.CANCEL );
        dialog.getDialogPane().setContent( this );
        dialog.setResultConverter( this::convertControls );

        Node okButton = dialog.getDialogPane().lookupButton( ButtonType.OK );
        if( okButton != null ) {
            okButton.setDisable( true );
        }

        nameField.setText( model.getName() );
        nameField.textProperty().addListener( ( observable, oldValue, newValue ) -> disableButtons() );

        startDate.setValue( model.getStartDate() );
        startDate.editorProperty().getValue().textProperty().addListener( ( observable, oldValue, newValue ) -> disableButtons() );

        endDate.setValue( model.getEndDate() );
        endDate.editorProperty().getValue().textProperty().addListener( ( observable, oldValue, newValue ) -> disableButtons() );
    }

    public Optional<Accounting> showAndWait() {
        Platform.runLater( () -> {
            nameField.requestFocus();
            nameField.focusedProperty().addListener( ( observable, oldValue, newValue ) -> disableButtons() );
        } );

        return dialog.showAndWait();
    }

    private void disableButtons() {
        Node okButton = dialog.getDialogPane().lookupButton( ButtonType.OK );
        if( okButton != null ) {
            okButton.setDisable( ControlUtils.getText( nameField ).isEmpty()
                    || ControlUtils.getText( startDate ).isEmpty()
                    || ControlUtils.getText( endDate ).isEmpty()
            );
        }
    }

    private Accounting convertControls( ButtonType buttonType ) {
        if( !buttonType.equals( ButtonType.OK ) ) {
            return null;
        }

        Accounting accounting = new Accounting();
        accounting.setId( model.getId() );
        accounting.setName( nameField.getText() );
        accounting.setStartDate( startDate.getValue() );
        accounting.setEndDate( endDate.getValue() );

        return accounting;
    }
}
