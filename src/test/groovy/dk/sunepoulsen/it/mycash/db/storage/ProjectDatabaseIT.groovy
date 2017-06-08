package dk.sunepoulsen.it.mycash.db.storage

import dk.sunepoulsen.mycash.db.storage.ProjectDatabase
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

/**
 * Created by sunepoulsen on 12/05/2017.
 */
class ProjectDatabaseIT {
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
    void testQueryCities() {
        assert projectStorage.query { em -> em.createQuery( "SELECT c from CityCodeEntity c" ) }.size() == 1102
    }
}
