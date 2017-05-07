/**
 * 
 */
package fr.univavignon.pokedex.api.impl;

import org.apache.log4j.Logger;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * @author antoine
 *
 */
public class PokemonFactory implements IPokemonFactory {
	
	private static Logger log = Logger.getLogger(PokemonFactory.class);

	/* (non-Javadoc)
	 * @see fr.univavignon.pokedex.api.IPokemonFactory#createPokemon(int, int, int, int, int)
	 */
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadataProvider metadataProvider = new PokemonMetadataProvider();
		try {
			PokemonMetadata pokemonData = metadataProvider.getPokemonMetadata(index);
			Pokemon pokemon = new Pokemon(index, pokemonData.getName(), pokemonData.getAttack(), pokemonData.getDefense(), pokemonData.getStamina(), cp, hp, dust, candy, 1.0);
			return pokemon;
		} catch (PokedexException e) {
			log.fatal("Erreur lors de la création du pokemon avec l'index"+index+" : "+e.getMessage());
		}
		
		return null;
		
	}

}
