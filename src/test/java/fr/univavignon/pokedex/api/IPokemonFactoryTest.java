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
		pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
		 Mockito.when(pokemonFactoryMock.createPokemon(0,126,126,4000,4)).thenReturn(pokemon);
	}
	@Test
	public void testCreatePokedex() throws PokedexException {
		assertEquals(pokemon,pokemonFactoryMock.createPokemon(0,126,126,4000,4));
	}

}
