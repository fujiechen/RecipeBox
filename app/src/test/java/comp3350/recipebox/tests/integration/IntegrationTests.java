package comp3350.recipebox.tests.integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DataAccessHSQLDBTest.class,BusinessPersistenceSeamTest.class })
public class IntegrationTests
{
}
