/**
 * 
 */
package fr.univavignon.pokedex.api.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * @author antoine
 *
 */
public class Pokedex implements IPokedex {

	private static Logger log = Logger.getLogger(Pokedex.class);

	// Liste des pokemons contenu dans ce pokedex
	private List<Pokemon> pokemons;
	private transient IPokemonFactory pokemonFactory;
	private transient IPokemonMetadataProvider pokemonMetadataProvider;


	public Pokedex() {
		pokemons = new ArrayList<Pokemon>();
		pokemonMetadataProvider = new PokemonMetadataProvider();
		pokemonFactory = new PokemonFactory();
		
		try {
			load();
		} catch (FileNotFoundException e) {
			log.fatal("Le pokedex n'a pas pu être chargé : Fichier introuvable");
		} catch (IOException e) {
			log.fatal("Erreur d'écriture lors du chargement du pokedex : "+e.getMessage());
		} catch (ClassNotFoundException e) {
			log.fatal("Erreur lors de la récupération de l'objet Pokedex : "+e.getMessage());
		}
	}

	public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
		this();
		this.pokemonMetadataProvider = pokemonMetadataProvider;
		this.pokemonFactory = pokemonFactory;
	}

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return pokemonMetadataProvider.getPokemonMetadata(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
	}

	@Override
	public int size() {
		return pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		pokemons.add(pokemon);
		save();
		return pokemons.size() - 1;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		return pokemons.get(id);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return Collections.unmodifiableList(pokemons);
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> pokemonsSorted = new ArrayList<Pokemon>(pokemons);
        pokemonsSorted.sort(order);
        return Collections.unmodifiableList(pokemonsSorted);
	}

	private void save() {
		File fichier =  new File("pokedex.ser") ;
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			log.fatal("Le pokedex "+fichier.getName()+" n'a pas pu être sauvegardé : Fichier introuvable");
		} catch (IOException e) {
			log.fatal("Erreur d'écriture lors de la sauvegarde du pokedex : "+e.getMessage());
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				log.fatal("Erreur lors de la fermeture du flux pour sauvegarder le pokedex : "+e.getMessage());
			}
		}
	}

	private void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		File fichier =  new File("pokedex.ser") ;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier)) ;
		//try {
			Pokedex pokedex = (Pokedex) ois.readObject();
			pokemons = new ArrayList<Pokemon>(pokedex.pokemons);
			try {
				ois.close();
			} catch (IOException e) {
				log.fatal("Erreur lors de la fermeture du flux pour charger le pokedex : "+e.getMessage());
			}
		/*} catch (FileNotFoundException e) {
			log.fatal("Le pokedex "+fichier.getName()+" n'a pas pu être chargé : Fichier introuvable");
		} catch (IOException e) {
			log.fatal("Erreur d'écriture lors du chargement du pokedex : "+e.getMessage());
		} catch (ClassNotFoundException e) {
			log.fatal("Erreur lors de la récupération de l'objet Pokedex : "+e.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				log.fatal("Erreur lors de la fermeture du flux pour charger le pokedex : "+e.getMessage());
			}
		}*/
	}
}
