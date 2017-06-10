package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.projects.AccountingProject;
import dk.sunepoulsen.mycash.registry.Registry;
import javafx.concurrent.Task;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;

/**
 * Created by sunepoulsen on 18/05/2017.
 */
@XSlf4j
public class NewAccountingProjectTask extends Task<Void> {
    private File directory;
    private AccountingProject createdProject;

    public NewAccountingProjectTask( File directory ) {
        this.directory = directory;
        this.createdProject = null;
    }

    @Override
    protected Void call() throws Exception {
        try {
            AccountingProject project = new AccountingProject( this.directory );
            project.connect();
            log.info( "Created new accounting project in directory: {}", directory.getAbsolutePath() );

            // Store the project on the instance so we can register it in the registry when the task succeed.
            this.createdProject = project;

            return null;
        }
        catch( Exception ex ) {
            log.warn( "An error occurred doing creating new accounting project" );
            log.catching( ex );

            return null;
        }
    }

    @Override
    protected void succeeded() {
        Registry.getDefault().getCurrentProjectProperty().set( createdProject );
        super.succeeded();
    }
}
