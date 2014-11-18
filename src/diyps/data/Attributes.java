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
 * Class containing data regarding a Pokémon's attributes. No attribute can drop
 * below 1, with the exception of Health which can't drop below 0.
 *
 * @author Alice Quiros
 */
public class Attributes {

    private int health;
    private int attack;
    private int defense;
    private int speed;

    /**
     * Default constructor which sets all values to their lowest
     */
    public Attributes() {
        this.health = 0;
        this.attack = 1;
        this.defense = 1;
        this.speed = 1;
    }

    /**
     * Default constructor allowing you to pre-set all four values
     *
     * @param health the health value
     * @param attack the attack value
     * @param defense the defense value
     * @param speed the speed value
     * @throws UnsupportedOperationException if health is less than 0 or any of
     * the other 3 values are less than 1
     */
    public Attributes(int health, int attack, int defense, int speed) {
        if (health < 0 || attack < 1 || defense < 1 || speed < 1) {
            throw new UnsupportedOperationException();
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    /**
     * Simultaneously sets all four values within the object
     *
     * @param health the new health value
     * @param attack the new attack value
     * @param defense the new defense value
     * @param speed the new speed value
     * @throws UnsupportedOperationException if health is less than 0 or any of
     * the other 3 values are less than 1
     */
    public void set(int health, int attack, int defense, int speed) {
        if (health < 0 || attack < 1 || defense < 1 || speed < 1) {
            throw new UnsupportedOperationException();
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    /**
     * Changes the health value by a given amount, preventing it from dropping
     * below 0
     *
     * @param amount the amount to change by
     * @return true if health is at 0, otherwise false
     */
    public boolean changeHealth(int amount) {
        health += amount;
        if (health <= 0) {
            health = 0;
            return true;
        }
        return false;
    }

    /**
     * Changes the attack value by a given amount, preventing it from dropping
     * below 1
     *
     * @param amount the amount to change by
     * @return true if attack is at 1, otherwise false
     */
    public boolean changeAttack(int amount) {
        attack += amount;
        if (attack <= 1) {
            attack = 1;
            return true;
        }
        return false;
    }

    /**
     * Changes the defense value by a given amount, preventing it from dropping
     * below 1
     *
     * @param amount the amount to change by
     * @return true if defense is at 1, otherwise false
     */
    public boolean changeDefense(int amount) {
        defense += amount;
        if (defense < 1) {
            defense = 1;
            return true;
        }
        return false;
    }

    /**
     * Changes the speed value by a given amount, preventing it from dropping
     * below 1
     *
     * @param amount the amount to change by
     * @return true if speed is at 1, otherwise false
     */
    public boolean changeSpeed(int amount) {
        speed += amount;
        if (speed < 1) {
            speed = 1;
            return true;
        }
        return false;
    }

    /**
     * Returns the health value
     *
     * @return the health value
     */
    public int health() {
        return health;
    }

    /**
     * Returns the attack value
     *
     * @return the attack value
     */
    public int attack() {
        return attack;
    }

    /**
     * Returns the defense value
     *
     * @return the defense value
     */
    public int defense() {
        return defense;
    }

    /**
     * Returns the speed value
     *
     * @return the speed value
     */
    public int speed() {
        return speed;
    }

    /**
     * Returns a copy of this Attributes instance
     *
     * @return a copy of this Attributes instance
     */
    public Attributes copyOf() {
        return new Attributes(health, attack, defense, speed);
    }

    @Override
    public String toString() {
        return "Hp:" + health + " Attk:" + attack + " Def:" + defense + " Spd:" + speed;
    }

    /**
     * Compares this Attributes instance with a second and returns true if they
     * are both equal
     *
     * @param attributes the second instance to compare to
     * @return true if all the values within the two are equal
     */
    public boolean equals(Attributes attributes) {
        return (this.health == attributes.health
                && this.attack == attributes.attack
                && this.defense == attributes.defense
                && this.speed == attributes.speed);
    }
}
