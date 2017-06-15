package dk.sunepoulsen.mycash.settings.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by sunepoulsen on 15/06/2017.
 */
@Data
public class UserStates {
    @JsonProperty( "last-connection-directory" )
    private String lastConnectionDirectory;

    @JsonProperty( "divider-positions" )
    private List<Double> dividerPositions;
}
