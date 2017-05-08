/**
 * 
 */
package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.impl.PokemonFactory;

/**
 * @author antoine
 *
 */
public class PokemonFactoryTest extends IPokemonFactoryTest {
	
	private IPokemonFactory pokemonFactory;
	
	@Before
	public void setUp() throws PokedexException {
		pokemonFactory = new PokemonFactory();
	}
	@Test
	public void testCreatePokemon() throws PokedexException {
		Pokemon pokemon = pokemonFactory.createPokemon(1,126,126,4000,4);
		assertEquals(1,pokemon.getIndex());
		assertEquals("Bulbasaur",pokemon.getName());
		assertEquals(126,pokemon.getAttack());
		assertEquals(126,pokemon.getDefense());
		assertEquals(90,pokemon.getStamina());
	}

}
