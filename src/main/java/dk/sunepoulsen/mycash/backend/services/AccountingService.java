package dk.sunepoulsen.mycash.backend.services;

import dk.sunepoulsen.mycash.db.entities.AccountingEntity;
import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import dk.sunepoulsen.mycash.validation.MyCashValidateException;
import dk.sunepoulsen.mycash.validation.MyCashValidation;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sunepoulsen on 12/06/2017.
 */
public class AccountingService {
    private final ProjectDatabase database;

    public AccountingService( ProjectDatabase database ) {
        this.database = database;
    }

    public Accounting create( Accounting accounting ) throws MyCashValidateException {
        MyCashValidation.validateValue( accounting );

        database.transactional( em -> {
            AccountingEntity entity = convertModel( accounting );
            em.persist( entity );

            accounting.setId( entity.getId() );
        } );

        return accounting;
    }

    public Accounting find( Long id ) throws MyCashValidateException {
        return database.untransactionalFunction( em -> {
            AccountingEntity entity = em.find( AccountingEntity.class, id );
            return convertEntity( entity );
        } );
    }

    public List<Accounting> findAllAccountings() {
        List<AccountingEntity> entities = database.query( em ->  em.createNamedQuery( "findAll", AccountingEntity.class ) );

        return entities.stream()
                .map( this::convertEntity )
                .collect( Collectors.toList() );
    }

    private AccountingEntity convertModel( Accounting model ) {
        AccountingEntity entity = new AccountingEntity();

        entity.setId( model.getId() );
        entity.setName( model.getName() );
        entity.setStartDate( model.getStartDate() );
        entity.setEndDate( model.getEndDate() );

        return entity;
    }

    private Accounting convertEntity( AccountingEntity entity ) {
        Accounting model = new Accounting();

        model.setId( entity.getId() );
        model.setName( entity.getName() );
        model.setStartDate( entity.getStartDate() );
        model.setEndDate( entity.getEndDate() );

        return model;
    }
}
