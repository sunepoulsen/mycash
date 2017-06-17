package dk.sunepoulsen.mycash.ui.topcomponents.accountspane;

import dk.sunepoulsen.mycash.backend.services.AccountingService;
import dk.sunepoulsen.mycash.ui.model.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.XSlf4j;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
@XSlf4j
public class AccountsPaneRootTreeItem extends TreeItem<Account> {
    private boolean invalidated = true;
    private AccountingService service;

    public AccountsPaneRootTreeItem( AccountingService service ) {
        this.invalidated = true;
        this.service = service;
    }

    @Override
    public boolean isLeaf() {
        loadAndSetChildrenIfInvalidated();
        return super.isLeaf();
    }

    @Override
    public ObservableList<TreeItem<Account>> getChildren() {
        loadAndSetChildrenIfInvalidated();
        return super.getChildren();
    }

    private void loadAndSetChildrenIfInvalidated() {
        if( invalidated ) {
            super.getChildren().setAll( loadChildren() );
            invalidated = false;
        }
    }

    private ObservableList<TreeItem<Account>> loadChildren() {
        return FXCollections.emptyObservableList();
    }
}
