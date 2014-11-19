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
 * Class containing data pertaining to a single Pokemon
 *
 * @author Alice Quiros
 */
public class Pokemon {

    private final String name;
    private final Attributes initAttributes;
    private Attributes attributes;
    private ElementType type1;
    private ElementType type2;
    private List<Move> moveset;
    private Trainer trainer;

    /**
     * Default constructor for a two-type Pokémon
     *
     * @param trainer the Pokémon's owner
     * @param name The name of the Pokémon
     * @param initHp The starting health
     * @param initAttk the starting attack value
     * @param initDef the starting defense value
     * @param initSpd the starting speed value
     * @param primaryType the Pokémon's primary type
     * @param secondaryType the Pokémon's secondary type
     */
    public Pokemon(Trainer trainer, String name, int initHp, int initAttk, int initDef, int initSpd, ElementType primaryType, ElementType secondaryType) {
        this.trainer = trainer;
        this.name = name;
        this.initAttributes = new Attributes(initHp, initAttk, initDef, initSpd);
        this.attributes = initAttributes.copyOf();
        this.type1 = primaryType;
        this.type2 = secondaryType;
        this.moveset = new ArrayList(4);
    }

    /**
     * Default constructor for a single-type Pokémon
     *
     * @param trainer the Pokémon's owner
     * @param name The name of the Pokémon
     * @param initHp The starting health
     * @param initAttk the starting attack value
     * @param initDef the starting defense value
     * @param initSpd the starting speed value
     * @param primaryType the Pokémon's primary and only type
     */
    public Pokemon(Trainer trainer, String name, int initHp, int initAttk, int initDef, int initSpd, ElementType primaryType) {
        this(trainer, name, initHp, initAttk, initDef, initSpd, primaryType, null);
    }

    /**
     * Returns a copy of the initial attributes of the Pokémon
     *
     * @return a copy of the initial attributes of the Pokémon
     */
    public Attributes getInitialAttributes() {
        return initAttributes.copyOf();
    }

    /**
     * Returns the current attributes of the Pokémon
     *
     * @return the current attributes of the Pokémon
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Resets the current attributes to their initial values
     */
    public void resetAttributes() {
        attributes = initAttributes.copyOf();
    }

    /**
     * Returns the Pokémon's primary type
     *
     * @return the Pokémon's primary type
     */
    public ElementType primaryType() {
        return type1;
    }

    /**
     * Returns the Pokémon's name
     *
     * @return the Pokémon's name
     */
    public String name() {
        return name;
    }

    /**
     * Returns the Pokémon's secondary type or null if it has none
     *
     * @return the Pokémon's secondary type or null if it has none
     */
    public ElementType secondaryType() {
        return type2;
    }

    /**
     * Returns the Pokémon's starting health
     *
     * @return the Pokémon's starting health
     */
    public int initialHealth() {
        return initAttributes.health();
    }

    /**
     * Returns the Pokémon's starting attack
     *
     * @return the Pokémon's starting attack
     */
    public int initialAttack() {
        return initAttributes.attack();
    }

    /**
     * Returns the Pokémon's starting defense
     *
     * @return the Pokémon's starting defense
     */
    public int initialDefense() {
        return initAttributes.defense();
    }

    /**
     * Returns the Pokémon's starting speed
     *
     * @return the Pokémon's starting speed
     */
    public int initialSpeed() {
        return initAttributes.speed();
    }

    /**
     * Returns true if the Pokémon has no health left
     *
     * @return true if the Pokémon has no health left
     */
    public boolean isDead() {
        return (attributes.health() <= 0);
    }

    /**
     * Returns the current health value
     *
     * @return the current health value
     */
    public int health() {
        return attributes.health();
    }

    /**
     * Returns the current attack value
     *
     * @return the current attack value
     */
    public int attack() {
        return attributes.attack();
    }

    /**
     * Returns the current defense value
     *
     * @return the current defense value
     */
    public int defense() {
        return attributes.defense();
    }

    /**
     * Returns the current speed value
     *
     * @return the current speed value
     */
    public int speed() {
        return attributes.speed();
    }

    /**
     * Returns the Pokémon's trainer
     *
     * @return the Pokémon's trainer
     */
    public Trainer trainer() {
        return trainer;
    }

    /**
     * Returns the number of moves a Pokémon has available
     *
     * @return the number of moves a Pokémon has available
     */
    public int numMoves() {
        return moveset.size();
    }

    /**
     * Returns a specific move, or null if there is none
     *
     * @param index the index of the move from 1 to 4
     * @return a specific move
     */
    public Move getMove(int index) {
        return moveset.get(index);
    }

    /**
     * Returns the move within the moveset with the corresponding name (not case
     * sensitive)
     *
     * @param name the name of the move
     * @return a move with the corresponding name, or null if none
     */
    public Move getMove(String name) {
        for (Move move : moveset) {
            if (move.name().toLowerCase().equals(name.toLowerCase())) {
                return move;
            }
        }
        return null;
    }

    /**
     * Returns a list of all the moves this Pokémon knows
     *
     * @return a list of all the moves this Pokémon knows
     */
    public List<Move> getMoveset() {
        return moveset;
    }

    protected void addMoves(List<Move> moves) {
        this.moveset.addAll(moves);
    }

    @Override
    public String toString() {
        return name + " [" + type1 + (type2 == null ? "" : "/" + type2) + " " + attributes + "]";
    }

    /**
     * Returns a new Pokémon instance with the same initial attributes as this
     * one, but reset volatile attributes
     *
     * @return a new Pokémon instance
     */
    public Pokemon copyOf() {
        return new Pokemon(trainer, name, initAttributes.health(), initAttributes.attack(), initAttributes.defense(), initAttributes.speed(), type1, type2);
    }

    /**
     * Compares this Pokémon with another and returns true if both Pokémon are
     * equal, ignoring any differences between their volatile attributes
     *
     * @param pokemon the second Pokémon to compare to
     * @return true if both Pokémon are equal
     */
    public boolean equals(Pokemon pokemon) {
        return (this.name.equals(pokemon.name)
                && this.type1 == pokemon.type1
                && this.type2 == pokemon.type2
                && this.initAttributes.equals(pokemon.initAttributes));
    }

}
