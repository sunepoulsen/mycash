package dk.sunepoulsen.mycash.registry;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
public class Settings {
    private Properties settings;

    public Settings() {
        this.settings = new Properties();
    }

    public String getShortDateFormat() {
        return settings.getProperty( "short.date.format" );
    }

    public void loadSettings() throws IOException {
        settings.load( getClass().getResourceAsStream( "/application.properties" ) );
    }
}
