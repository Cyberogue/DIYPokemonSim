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

    /**
     * Basic constructor for a trainer without Pokémon :(
     *
     * @param name The name of the trainer
     */
    public Trainer(String name) {
        this.name = name;
        this.party = new ArrayList();
        this.inventory = new TrainerInventory();
    }

    /**
     * Basic constructor for a trainer who already has Pokémon
     *
     * @param name The name of the trainer
     * @param party THe Pokémon in the trainer's party
     */
    public Trainer(String name, List<Pokemon> party) {
        this(name);
        party.addAll(party);
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
    
    @Override
    public String toString() {
        String s = name + " [";
        
        for (Pokemon pokemon : party) {
            s += pokemon.name().concat(", ");
        }
        
        s += "]";
        
        return s;
    }
}
