package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.registry.Registry;
import javafx.concurrent.Task;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;

/**
 * Created by sunepoulsen on 18/05/2017.
 */
@XSlf4j
public class CreateOrOpenAccountingProjectTask extends Task<Void> {
    private File directory;
    private BackendConnection connection;

    public CreateOrOpenAccountingProjectTask( File directory ) {
        this.directory = directory;
        this.connection = null;
    }

    @Override
    protected Void call() throws Exception {
        try {
            BackendConnection project = new BackendConnection( this.directory );
            project.connect();

            // Store the connection on the instance so we can register it in the registry when the task succeed.
            this.connection = project;

            return null;
        }
        catch( Exception ex ) {
            log.warn( "An error occurred while creating/opening accounting connection" );
            log.catching( ex );

            return null;
        }
    }

    @Override
    protected void succeeded() {
        Registry.getDefault().getCurrentBackendConnectionProperty().set( connection );
        super.succeeded();
    }
}
