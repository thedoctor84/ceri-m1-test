/**
 * 
 */
package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.impl.PokedexFactory;

/**
 * @author antoine
 *
 */
public class PokedexFactoryTest extends IPokedexFactoryTest {
	
	private IPokedexFactory pokedexFactory;
	
	@Before
	public void setUp() throws PokedexException {
		 pokedexFactory = new PokedexFactory();
	}
	
	@Test
	public void testCreatePokedex() throws PokedexException {
		assertNotNull(pokedexFactory.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock));
	}
	
	@After
	public void deletePokedex() {
		File file = new File("pokedex.ser");
		file.delete();
	}

}
