package dk.sunepoulsen.mycash.backend;

import dk.sunepoulsen.mycash.backend.services.ServicesFactory;
import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;
import liquibase.exception.LiquibaseException;
import lombok.Getter;
import lombok.extern.slf4j.XSlf4j;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class is the main entry to an Accounting Project
 *
 * <h3>Public interface</h3>
 *
 * From a public point of view this class has the following responsibilities:
 * <ul>
 *     <li>Define properties that can identify a connection on desk</li>
 *     <li>
 *         Provide factory methods to create service classes that can do operations on an Accounting Project.
 *     </li>
 * </ul>
 *
 * <h3>Implementation</h3>
 *
 *
 *
 */
@XSlf4j
public class BackendConnection {
    @Getter
    private File directory;
    private ProjectDatabase database;

    public BackendConnection( File directory ) {
        this.directory = directory;
        this.database = new ProjectDatabase( String.format( "jdbc:h2:%s/data", directory.getAbsolutePath() ) );
    }

    public void connect() throws BackendConnectionException {
        try {
            this.database.migrate();
            this.database.connect();
            log.info( "Creating/opening backend database connection in directory: {}", directory.getAbsolutePath() );
        }
        catch( IOException | SQLException | LiquibaseException ex ) {
            throw new BackendConnectionException( "Unable to connect to backend database connection in " + directory.getAbsolutePath(), ex );
        }
    }

    public void disconnect() {
        this.database.disconnect();
        log.info( "Closed accounting connection in directory: {}", directory.getAbsolutePath() );
    }

    public boolean isOpen() {
        return database.isOpen();
    }

    public ServicesFactory servicesFactory() {
        return new ServicesFactory( this.database );
    }
}
