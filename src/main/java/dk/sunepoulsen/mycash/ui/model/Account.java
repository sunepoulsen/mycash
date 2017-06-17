package dk.sunepoulsen.mycash.ui.model;

import lombok.Data;

/**
 * Created by sunepoulsen on 17/06/2017.
 */
@Data
public class Account {
    private Long id;

    private String no;
    private String name;
    private AccountType type;
    private String description;

    public Account() {
        this( null, null, null, null, null );
    }

    public Account( Long id, String no, String name, AccountType type, String description ) {
        this.id = id;

        this.no = no;
        this.name = name;
        this.type = type;
        this.description = description;
    }
}
