package fr.univavignon.pokedex.api;

import java.io.Serializable;

/**
 * Pokemon POJO.
 * 
 * @author fv
 */
public final class Pokemon extends PokemonMetadata implements Serializable {

	private static final long serialVersionUID = -6844565099843298492L;

	/** Combat Point of the pokemon. **/
	private final int cp;

	/** HP of the pokemon. **/
	private final int hp;

	/** Required dust for upgrading this pokemon. **/
	private final int dust;

	/** Required candy for upgrading this pokemon. **/
	private final int candy;

	/** IV perfection percentage. **/
	private final double iv;
	
	/**
	 * Default constructor.
	 * 
	 * @param index Pokemon index.
	 * @param name Pokemon name.
	 * @param attack Attack level.
	 * @param defense Defense level.
	 * @param stamina Stamina level.
	 * @param cp Pokemon cp.
	 * @param hp Pokemon hp.
	 * @param dust Required dust for upgrading this pokemon.
	 * @param candy Required candy for upgrading this pokemon.
	 * @param iv IV perfection percentage.
	 */
	public Pokemon(
			final int index,
			final String name,
			final int attack,
			final int defense,
			final int stamina,
			final int cp,
			final int hp,
			final int dust,
			final int candy,
			final double iv) {
		super(index, name, attack, defense, stamina);
		this.cp = cp;
		this.hp = hp;
		this.dust = dust;
		this.candy = candy;
		this.iv = iv;
	}
	
	public Pokemon() {
		this.cp = 0;
		this.hp = 0;
		this.dust = 0;
		this.candy = 0;
		this.iv = 0.0;
		
	}

	/** Combat Point getter getter. **/
	public int getCp() {
		return cp;
	}
	
	/** HP getter. **/
	public int getHp() {
		return hp;
	}

	/** Dust getter. **/
	public int getDust() {
		return dust;
	}

	/** Candy getter. **/
	public int getCandy() {
		return candy;
	}
	
	/** IV getter. **/
	public double getIv() {
		return iv;
	}
	
}
