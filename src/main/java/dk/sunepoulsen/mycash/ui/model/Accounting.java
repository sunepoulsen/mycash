package dk.sunepoulsen.mycash.ui.model;

import dk.sunepoulsen.mycash.ui.model.api.NavigatorNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by sunepoulsen on 11/06/2017.
 */
@Data
public class Accounting implements NavigatorNode {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Override
    public String displayText() {
        return name;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public ObservableList<NavigatorNode> getChildren() {
        return FXCollections.emptyObservableList();
    }
}
