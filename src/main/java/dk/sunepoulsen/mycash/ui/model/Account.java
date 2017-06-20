package dk.sunepoulsen.mycash.ui.model;

import dk.sunepoulsen.mycash.db.entities.AccountType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by sunepoulsen on 17/06/2017.
 */
@Data
public class Account {
    private Long id;

    @NotNull
    private List<Long> accountings;

    private Long parentId;

    @NotNull
    private String no;

    @NotNull
    private String name;

    @NotNull
    private AccountType type;

    private String description;

    public Account() {
        this( null, null, null, null, null );
    }

    public Account( Long id, String no, String name, AccountType type, String description ) {
        this.id = id;
        this.accountings = null;

        this.no = no;
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
