package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.projects.AccountingProject;
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
            AccountingProject project = Registry.getDefault().getCurrentProjectProperty().get();
            project.disconnect();
            log.info( "Closed accounting project in directory: {}", project.getDirectory().getAbsolutePath() );

            return null;
        }
        catch( Exception ex ) {
            log.warn( "An error occurred while creating/opening accounting project" );
            log.catching( ex );

            return null;
        }
    }

    @Override
    protected void succeeded() {
        Registry.getDefault().getCurrentProjectProperty().set( null );
        super.succeeded();
    }
}
