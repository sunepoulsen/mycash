package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.projects.AccountingProject;
import dk.sunepoulsen.mycash.ui.model.Accounting;

/**
 * Task to create a new Accounting in an AccountingProject
 */
public class CreateAccountingTask extends AccountingProjectTask<Void> {
    private final Accounting model;

    public CreateAccountingTask( AccountingProject project, Accounting model ) {
        super( project );
        this.model = model;
    }

    @Override
    protected Void call() throws Exception {
        project.servicesFactory().newAccountingService().create( model );
        return null;
    }
}
