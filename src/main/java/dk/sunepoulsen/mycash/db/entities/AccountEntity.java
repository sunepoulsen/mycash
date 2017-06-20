package dk.sunepoulsen.mycash.db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by sunepoulsen on 17/06/2017.
 */
@Data
@Entity
@Table( name = "accounts" )
public class AccountEntity {
    /**
     * Primary key.
     */
    @Id
    @SequenceGenerator( name = "account_id_seq_generator", sequenceName = "account_id_seq",
            allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "account_id_seq_generator" )
    @Column( name = "account_id" )
    private Long id;

    @ManyToOne
    @JoinColumn( name="parent_account_id" )
    private AccountEntity parent;

    @OneToMany( cascade = ALL, mappedBy = "parent" )
    private List<AccountEntity> children;

    @ManyToMany
    @JoinTable( name = "accounting_accounts",
            joinColumns = @JoinColumn( name = "account_id", referencedColumnName = "account_id" ),
            inverseJoinColumns = @JoinColumn( name = "accounting_id", referencedColumnName = "accounting_id" ) )
    private List<AccountingEntity> accountings;

    @Column( name = "type" )
    @Enumerated( EnumType.STRING )
    private AccountType accountType;

    @Column( name = "no", nullable = false )
    private String no;

    @Column( name = "name", nullable = false )
    private String name;

    @Lob
    @Column( name = "description" )
    private String description;

    public AccountEntity() {
    }

    public AccountEntity( Long id ) {
        this.id = id;
    }

}
