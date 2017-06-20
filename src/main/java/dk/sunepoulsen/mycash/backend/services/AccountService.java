package dk.sunepoulsen.mycash.backend.services;

import dk.sunepoulsen.mycash.db.entities.AccountEntity;
import dk.sunepoulsen.mycash.db.entities.AccountingEntity;
import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;
import dk.sunepoulsen.mycash.ui.model.Account;
import dk.sunepoulsen.mycash.validation.MyCashValidateException;
import dk.sunepoulsen.mycash.validation.MyCashValidation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunepoulsen on 12/06/2017.
 */
public class AccountService {
    private final ProjectDatabase database;

    public AccountService( ProjectDatabase database ) {
        this.database = database;
    }

    public Account create( Account account ) throws MyCashValidateException {
        MyCashValidation.validateValue( account );

        database.transactional( em -> {
            AccountEntity entity = convertModel( account );
            em.persist( entity );

            account.setId( entity.getId() );
        } );

        return account;
    }

    public Account find( Long id ) throws MyCashValidateException {
        return database.untransactionalFunction( em -> {
            AccountEntity entity = em.find( AccountEntity.class, id );
            return convertEntity( entity );
        } );
    }

    private AccountEntity convertModel( Account model ) {
        AccountEntity entity = new AccountEntity();

        entity.setId( model.getId() );
        if( model.getParentId() != null ) {
            entity.setParent( new AccountEntity( model.getParentId() ) );
        }
        entity.setChildren( null );
        entity.setAccountings( convertModel( entity, model.getAccountings() ) );

        entity.setAccountType( model.getType() );
        entity.setNo( model.getNo() );
        entity.setName( model.getName() );
        entity.setDescription( model.getDescription() );

        return entity;
    }

    private List<AccountingEntity> convertModel( AccountEntity entity, List<Long> accountingIds ) {
        return accountingIds.stream().map( it -> {
            AccountingEntity accountingEntity = new AccountingEntity( it );
            accountingEntity.setAccounts( Arrays.asList( entity ) );

            return accountingEntity;
        } ).collect( Collectors.toList());
    }

    private Account convertEntity( AccountEntity entity ) {
        Account model = new Account();

        model.setId( entity.getId() );

        if( entity.getParent() != null ) {
            model.setParentId( entity.getParent().getId() );
        }

        if( entity.getAccountings() != null ) {
            model.setAccountings( convertEntity( entity.getAccountings() ) );
        }

        model.setType( entity.getAccountType() );
        model.setNo( entity.getNo() );
        model.setName( entity.getName() );
        model.setDescription( entity.getDescription() );

        return model;
    }

    private List<Long> convertEntity( List<AccountingEntity> accountings ) {
        return accountings.stream().map( it -> it.getId() ).collect( Collectors.toList() );
    }
}
