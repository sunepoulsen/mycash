package dk.sunepoulsen.mycash.ui.mainwindow;

import dk.sunepoulsen.mycash.registry.Registry;
import dk.sunepoulsen.mycash.ui.topcomponents.navigator.Navigator;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.extern.slf4j.XSlf4j;

import java.net.URL;
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
    private Navigator navigator;

    public MainWindow() {
        this.taskExecutorService = Registry.getDefault().getUiRegistry().getTaskExecutorService();
    }

    @Override
    public void initialize( final URL location, final ResourceBundle resources ) {
        actionPanel.setNavigator( navigator );
        actionPanel.setOnTaskCreated( this::setupAndExecuteTask );
    }

    public void setupAndExecuteTask( Task task ) {
        taskExecutorService.submit( task );
    }
}
