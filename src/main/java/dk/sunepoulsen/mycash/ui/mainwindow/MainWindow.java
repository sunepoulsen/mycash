package dk.sunepoulsen.mycash.ui.mainwindow;

import com.google.common.primitives.Doubles;
import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.topcomponents.contentpanes.ContentPanes;
import dk.sunepoulsen.mycash.ui.topcomponents.navigator.Navigator;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.XSlf4j;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

/**
 * Created by sunepoulsen on 10/05/2017.
 */
@XSlf4j
public class MainWindow implements Initializable {
    ExecutorService taskExecutorService;

    @FXML
    private ActionPanel actionPanel;

    @FXML
    private SplitPane splitPane;

    @FXML
    private Navigator navigator;

    @FXML
    private ContentPanes contentPanes;

    boolean splitPaneInitialized = false;

    public MainWindow() {
        this.taskExecutorService = Registry.getDefault().getUiRegistry().getTaskExecutorService();
    }

    @Override
    public void initialize( final URL location, final ResourceBundle resources ) {
        contentPanes.getActivatedContentPaneNodeProperty().bind( navigator.getActivatedContentPaneNodeProperty() );

        actionPanel.setNavigator( navigator );
        actionPanel.setOnTaskCreated( this::setupAndExecuteTask );
    }

    public void setupAndExecuteTask( Task task ) {
        taskExecutorService.submit( task );
    }

    public void initializeSplitPaneDividerPosition( WindowEvent windowEvent ) {
        log.debug( "Initialize divider position of SplitPane. Old positions: {}", splitPane.getDividerPositions() );

        if( windowEvent.getEventType().equals( WindowEvent.WINDOW_SHOWN ) && !splitPaneInitialized ) {
            double[] newPositions = { 0.20 };
            List<Double> userPositions = Registry.getDefault().getSettings().getUserStates().getDividerPositions();

            if( userPositions != null && !userPositions.isEmpty() ) {
                newPositions = Doubles.toArray( userPositions );
            }

            log.debug( "Set divider position to {}. Old value: {}", newPositions, splitPane.getDividerPositions() );
            splitPane.setDividerPositions( newPositions );
            splitPaneInitialized = true;
        }
    }

    public void storeSplitPaneDividerPosition( WindowEvent windowEvent ) {
        if( windowEvent.getEventType().equals( WindowEvent.WINDOW_CLOSE_REQUEST ) ) {
            log.debug( "Store divider positions of SplitPane: {}", splitPane.getDividerPositions() );

            List<Double> positions = Doubles.asList( splitPane.getDividerPositions() );
            Registry.getDefault().getSettings().getUserStates().setDividerPositions( positions );
        }
    }
}
