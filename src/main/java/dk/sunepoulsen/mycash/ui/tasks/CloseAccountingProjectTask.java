package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.registry.Registry;
import javafx.concurrent.Task;
import lombok.extern.slf4j.XSlf4j;

/**
 * Created by sunepoulsen on 10/06/2017.
 */
@XSlf4j
public class CloseAccountingProjectTask extends Task<Void> {
    @Override
    protected Void call() throws Exception {
        try {
            BackendConnection connection = Registry.getDefault().getCurrentBackendConnectionProperty().get();
            connection.disconnect();

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
        Registry.getDefault().getCurrentBackendConnectionProperty().set( null );
        super.succeeded();
    }
}
