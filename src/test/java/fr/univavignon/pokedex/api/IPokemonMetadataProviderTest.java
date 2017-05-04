package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonMetadataProviderTest {

	@Mock private IPokemonMetadataProvider pokemonMetadataProviderMock;

	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	private PokemonMetadata pokemonMetadata;

	@Before
	public void setUp() throws PokedexException {
		 pokemonMetadata = new PokemonMetadata(1,"Bulbizarre",50,40,30);
		 Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(1)).thenReturn(pokemonMetadata);
		 Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(-1)).thenThrow(new PokedexException("Pokedex exception"));
	}
	@Test
	public void testGetPokemonMetadata() throws PokedexException {
		assertEquals(1, pokemonMetadataProviderMock.getPokemonMetadata(pokemonMetadata.getIndex()).getIndex());
		assertEquals("Bulbizarre", pokemonMetadataProviderMock.getPokemonMetadata(pokemonMetadata.getIndex()).getName());
		assertEquals(50, pokemonMetadataProviderMock.getPokemonMetadata(pokemonMetadata.getIndex()).getAttack());
		assertEquals(40, pokemonMetadataProviderMock.getPokemonMetadata(pokemonMetadata.getIndex()).getDefense());
		assertEquals(30, pokemonMetadataProviderMock.getPokemonMetadata(pokemonMetadata.getIndex()).getStamina());
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonMetadataNotFoundException() throws PokedexException {
			pokemonMetadataProviderMock.getPokemonMetadata(-1);
	}

}
