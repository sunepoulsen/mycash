package dk.sunepoulsen.mycash.ui.topcomponents.contentpanes;

import dk.sunepoulsen.mycash.ui.model.Accounting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;

/**
 * Created by sunepoulsen on 16/06/2017.
 */
@XSlf4j
public class AccountsPane extends AnchorPane {
    private Accounting accounting;

    public AccountsPane( Accounting accounting ) {
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
