package dk.sunepoulsen.mycash.db.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by sunepoulsen on 12/06/2017.
 */
@Data
@EqualsAndHashCode( exclude = { "accounts" } )
@ToString( exclude = { "accounts" } )
@Entity
@Table( name = "accountings" )
@NamedQueries( {
        @NamedQuery( name = "findAll", query = "SELECT a FROM AccountingEntity a" )
})
public class AccountingEntity {
    /**
     * Primary key.
     */
    @Id
    @SequenceGenerator( name = "accounting_id_seq_generator", sequenceName = "accounting_id_seq",
            allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "accounting_id_seq_generator" )
    @Column( name = "accounting_id" )
    private Long id;

    @Column( name = "name", nullable = false )
    private String name;

    @Column( name = "start_date", nullable = false )
    private LocalDate startDate;

    @Column( name = "end_date", nullable = false )
    private LocalDate endDate;

    @ManyToMany
    @JoinTable( name = "accounting_accounts",
            joinColumns = @JoinColumn( name = "accounting_id", referencedColumnName = "accounting_id" ),
            inverseJoinColumns = @JoinColumn( name = "account_id", referencedColumnName = "account_id" ) )
    private List<AccountEntity> accounts;

    public AccountingEntity() {
    }

    public AccountingEntity( Long id ) {
        this.id = id;
    }
}
