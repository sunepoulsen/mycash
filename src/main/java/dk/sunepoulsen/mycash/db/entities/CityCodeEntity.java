package dk.sunepoulsen.mycash.db.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by sunepoulsen on 08/06/2017.
 */
@Data
@Entity
@Table( name = "city_codes" )
public class CityCodeEntity {
    /**
     * Primary key.
     */
    @Id
    @SequenceGenerator( name = "city_code_id_seq_generator", sequenceName = "city_code_id_seq",
                        allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "city_code_id_seq_generator" )
    @Column( name = "citycode_id" )
    private Long id;

    @Column( name = "citycode" )
    private String cityCode;

    @Column( name = "cityname" )
    private String cityName;
}
