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

    public Attributes() {
        this.health = 0;
        this.attack = 1;
        this.defense = 1;
        this.speed = 1;
    }

    public Attributes(int health, int attack, int defense, int speed) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public void set(int health, int attack, int defense, int speed) {
        if (health < 0 || attack < 1 || defense < 1 || speed < 1) {
            throw new UnsupportedOperationException();
        }

        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
    }

    public void changeHealth(int amount) {
        health += amount;
        if (health < 0) {
            health = 0;
        }
    }

    public void changeAttack(int amount) {
        attack += amount;
        if (attack < 1) {
            attack = 1;
        }
    }

    public void changeDefense(int amount) {
        defense += amount;
        if (defense < 1) {
            defense = 1;
        }
    }

    public void changeSpeed(int amount) {
        speed += amount;
        if (speed < 1) {
            speed = 1;
        }
    }

    public final int health() {
        return health;
    }

    public final int attack() {
        return attack;
    }

    public final int defense() {
        return defense;
    }

    public final int speed() {
        return speed;
    }
}
