//-----------------------------------------------------------------------------
package dk.sunepoulsen.mycash.registry;

//-----------------------------------------------------------------------------

import dk.sunepoulsen.mycash.projects.AccountingProject;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

        this.noCurrentProjectWrapper = new ReadOnlyBooleanWrapper( true );
        this.currentProjectProperty = new SimpleObjectProperty<>();
        this.currentProjectProperty.addListener( this::disconnectCurrentAccountingProject );
        this.currentProjectProperty.addListener( this::updateNoCurrentProjectWrapper );
    }

    public void initialize( final Stage primaryStage ) {
        this.uiRegistry.initialize( primaryStage );
    }

    public void shutdown() {
        this.uiRegistry.shutdown();
        disconnectCurrentAccountingProject( currentProjectProperty, currentProjectProperty.get(), currentProjectProperty.get() );
    }

    //-------------------------------------------------------------------------
    //              Properties
    //-------------------------------------------------------------------------

    public ReadOnlyBooleanProperty getNoCurrentProjectProperty() {
        return this.noCurrentProjectWrapper.getReadOnlyProperty();
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

    private void disconnectCurrentAccountingProject( ObservableValue<? extends AccountingProject> observable, AccountingProject oldValue, AccountingProject newValue ) {
        if( oldValue != null && oldValue.isOpen() ) {
            oldValue.disconnect();
        }
    }

    private void updateNoCurrentProjectWrapper( ObservableValue<? extends AccountingProject> observable, AccountingProject oldValue, AccountingProject newValue ) {
        noCurrentProjectWrapper.set( newValue == null );
    }

    //-------------------------------------------------------------------------
    //              Private members
    //-------------------------------------------------------------------------

    private static Registry global;

    /**
     * This project holds the currently opened Accounting Project.
     */
    @Getter
    private SimpleObjectProperty<AccountingProject> currentProjectProperty;

    private ReadOnlyBooleanWrapper noCurrentProjectWrapper;

    @Getter
    @Setter
    private Locale locale;

    @Getter
    private UIRegistry uiRegistry;
}
