package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import javafx.concurrent.Task;

/**
 * Created by sunepoulsen on 12/06/2017.
 */
public abstract class AccountingProjectTask<T> extends Task<T> {
    protected final BackendConnection connection;

    public AccountingProjectTask( BackendConnection connection ) {
        this.connection = connection;
    }
}
