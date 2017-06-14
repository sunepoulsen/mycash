package dk.sunepoulsen.mycash.projects.services;

import dk.sunepoulsen.mycash.db.entities.AccountingEntity;
import dk.sunepoulsen.mycash.db.storage.ProjectDatabase;
import dk.sunepoulsen.mycash.ui.model.Accounting;
import dk.sunepoulsen.mycash.validation.MyCashValidateException;
import dk.sunepoulsen.mycash.validation.MyCashValidation;

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

    private AccountingEntity convertModel( Accounting model ) {
        AccountingEntity entity = new AccountingEntity();

        entity.setId( model.getId() );
        entity.setName( model.getName() );
        entity.setStartDate( model.getStartDate() );
        entity.setEndDate( model.getEndDate() );

        return entity;
    }
}
