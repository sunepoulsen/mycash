//-----------------------------------------------------------------------------
package dk.sunepoulsen.mycash.registry;

//-----------------------------------------------------------------------------

import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by sunepoulsen on 21/10/2016.
 */
public class Registry {
    //-------------------------------------------------------------------------
    //              Constructors
    //-------------------------------------------------------------------------

    public Registry() {
        this.uiRegistry = new UIRegistry();
        this.locale = Locale.getDefault();
    }

    public void initialize( final Stage primaryStage ) {
        this.uiRegistry.initialize( primaryStage );
    }

    public void shutdown() {
        this.uiRegistry.shutdown();
    }

    //-------------------------------------------------------------------------
    //              Resource Bundles
    //-------------------------------------------------------------------------

    public <T> ResourceBundle getBundle( Class<T> clazz ) {
        String baseName = clazz.getName().toLowerCase();
        return ResourceBundle.getBundle( baseName, locale );
    }

    //-------------------------------------------------------------------------
    //              Global registry
    //-------------------------------------------------------------------------

    public static synchronized Registry getDefault() {
        if( global == null ) {
            global = new Registry();
        }

        return global;
    }

    //-------------------------------------------------------------------------
    //              Private members
    //-------------------------------------------------------------------------

    private static Registry global;

    @Getter
    @Setter
    private Locale locale;

    @Getter
    private UIRegistry uiRegistry;
}
