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
public class CreateOrOpenAccountingProjectTask extends Task<Void> {
    private File directory;
    private AccountingProject accountingProject;

    public CreateOrOpenAccountingProjectTask( File directory ) {
        this.directory = directory;
        this.accountingProject = null;
    }

    @Override
    protected Void call() throws Exception {
        try {
            AccountingProject project = new AccountingProject( this.directory );
            project.connect();
            log.info( "Creating/opening accounting project in directory: {}", directory.getAbsolutePath() );

            // Store the project on the instance so we can register it in the registry when the task succeed.
            this.accountingProject = project;

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
        Registry.getDefault().getCurrentProjectProperty().set( accountingProject );
        super.succeeded();
    }
}
