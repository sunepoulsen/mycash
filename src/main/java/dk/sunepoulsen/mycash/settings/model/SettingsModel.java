package dk.sunepoulsen.mycash.settings.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
@Data
public class SettingsModel {
    @JsonProperty( "date-formats" )
    private DateFormats dateFormats;
}
