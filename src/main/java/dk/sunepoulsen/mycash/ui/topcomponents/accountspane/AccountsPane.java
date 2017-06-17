package dk.sunepoulsen.mycash.ui.topcomponents.accountspane;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.model.Account;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;

/**
 * Created by sunepoulsen on 16/06/2017.
 */
@XSlf4j
public class AccountsPane extends AnchorPane {
    private BackendConnection backendConnection;
    private Accounting accounting;

    @FXML
    private TreeTableView<Account> treeTableView;

    public AccountsPane( Accounting accounting ) {
        this.backendConnection = Registry.getDefault().getCurrentBackendConnectionProperty().getValue();
        this.accounting = accounting;

        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( "accountspane.fxml" ) );
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
