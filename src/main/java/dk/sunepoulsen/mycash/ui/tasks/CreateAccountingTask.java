package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.ui.model.Accounting;

/**
 * Task to create a new Accounting in an BackendConnection
 */
public class CreateAccountingTask extends AccountingProjectTask<Void> {
    private final Accounting model;

    public CreateAccountingTask( BackendConnection connection, Accounting model ) {
        super( connection );
        this.model = model;
    }

    @Override
    protected Void call() throws Exception {
        connection.servicesFactory().newAccountingService().create( model );
        return null;
    }
}
