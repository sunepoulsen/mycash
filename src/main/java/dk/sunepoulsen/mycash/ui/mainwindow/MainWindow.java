package dk.sunepoulsen.mycash.ui.mainwindow;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import lombok.extern.slf4j.XSlf4j;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by sunepoulsen on 10/05/2017.
 */
@XSlf4j
public class MainWindow implements Initializable {
    @FXML
    private SplitPane panes;

    @Override
    public void initialize( final URL location, final ResourceBundle resources ) {
    }
}
