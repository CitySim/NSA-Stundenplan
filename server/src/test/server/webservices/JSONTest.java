package server.webservices;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import server.entities.Form;

public class JSONTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Form class1 = new Form();
		class1.setDescription("IT1a");
		String json = Webservices.send(class1);
		Form class2 = Webservices.receive(json);
		assertEquals(class1.getDescription(), class2.getDescription());
	}

}
