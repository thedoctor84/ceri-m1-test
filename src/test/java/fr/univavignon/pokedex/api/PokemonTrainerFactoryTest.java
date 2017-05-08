/**
 * 
 */
package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.impl.PokemonTrainerFactory;

/**
 * @author antoine
 *
 */
public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest{
	private IPokemonTrainerFactory pokemonTrainerFactory;
	
	private PokemonTrainer pokemonTrainer;
	private Team team = Team.INSTINCT;
	
	@Before
	public void setUp() throws PokedexException {
		pokemonTrainerFactory = new PokemonTrainerFactory();
		pokemonTrainer = pokemonTrainerFactory.createTrainer("TheDoctor", team, pokedexFactoryMock);
	}
	
	@Test
	public void testCreateTrainer() throws PokedexException {
		assertEquals("TheDoctor",pokemonTrainer.getName());
		assertEquals(Team.INSTINCT,pokemonTrainer.getTeam());
		assertNull(pokemonTrainer.getPokedex());
	}
}
