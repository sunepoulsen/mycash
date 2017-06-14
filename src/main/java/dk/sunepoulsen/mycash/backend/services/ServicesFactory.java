package dk.sunepoulsen.mycash.backend.services;

import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;

/**
 * Factory class to create service instances for an BackendConnection to access and update
 * the different concepts that makes an BackendConnection.
 */
public class ServicesFactory {
    private final ProjectDatabase database;

    public ServicesFactory( ProjectDatabase database ) {
        this.database = database;
    }

    public AccountingService newAccountingService() {
        return new AccountingService( this.database );
    }
}
