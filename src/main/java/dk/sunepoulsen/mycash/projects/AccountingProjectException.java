package dk.sunepoulsen.mycash.projects;

/**
 * Created by sunepoulsen on 09/06/2017.
 */
public class AccountingProjectException extends Exception {
    public AccountingProjectException( final String message, final Exception ex ) {
        super( message, ex );
    }
}
