package dk.sunepoulsen.mycash.projects.services;

import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;

/**
 * Factory class to create service instances for an AccountingProject to access and update
 * the different concepts that makes an AccountingProject.
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
