/**
 * 
 */
package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * @author antoine
 *
 */
public class PokedexFactory implements IPokedexFactory {

	/* (non-Javadoc)
	 * @see fr.univavignon.pokedex.api.IPokedexFactory#createPokedex(fr.univavignon.pokedex.api.IPokemonMetadataProvider, fr.univavignon.pokedex.api.IPokemonFactory)
	 */
	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		return new Pokedex(metadataProvider,pokemonFactory);
	}

}
