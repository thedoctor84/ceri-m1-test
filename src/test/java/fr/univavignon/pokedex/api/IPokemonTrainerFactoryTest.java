package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonTrainerFactoryTest {
	@Mock protected IPokedexFactory pokedexFactoryMock;
	
	@Mock protected IPokedex pokedexMock;
	
	@Mock private IPokemonTrainerFactory pokemonTrainerFactoryMock;
	
	private PokemonTrainer pokemonTrainer;
	
	private Team team = Team.INSTINCT;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void setUp() throws PokedexException {
		pokemonTrainer = new PokemonTrainer("TheDoctor", team, pokedexMock);
		 Mockito.when(pokemonTrainerFactoryMock.createTrainer("TheDoctor", team, pokedexFactoryMock)).thenReturn(pokemonTrainer);
	}
	@Test
	public void testCreateTrainer() throws PokedexException {
		assertEquals(pokemonTrainer,pokemonTrainerFactoryMock.createTrainer("TheDoctor", team, pokedexFactoryMock));
		assertEquals("TheDoctor",pokemonTrainer.getName());
		assertEquals(Team.INSTINCT,pokemonTrainer.getTeam());
		assertEquals(pokedexMock,pokemonTrainer.getPokedex());
	}
}
