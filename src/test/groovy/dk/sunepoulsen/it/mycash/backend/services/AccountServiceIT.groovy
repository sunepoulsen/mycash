package dk.sunepoulsen.it.mycash.backend.services

import dk.sunepoulsen.mycash.backend.services.AccountService
import dk.sunepoulsen.mycash.backend.services.AccountingService
import dk.sunepoulsen.mycash.db.entities.AccountType
import dk.sunepoulsen.mycash.db.storage.ProjectDatabase
import dk.sunepoulsen.mycash.ui.model.Account
import dk.sunepoulsen.mycash.ui.model.Accounting
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import java.time.LocalDate

/**
 * Created by sunepoulsen on 12/05/2017.
 */
class AccountServiceIT {
    private static Properties settings = new Properties()
    private ProjectDatabase projectStorage

    @BeforeClass
    static void migrateDatabase() {
        settings.load( getClass().getResourceAsStream( "/application-test.properties" ) )

        ProjectDatabase database = new ProjectDatabase( "mycash-it", settings.getProperty( "liquibase.datasource.url" ) )
        database.migrate()
    }

    @Before
    void initDatabaseStorage() {
        projectStorage = new ProjectDatabase( "mycash-it", settings.getProperty( "liquibase.datasource.url" ) )
        projectStorage.connect()
        projectStorage.deleteAllData()
    }

    @After
    void clearDatabaseStorage() {
        projectStorage.disconnect()
    }

    @Test
    void testCreateAccount() {
        AccountingService accountingService = new AccountingService( projectStorage )

        Accounting accounting = new Accounting()
        accounting.name = "name"
        accounting.startDate = LocalDate.now()
        accounting.endDate = LocalDate.now()

        accountingService.create( accounting )
        assert accounting.id != null

        AccountService service = new AccountService( projectStorage )

        Account model = new Account();
        model.accountings = [ accounting.id ]
        model.parentId = null
        model.no = "1100"
        model.name = "Income"
        model.type = AccountType.INCOME

        service.create( model )
        assert model.id != null

        Account expected = new Account()
        expected.id = model.id
        expected.accountings = [ accounting.id ]
        expected.parentId = null
        expected.no = "1100"
        expected.name = "Income"
        expected.type = AccountType.INCOME

        Account actual = service.find( model.id )
        assert actual == expected

        model = new Account()
        model.accountings = [ accounting.id ]
        model.parentId = null
        model.no = "1200"
        model.name = "Income"
        model.type = AccountType.INCOME

        service.create( model )
        assert model.id != null

        expected = new Account()
        expected.id = model.id
        expected.accountings = [ accounting.id ]
        expected.parentId = null
        expected.no = "1200"
        expected.name = "Income"
        expected.type = AccountType.INCOME

        actual = service.find( model.id )
        assert actual == expected
    }
}
