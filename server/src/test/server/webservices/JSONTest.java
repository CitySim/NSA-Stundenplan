package server.webservices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.webservices.Webservices;
import server.entities.Klasse;

public class JSONTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Klasse klasse = new Klasse();
		klasse.setBezeichung("IT1a");
		String json = Webservices.send(klasse);
		Klasse klasse2 = Webservices.receive(json);
		assertEquals(klasse.getBezeichung(), klasse2.getBezeichung());
	}

}
