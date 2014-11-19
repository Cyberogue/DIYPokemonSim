/*
 * The MIT License
 *
 * Copyright 2014 Alice Quiros.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package diyps.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing the data and relevant methods for a trainer (team) in a
 * battle
 *
 * @author Alice Quiros
 */
public class Trainer {

    private final String name;
    private final List<Pokemon> party;
    private final TrainerInventory inventory;
    private Pokemon withdrawn;

    /**
     * Basic constructor for a trainer without Pokémon :(
     *
     * @param name The name of the trainer
     */
    public Trainer(String name) {
        this.name = name;
        this.party = new ArrayList();
        this.inventory = new TrainerInventory();
        withdrawn = null;
    }

    /**
     * Basic constructor for a trainer who already has Pokémon
     *
     * @param name The name of the trainer
     * @param party THe Pokémon in the trainer's party
     */
    public Trainer(String name, List<Pokemon> party) {
        this(name);
        this.party.addAll(party);
        withdrawn = this.party.get(0);
    }

    /**
     * Returns the name of the trainer
     *
     * @return the name of the trainer
     */
    public String name() {
        return name;
    }

    /**
     * Adds a Pokémon to the trainer's party
     *
     * @param pokemon the Pokémon to add to the party
     */
    protected void addToParty(Pokemon pokemon) {
        if (withdrawn == null) {
            withdrawn = pokemon;
        }
        party.add(pokemon);
    }

    /**
     * Returns a list of all the Pokémon in the trainer's party
     *
     * @return a list of all the Pokémon in the trainer's party
     */
    public List<Pokemon> getParty() {
        return party;
    }

    /**
     * Returns the first Pokémon in the party with the provided name
     *
     * @param name the name of the Pokémon to retrieve
     * @return the Pokémon instance corresponding to the name provided. In the
     * case of multiple Pokémon with the same name, this returns the first
     * instance. If no instance is found, returns null.
     */
    public Pokemon getPokemon(String name) {
        for (Pokemon pokemon : party) {
            if (pokemon.name().equals(name)) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Returns the Pokémon with the specified index in the party
     *
     * @param index the index of the Pokémon
     * @return the Pokémon with the specified index in the party
     */
    public Pokemon getPokemon(int index) {
        return party.get(index);
    }

    /**
     * Returns false if at least one Pokémon in the trainer's party has more
     * than 0 health
     *
     * @return false if at least one Pokémon in the tTrainer's party has more
     * than 0 health
     */
    public boolean noUsablePokemon() {
        for (Pokemon pokemon : party) {
            if (!pokemon.isDead()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the first Pokémon within the strainers party that has more than 0
     * health, or null if there are none
     *
     * @return a Pokémon that is still alive or null
     */
    public Pokemon getFirstUsablePokemon() {
        for (Pokemon pokemon : party) {
            if (!pokemon.isDead()) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Sets the currently withdrawn Pokémon to a new Pokémon
     *
     * @param pokemon the Pokémon to replace the previous one with
     * @return the recalled Pokémon, or null if none
     */
    public Pokemon withdrawPokemon(Pokemon pokemon) {
        Pokemon old = withdrawn;
        withdrawn = pokemon;

        return old;
    }

    /**
     * Returns the currently withdrawn Pokémon belonging to the trainer
     *
     * @return the currently withdrawn Pokémon belonging to the trainer
     */
    public Pokemon getWithdrawn() {
        return withdrawn;
    }

    @Override
    public String toString() {
        String s = name + " [";

        for (Pokemon pokemon : party) {
            s = s.concat(pokemon.name() + ", ");
        }

        s += "]";

        return s;
    }
}
