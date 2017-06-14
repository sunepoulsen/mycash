package dk.sunepoulsen.mycash.ui;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by sunepoulsen on 14/06/2017.
 */
public class ControlUtils {
    public static String getText( TextField field ) {
        if( field == null ) {
            return "";
        }

        return field.getText();
    }

    public static String getText( DatePicker control ) {
        if( control == null ) {
            return "";
        }

        if( control.editorProperty() == null ) {
            return "";
        }

        return getText( control.editorProperty().getValue() );
    }
}
