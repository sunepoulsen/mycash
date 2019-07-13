package dk.sunepoulsen.mycash;

import dk.sunepoulsen.adopt.core.environment.Environment;
import dk.sunepoulsen.adopt.core.environment.EnvironmentException;
import dk.sunepoulsen.adopt.javafx.application.AdoptJavaFXApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application class.
 */
public class MyCashApplication extends AdoptJavaFXApplication {
    private static Logger log = LoggerFactory.getLogger( MyCashApplication.class );

    /**
     * Main function.
     *
     * @param args Arguments from the command line.
     */
    public static void main( String[] args ) throws EnvironmentException {
        Environment env = new Environment();

        log.info( "Starting {} version {}", env.getProperty( "application.name", String.class, "unknown" ), env.getProperty( "application.version", String.class, "unknown" ) );
        launchApplication( MyCashApplication.class, args );
    }
}
