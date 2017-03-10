package library;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDBConnection.class,
	TestLibraryDaoImpl.class
})
public class LibraryTestingSuite {

	
}
