/**
 * 
 */
package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

/**
 * @author antoine
 *
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	/* (non-Javadoc)
	 * @see fr.univavignon.pokedex.api.IPokemonTrainerFactory#createTrainer(java.lang.String, fr.univavignon.pokedex.api.Team, fr.univavignon.pokedex.api.IPokedexFactory)
	 */
	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		return new PokemonTrainer(name,team,pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory()));
	}

}
