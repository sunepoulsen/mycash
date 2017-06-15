//-----------------------------------------------------------------------------
package dk.sunepoulsen.mycash.registry;

//-----------------------------------------------------------------------------

import dk.sunepoulsen.mycash.backend.BackendConnection;
import dk.sunepoulsen.mycash.settings.Settings;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.XSlf4j;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by sunepoulsen on 21/10/2016.
 */
@XSlf4j
public class Registry {
    //-------------------------------------------------------------------------
    //              Constructors
    //-------------------------------------------------------------------------

    public Registry() {
        this.uiRegistry = new UIRegistry();
        this.locale = null;
        this.settings = new Settings();

        this.noBackendConnectionWrapper = new ReadOnlyBooleanWrapper( true );
        this.currentBackendConnectionProperty = new SimpleObjectProperty<>();
        this.currentBackendConnectionProperty.addListener( this::disconnectCurrentBackendConnection );
        this.currentBackendConnectionProperty.addListener( this::updateNoBackendConnectionWrapper );
    }

    public void initialize( final Stage primaryStage ) throws IOException {
        this.uiRegistry.initialize( primaryStage );
        this.locale = Locale.getDefault();

        this.settings.loadSettings();
        this.settings.loadUserStates();
    }

    public void shutdown() {
        this.uiRegistry.shutdown();

        settings.getUserStates().setLastConnectionDirectory( null );
        if( currentBackendConnectionProperty.getValue() != null ) {
            settings.getUserStates().setLastConnectionDirectory( currentBackendConnectionProperty.getValue().getDirectory().getAbsolutePath() );
        }

        try {
            settings.storeUserStates();
        }
        catch( IOException ex ) {
            log.warn( "Unable to store user states", ex );
        }

        disconnectCurrentBackendConnection( currentBackendConnectionProperty, currentBackendConnectionProperty.get(), currentBackendConnectionProperty.get() );
    }

    //-------------------------------------------------------------------------
    //              Properties
    //-------------------------------------------------------------------------

    public ReadOnlyBooleanProperty getNoBackendConnectionProperty() {
        return this.noBackendConnectionWrapper.getReadOnlyProperty();
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
    //              Accounting Project
    //-------------------------------------------------------------------------

    private void disconnectCurrentBackendConnection( ObservableValue<? extends BackendConnection> observable, BackendConnection oldValue, BackendConnection newValue ) {
        if( oldValue != null && oldValue.isOpen() ) {
            oldValue.disconnect();
        }
    }

    private void updateNoBackendConnectionWrapper( ObservableValue<? extends BackendConnection> observable, BackendConnection oldValue, BackendConnection newValue ) {
        noBackendConnectionWrapper.set( newValue == null );
    }

    //-------------------------------------------------------------------------
    //              Private members
    //-------------------------------------------------------------------------

    private static Registry global;

    /**
     * This connection holds the currently opened Accounting Project.
     */
    @Getter
    private SimpleObjectProperty<BackendConnection> currentBackendConnectionProperty;

    private ReadOnlyBooleanWrapper noBackendConnectionWrapper;

    @Getter
    @Setter
    private Locale locale;

    @Getter
    @Setter
    private Settings settings;

    @Getter
    private UIRegistry uiRegistry;
}
