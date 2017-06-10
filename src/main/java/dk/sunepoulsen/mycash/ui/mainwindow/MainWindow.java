package dk.sunepoulsen.mycash.ui.mainwindow;

import dk.sunepoulsen.mycash.registry.Registry;
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

    public MainWindow() {
        this.taskExecutorService = Registry.getDefault().getUiRegistry().getTaskExecutorService();
    }

    @Override
    public void initialize( final URL location, final ResourceBundle resources ) {
        actionPanel.setOnTaskCreated( this::setupAndExecuteTask );
    }

    private void setupAndExecuteTask( Task task ) {
        taskExecutorService.submit( task );
    }
}
