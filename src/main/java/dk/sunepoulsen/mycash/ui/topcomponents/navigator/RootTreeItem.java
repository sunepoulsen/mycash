package dk.sunepoulsen.mycash.ui.topcomponents.navigator;

import dk.sunepoulsen.mycash.backend.services.AccountingService;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import dk.sunepoulsen.mycash.ui.model.AccountsNode;
import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.XSlf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
@XSlf4j
public class RootTreeItem extends TreeItem<NavigatorNode> {
    private boolean invalidated = true;
    private AccountingService service;

    public RootTreeItem( AccountingService service ) {
        this.invalidated = true;
        this.service = service;
    }

    @Override
    public boolean isLeaf() {
        loadAndSetChildrenIfInvalidated();
        return super.isLeaf();
    }

    @Override
    public ObservableList<TreeItem<NavigatorNode>> getChildren() {
        loadAndSetChildrenIfInvalidated();
        return super.getChildren();
    }

    private void loadAndSetChildrenIfInvalidated() {
        if( invalidated ) {
            super.getChildren().setAll( loadChildren() );
            invalidated = false;
        }
    }

    private ObservableList<TreeItem<NavigatorNode>> loadChildren() {
        log.info( "Fetching children to {}", getClass().getSimpleName() );
        List<Accounting> accountings = service.findAllAccountings();

        log.info( "Constructing TreeItems for {} accountings", accountings.size() );
        return FXCollections.observableList( accountings.stream()
                .map( node -> {
                    TreeItem<NavigatorNode> item = new TreeItem<>( node );

                    ObservableList<TreeItem<NavigatorNode>> children = FXCollections.observableArrayList();
                    children.add( new TreeItem<>( new AccountsNode( node ) ) );

                    item.getChildren().setAll( children );
                    return item;
                } )
                .collect( Collectors.toList() )
        );
    }
}
