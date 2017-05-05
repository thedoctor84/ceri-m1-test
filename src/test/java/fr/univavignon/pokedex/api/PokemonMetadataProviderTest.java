/**
 * 
 */
package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.impl.PokemonMetadataProvider;

/**
 * @author antoine
 *
 */
public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest {
	
	private PokemonMetadataProvider pokemonMetadataProvider ;

	private PokemonMetadata bulbasarMetadata;
	
	private PokemonMetadata eeveeMetadata;

	@Before
	public void setUp() throws PokedexException {
		 bulbasarMetadata = new PokemonMetadata(1,"Bulbasaur",126,126,90);
		 eeveeMetadata = new  PokemonMetadata(133,"Eevee",114,128,110);
		 pokemonMetadataProvider = new PokemonMetadataProvider();
	}
	@Test
	public void testGetPokemonMetadata() throws PokedexException {
		assertEquals(1, pokemonMetadataProvider.getPokemonMetadata(bulbasarMetadata.getIndex()).getIndex());
		assertEquals("Bulbasaur", pokemonMetadataProvider.getPokemonMetadata(bulbasarMetadata.getIndex()).getName());
		assertEquals(126, pokemonMetadataProvider.getPokemonMetadata(bulbasarMetadata.getIndex()).getAttack());
		assertEquals(126, pokemonMetadataProvider.getPokemonMetadata(bulbasarMetadata.getIndex()).getDefense());
		assertEquals(90, pokemonMetadataProvider.getPokemonMetadata(bulbasarMetadata.getIndex()).getStamina());
		
		assertEquals(133, pokemonMetadataProvider.getPokemonMetadata(eeveeMetadata.getIndex()).getIndex());
		assertEquals("Eevee", pokemonMetadataProvider.getPokemonMetadata(eeveeMetadata.getIndex()).getName());
		assertEquals(114, pokemonMetadataProvider.getPokemonMetadata(eeveeMetadata.getIndex()).getAttack());
		assertEquals(128, pokemonMetadataProvider.getPokemonMetadata(eeveeMetadata.getIndex()).getDefense());
		assertEquals(110, pokemonMetadataProvider.getPokemonMetadata(eeveeMetadata.getIndex()).getStamina());
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonMetadataNotFoundException() throws PokedexException {
		pokemonMetadataProvider.getPokemonMetadata(-1);
	}
}
