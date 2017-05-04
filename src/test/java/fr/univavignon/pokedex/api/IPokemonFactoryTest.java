package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonFactoryTest {
	
	@Mock private IPokemonFactory pokemonFactoryMock;
	
	private Pokemon pokemon;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void setUp() throws PokedexException {
		pokemon = new Pokemon(1, "Bulbizarre", 20, 18, 25, 20, 30, 15, 2, 1.0);
		 Mockito.when(pokemonFactoryMock.createPokemon(1,20,30,15,2)).thenReturn(pokemon);
	}
	@Test
	public void testCreatePokedex() throws PokedexException {
		assertEquals(pokemon,pokemonFactoryMock.createPokemon(1,20,30,15,2));
	}

}
