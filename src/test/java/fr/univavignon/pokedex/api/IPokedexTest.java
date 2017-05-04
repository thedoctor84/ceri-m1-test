package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexTest {

	//int size();
	//int addPokemon(Pokemon pokemon);
	//Pokemon getPokemon(int id) throws PokedexException;
	//List<Pokemon> getPokemons();
	//List<Pokemon> getPokemons(Comparator<Pokemon> order);
	
	@Mock IPokedex pokedex;
	
	private Pokemon pokemon;
	
	@Before
	public void setUp() throws PokedexException {
	}
	@Test
	public void testGetPokemonMetadata() throws PokedexException {
	}
	
	
}
