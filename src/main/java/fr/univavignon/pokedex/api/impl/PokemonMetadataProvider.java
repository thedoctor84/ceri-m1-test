/**
 * 
 */
package fr.univavignon.pokedex.api.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

/**
 * @author antoine
 *
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

	private static Logger log = Logger.getLogger(PokemonMetadataProvider.class);

	private JsonArray completePokemonsData = null;

	// URL pour récuperer les data des pokémons
	private static final String URL_DATA = "https://raw.githubusercontent.com/PokemonGo-Enhanced/node-pokemongo-data/master/data.json";

	private static final String name = "Identifier" ;
	private static final String attack = "BaseAttack";
	private static final String defense = "BaseDefense";
	private static final String stamina = "BaseStamina";

	/* (non-Javadoc)
	 * @see fr.univavignon.pokedex.api.IPokemonMetadataProvider#getPokemonMetadata(int)
	 * index de 1 à 151, correspondant au numéro du pokémon dans le vrai pokédex
	 */
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		if(index < 1 || index > 151)
			throw new PokedexException("l'index "+index+" est en dehors des limites");

		if(completePokemonsData == null) {
			log.debug("Récupération des data");
			parseMetadata();
		}

		JsonObject pokemon = completePokemonsData.get(index-1).getAsJsonObject();
		log.debug(index+" "+pokemon.get(name).getAsString()+" "+pokemon.get(attack).getAsInt()+" "+pokemon.get(defense).getAsInt()+" "+pokemon.get(stamina).getAsInt());
		return new PokemonMetadata(index,pokemon.get(name).getAsString(),pokemon.get(attack).getAsInt(),pokemon.get(defense).getAsInt(),pokemon.get(stamina).getAsInt());
	}

	// Récupère et parse toutes metadonnees d'un pokemon
	public void parseMetadata() {
		try {
			String json = readUrl(URL_DATA);
			Gson gson = new Gson(); 
			completePokemonsData = gson.fromJson(json, JsonArray.class);
		} catch (MalformedURLException e) {
			log.fatal("Erreur lors de la lecture de l'URL du fichier json : "+e.getMessage());
		} catch (IOException e) {
			log.fatal("Erreur lors du parsing du fichier json : "+e.getMessage());
		}
	}

	// Récuperation du contenu de l'URL pasée en parmètre
	private String readUrl(String urlString) throws MalformedURLException,IOException {

		StringBuffer buffer = new StringBuffer();
		URL url = new URL(urlString);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); 
		String inputLine;
		while ((inputLine = reader.readLine()) != null)
			buffer.append(inputLine);
		reader.close();
		return buffer.toString();
	}
}
