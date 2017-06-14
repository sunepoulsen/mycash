package dk.sunepoulsen.mycash.application;

import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.mainwindow.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.XSlf4j;

import java.util.Locale;

/**
 * Application class of MyCash
 */
@XSlf4j
public class MyCashApplication extends Application {
    private Registry registry = Registry.getDefault();

    /**
     * Starts the JavaFX application.
     * <p>
     *     This method is called automatically by JavaFX.
     * </p>
     * <p>
     *     It will setup the application and main window:
     *     <ul>
     *         <li>Initializing the global registry.</li>
     *         <li>Initialize the main window from fxml</li>
     *         <li>Maximize the main window to the primary screen</li>
     *     </ul>
     * </p>
     */
    @Override
    public void start( final Stage primaryStage ) throws Exception {
        Locale.setDefault( new Locale( "en", "DK" ) );

        registry.initialize( primaryStage );
        log.info( "Using locale: {}", registry.getLocale() );

        Parent root = FXMLLoader.load( MainWindow.class.getResource( "mainwindow.fxml" ) );
        Scene scene = new Scene( root );

        primaryStage.setTitle( "MyCash" );
        primaryStage.setScene( scene );

        maximizeStage( primaryStage );
        primaryStage.show();
    }

    /**
     * Called when the application is about to stop executing.
     * <p>
     *     Extra steps:
     *     <ul>
     *         <li>Shutdown of the global registry</li>
     *     </ul>
     * </p>
     *
     * @throws Exception Not used by this method.
     */
    @Override
    public void stop() throws Exception {
        registry.shutdown();
        super.stop();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        launch( args );
    }

    /**
     * Maximizes a stage to the size of the primary screen.
     *
     * @param stage The stage to maximize.
     */
    private void maximizeStage( final Stage stage ) {
        final Screen screen = Screen.getPrimary();

        stage.setX( screen.getVisualBounds().getMinX() );
        stage.setY( screen.getVisualBounds().getMinY() );
        stage.setWidth( screen.getVisualBounds().getWidth() );
        stage.setHeight( screen.getVisualBounds().getHeight() );
    }
}
