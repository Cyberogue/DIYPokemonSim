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

/**
 * Class containing data about an attack from one trainer's Pokémon to the other
 *
 * @author Alice Quiros
 */
public class AttackRecord {

    public static final short EFFECTIVENESS_NONE = 0;
    public static final short EFFECTIVENESS_LOW = 1;
    public static final short EFFECTIVENESS_NORMAL = 2;
    public static final short EFFECTIVENESS_HIGH = 3;

    private Move move;
    private Pokemon attacker;
    private Pokemon defender;
    private float typeBonus;

    /**
     * Basic constructor for an immutable attack record
     *
     * @param move the move used by the attacker
     * @param attacker the attacking Pokémon
     * @param defender the defending Pokémon
     */
    public AttackRecord(Move move, Pokemon attacker, Pokemon defender) {
        this.move = move;
        this.attacker = attacker;
        this.defender = defender;
        typeBonus = PokeCalculator.getTypeBonus(move.type(), defender.primaryType())
                * PokeCalculator.getTypeBonus(move.type(), defender.secondaryType());
    }

    /**
     * Returns the move used by the attacker
     *
     * @return the move used by the attacker
     */
    public Move move() {
        return move;
    }

    /**
     * Returns the attacking Pokémon
     *
     * @return the attacking Pokémon
     */
    public Pokemon attacker() {
        return attacker;
    }

    /**
     * Returns the defending Pokémon
     *
     * @return the defending Pokémon
     */
    public Pokemon defender() {
        return defender;
    }

    /**
     * Returns the effectiveness of the move
     *
     * @return a short value representing the effectiveness of the move
     */
    public short effectiveness() {

        if (typeBonus <= 0) {
            return EFFECTIVENESS_NONE;
        } else if (typeBonus <= .9) {
            return EFFECTIVENESS_LOW;
        } else if (typeBonus <= 1.1) {
            return EFFECTIVENESS_NORMAL;
        } else {
            return EFFECTIVENESS_HIGH;
        }
    }

    /**
     * Returns the type advantage bonus of the move
     *
     * @return the type advantage bonus of the move
     */
    public float typeBonus() {
        return typeBonus;
    }

    /**
     * Returns true if Same Type Attack Bonus applies to the move
     *
     * @return true if Same Type Attack Bonus applies to the move
     */
    public boolean hasSTAB() {
        return (move.type() == attacker.primaryType() || move.type() == attacker.secondaryType());
    }

    /**
     * Returns the total damage multiplier
     *
     * @return the total damage multiplier
     */
    public float getDamageBonus() {
        float bonus = typeBonus;
        if (hasSTAB()) {
            bonus = 1.5f;
        }
        return bonus;
    }

    /**
     * Returns the total damage dealt against the defending Pokémon
     *
     * @return the total damage dealt against the defending Pokémon
     */
    public int getTotalDamage() {
        int damage = (int) (move.value() * getDamageBonus() * attacker.attack() / defender.defense());
        if (damage < 1) {
            return 1;
        } else {
            return damage;
        }
    }
}
