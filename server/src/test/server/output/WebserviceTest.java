package server.output;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import storage.entities.Klasse;

public class WebserviceTest {

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
