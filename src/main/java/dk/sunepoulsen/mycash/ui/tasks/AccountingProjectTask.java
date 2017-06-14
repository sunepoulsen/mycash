package dk.sunepoulsen.mycash.ui.tasks;

import dk.sunepoulsen.mycash.projects.AccountingProject;
import javafx.concurrent.Task;

/**
 * Created by sunepoulsen on 12/06/2017.
 */
public abstract class AccountingProjectTask<T> extends Task<T> {
    protected final AccountingProject project;

    public AccountingProjectTask( AccountingProject project ) {
        this.project = project;
    }
}
