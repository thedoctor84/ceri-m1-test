package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexFactoryTest {
	
	@Mock private IPokedexFactory pokedexFactoryMock;
	
	@Mock protected IPokedex pokedexMock;

	@Mock protected IPokemonMetadataProvider pokemonMetadataProviderMock;
	
	@Mock protected IPokemonFactory pokemonFactoryMock;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	public void setUp() throws PokedexException {
		 Mockito.when(pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock,pokemonFactoryMock)).thenReturn(pokedexMock);
	}
	@Test
	public void testCreatePokedex() throws PokedexException {
		assertEquals(pokedexMock,pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock,pokemonFactoryMock));
	}
}
