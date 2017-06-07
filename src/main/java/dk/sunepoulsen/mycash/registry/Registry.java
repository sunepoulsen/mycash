//-----------------------------------------------------------------------------
package dk.sunepoulsen.mycash.registry;

//-----------------------------------------------------------------------------

import javafx.stage.Stage;
import lombok.Getter;

/**
 * Created by sunepoulsen on 21/10/2016.
 */
public class Registry {
    //-------------------------------------------------------------------------
    //              Constructors
    //-------------------------------------------------------------------------

    public Registry() {
        this.uiRegistry = new UIRegistry();
    }

    public void initialize( final Stage primaryStage ) {
        this.uiRegistry.initialize( primaryStage );
    }

    public void shutdown() {
        this.uiRegistry.shutdown();
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
    private UIRegistry uiRegistry;
}
