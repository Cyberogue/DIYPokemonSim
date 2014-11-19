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
package diyps.game.Old;

import diyps.data.Move;
import diyps.data.PokeCalculator;
import diyps.data.Pokemon;
import diyps.data.Trainer;
import java.util.Random;

/**
 * Class containing the main looping methods to run a DIYPokemonGame
 *
 * @author Alice Quiros
 */
public class DIYPokemonGame {

    private DIYPokemonGUI gui = null;
    private final Trainer trainer1;
    private final Trainer trainer2;

    public DIYPokemonGame(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
    }

    public void setGUI(DIYPokemonGUI gui) {
        this.gui = gui;
    }

    protected void submitTurn(TurnMode p1Mode, int p1Select, TurnMode p2Mode, int p2Select) {
        if (p1Select < 0 || p2Select < 0) {
            return;
        }

        gui.println("-----------------------------------------");

        if (p1Mode == TurnMode.ITEM) {
            gui.println(trainer1.name() + " used " + p1Select + "!");
        } else if (p1Mode == TurnMode.SWAP && p1Select < trainer1.getParty().size()) {
            Pokemon newPk = trainer1.getPokemon(p1Select);
            swapPokemon(trainer1, newPk.name());
        }

        if (p2Mode == TurnMode.ITEM) {
            gui.println(trainer2.name() + " used " + p1Select + "!");
        } else if (p2Mode == TurnMode.SWAP && p2Select < trainer2.getParty().size()) {
            Pokemon newPk = trainer2.getPokemon(p2Select);

            swapPokemon(trainer2, newPk.name());
        }

        Trainer firstAttk = null;
        Trainer firstDef = null;
        Move firstMove = null;
        Trainer secondAttk = null;
        Trainer secondDef = null;
        Move secondMove = null;

        if (p1Mode == TurnMode.FIGHT && p2Mode == TurnMode.FIGHT) {
            System.out.println(trainer1.getWithdrawnPokemon().speed() + "\t" + trainer2.getWithdrawnPokemon().speed());
            if ((trainer1.getWithdrawnPokemon().speed() > trainer2.getWithdrawnPokemon().speed())
                    || (trainer1.getWithdrawnPokemon().speed() == trainer2.getWithdrawnPokemon().speed() && new Random().nextBoolean())) {
                firstAttk = trainer1;
                firstDef = trainer2;
                firstMove = trainer1.getWithdrawnPokemon().getMove(p1Select);

                secondAttk = trainer2;
                secondDef = trainer1;
                secondMove = trainer2.getWithdrawnPokemon().getMove(p2Select);
            } else {
                firstAttk = trainer2;
                firstDef = trainer1;
                firstMove = trainer2.getWithdrawnPokemon().getMove(p2Select);

                secondAttk = trainer1;
                secondDef = trainer2;
                secondMove = trainer1.getWithdrawnPokemon().getMove(p1Select);
            }
        } else if (p1Mode == TurnMode.FIGHT && p2Mode != TurnMode.FIGHT) {
            firstAttk = trainer1;
            firstDef = trainer2;
            firstMove = trainer1.getWithdrawnPokemon().getMove(p1Select);
        } else if (p1Mode != TurnMode.FIGHT && p2Mode == TurnMode.FIGHT) {
            firstAttk = trainer2;
            firstDef = trainer1;
            firstMove = trainer2.getWithdrawnPokemon().getMove(p2Select);
        }

        // FIRST ATTACK
        if (firstAttk != null) {
            calculateAttack(firstMove, firstAttk.getWithdrawnPokemon(), firstDef.getWithdrawnPokemon());
            if (checkIfOutOfPokemon(firstDef)) {
                gui.repaint();
                gui.revalidate();
                return;
            }
        }

        if (secondAttk != null) {
            calculateAttack(secondMove, secondAttk.getWithdrawnPokemon(), secondDef.getWithdrawnPokemon());
            checkIfOutOfPokemon(secondDef);
        }

        gui.repaint();
        gui.revalidate();
    }

    private void swapPokemon(Trainer trainer, String newPokemon) {
        String oldPk = trainer.getWithdrawnPokemon().name();
        Pokemon newPk = trainer.getPokemon(newPokemon);

        if (newPk.isDead()) {
            return;
        }

        trainer.setWithdrawnPokemon(newPk.name());

        gui.println(trainer.name() + " called back " + oldPk + " and sent out " + newPk + "!");
    }

    private boolean checkIfOutOfPokemon(Trainer trainer) {
        if (!trainer.getWithdrawnPokemon().isDead()) {
            return false;
        }
        gui.println(trainer.getWithdrawnPokemon().name() + " fainted!");

        Pokemon newPk = null;
        for (Pokemon pokemon : trainer.getParty()) {
            if (!pokemon.isDead()) {
                newPk = pokemon;
                break;
            }
        }

        if (newPk == null) {
            gui.println(trainer.name() + " is out of usable Pokemon! " + trainer.name() + " whited out!");
            return true;
        }

        swapPokemon(trainer, newPk.name());

        return false;
    }

    private void calculateAttack(Move move, Pokemon attacker, Pokemon defender) {
        dealDamage(move, attacker, defender);
    }

    private void dealDamage(Move move, Pokemon attacker, Pokemon defender) {
        String s = attacker.name() + " used " + move.name() + " on " + defender.name() + "!";

        float typeMultiplier = PokeCalculator.getTypeBonus(move.type(), defender.primaryType())
                * PokeCalculator.getTypeBonus(move.type(), defender.secondaryType());
        if (typeMultiplier > 1.1f) {
            s += " It's super effective!";
        } else if (typeMultiplier < .9 && typeMultiplier > .1) {
            s += " It's not very effective...";
        } else if (typeMultiplier < .1) {
            s += " It has no effect.";
        }
        gui.println(s);

        int damage = (int) (move.value() * PokeCalculator.calculateDamageMultiplier(move.type(), attacker, defender));
        if (damage < 1) {
            damage = 1;
        }

        defender.getAttributes().changeHealth(-damage);
        gui.println(defender.name() + " took " + damage + " damage! [" + defender.health() + "/" + defender.initialHealth() + "]");
    }

    public static enum TurnMode {

        FIGHT, SWAP, ITEM
    }
}
