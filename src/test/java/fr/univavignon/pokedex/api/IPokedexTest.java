package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
	
	@Mock IPokedex pokedexMock;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private Pokemon bulbizarre;
	private Pokemon aquali;
	
	private List<Pokemon> pokemons;
	
	private Comparator<Pokemon> ordre = new Comparator<Pokemon>() {
		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			if(o1.getIndex() > o2.getIndex()) return 1;
			else if(o1.getIndex() < o2.getIndex()) return -1;
			else return 0;
		}
};
	
	@Before
	public void setUp() throws PokedexException {
		bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
		aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
		Mockito.when(pokedexMock.size()).thenReturn(2);
		Mockito.when(pokedexMock.addPokemon(bulbizarre)).thenReturn(bulbizarre.getIndex());
		Mockito.when(pokedexMock.addPokemon(aquali)).thenReturn(aquali.getIndex());
		Mockito.when(pokedexMock.getPokemon(bulbizarre.getIndex())).thenReturn(bulbizarre);
		Mockito.when(pokedexMock.getPokemon(aquali.getIndex())).thenReturn(aquali);
		Mockito.when(pokedexMock.getPokemon(-1)).thenThrow(new PokedexException("Pokedex exception"));
		
		pokemons = new ArrayList<Pokemon>(2);
		pokemons.add(bulbizarre);
		pokemons.add(aquali);
		
		Mockito.when(pokedexMock.getPokemons()).thenReturn(pokemons);
		Mockito.when(pokedexMock.getPokemons(ordre)).thenReturn(pokemons);
		}
	
	@Test
	public void testGetSize() throws PokedexException {
		assertEquals(2,pokedexMock.size());
	}
	
	@Test
	public void testAddPokemon() throws PokedexException {
		assertEquals(0,pokedexMock.addPokemon(bulbizarre));
		assertEquals(133,pokedexMock.addPokemon(aquali));
	}
	
	@Test
	public void testGetPokemon() throws PokedexException {
		assertEquals(bulbizarre,pokedexMock.getPokemon(0));
		assertEquals(aquali,pokedexMock.getPokemon(133));
	}
	
	@Test(expected=PokedexException.class)
	public void testGetPokemonNotFoundException() throws PokedexException {
		pokedexMock.getPokemon(-1);
	}
	
	@Test
	public void testGetPokemons() throws PokedexException {
		assertEquals(pokemons,pokedexMock.getPokemons());
	}
	
	@Test
	public void testGetPokemonsOrdre() throws PokedexException {
		assertEquals(pokemons,pokedexMock.getPokemons(ordre));
	}
	
}
