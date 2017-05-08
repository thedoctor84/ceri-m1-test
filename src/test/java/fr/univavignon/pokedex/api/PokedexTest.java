package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.univavignon.pokedex.api.impl.Pokedex;

public class PokedexTest extends IPokedexTest {

	private Pokedex pokedex;

	private Pokemon bulbizarre;
	private Pokemon aquali;

	private List<Pokemon> pokemons;

	@Before
	public void setUp() throws PokedexException {
		pokedex = new Pokedex();
		bulbizarre = new Pokemon(1, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
		aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
		pokemons = new ArrayList<Pokemon>(2);
		pokemons.add(bulbizarre);
		pokemons.add(aquali);
	}

	@Test
	public void testGetSize() throws PokedexException {
		assertEquals(0,pokedex.size());
		pokedex.addPokemon(bulbizarre);
		assertEquals(1,pokedex.size());
		pokedex.addPokemon(aquali);
		assertEquals(2,pokedex.size());
	}

	@Test
	public void testAddPokemon() throws PokedexException {
		assertEquals(0,pokedex.addPokemon(bulbizarre));
		assertEquals(1,pokedex.addPokemon(aquali));
	}

	@Test
	public void testGetPokemon() throws PokedexException {
		pokedex.addPokemon(bulbizarre);
		pokedex.addPokemon(aquali);
		assertEquals(bulbizarre,pokedex.getPokemon(0));
		assertEquals(aquali,pokedex.getPokemon(1));
	}

	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPokemonNotFoundException() throws PokedexException {
		pokedex.getPokemon(-1);
	}

	@Test
	public void testGetPokemons() throws PokedexException {
		pokedex.addPokemon(bulbizarre);
		pokedex.addPokemon(aquali);
		assertEquals(pokemons,pokedex.getPokemons());
	}

	@Test
	public void testGetPokemonsOrdre() throws PokedexException {
		pokedex.addPokemon(bulbizarre);
		pokedex.addPokemon(aquali);
		List<Pokemon> pokemonsSortedByIndex = pokedex.getPokemons(PokemonComparators.INDEX);
		List<Pokemon> pokemonsSortedByName = pokedex.getPokemons(PokemonComparators.NAME);

		assertEquals(0, pokemonsSortedByIndex.indexOf(bulbizarre));
		assertEquals(1, pokemonsSortedByIndex.indexOf(aquali));

		assertEquals(0, pokemonsSortedByName.indexOf(aquali));
		assertEquals(1, pokemonsSortedByName.indexOf(bulbizarre));
	}

	@Test
	public void testLoadAndSaveFile() throws PokedexException {
		Pokedex test1 = new Pokedex();
		assertEquals(0, test1.size());
		
		test1.addPokemon(bulbizarre);
		test1.addPokemon(aquali);
		assertEquals(2, test1.size());
		
		Pokedex test2 = new Pokedex();
		assertEquals(2, test2.size());
		
		assertEquals(test1.getPokemons().get(0).getName(),test2.getPokemons().get(0).getName());
		assertEquals(test1.getPokemons().get(0).getIndex(),test2.getPokemons().get(0).getIndex());
		
		assertEquals(test1.getPokemons().get(1).getName(),test2.getPokemons().get(1).getName());
		assertEquals(test1.getPokemons().get(1).getIndex(),test2.getPokemons().get(1).getIndex());
	}

	@After
	public void deletePokedex() {
		File file = new File("pokedex.ser");
		file.delete();
	}
}
