package dk.sunepoulsen.mycash.ui.model;

import dk.sunepoulsen.mycash.ui.model.api.ContentPaneNode;
import dk.sunepoulsen.mycash.ui.topcomponents.accountspane.AccountsPane;
import javafx.scene.control.Tab;

/**
 * Created by sunepoulsen on 16/06/2017.
 */
public class AccountsNode implements ContentPaneNode {
    private Accounting accounting;

    public AccountsNode( Accounting accounting ) {
        this.accounting = accounting;
    }

    @Override
    public String displayText() {
        return "Accounts";
    }

    @Override
    public String getId() {
        return String.valueOf( accounting.getId() ) + ":Accounts";
    }

    @Override
    public Tab createTab() {
        Tab tab = new Tab();
        tab.setText( accounting.getName() + " - " + displayText() );
        tab.setContent( new AccountsPane( accounting ) );

        return tab;
    }
}
