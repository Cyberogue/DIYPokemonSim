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
package diyps.game;

import diyps.data.*;

/**
 * Class containing data and methods related to the central core game
 * functionality
 *
 * @author Alice Quiros
 */
public class DIYPSGame {

    public final static GameLogger out = new GameLogger();

    private Trainer[] trainers;
    private Pokemon[] activePokemon;

    public DIYPSGame(String trainer1xml, String trainer2xml) {
        trainers = new Trainer[2];
        activePokemon = new Pokemon[2];
    }

    public void loadCombatants(String trainer1xml, String trainer2xml) throws Exception {
        PokemonXMLLoader xmlloader;
        try {
            xmlloader = new PokemonXMLLoader();

            out.startTask("LOADING TRAINER ONE DATA...");
            trainers[0] = xmlloader.loadTrainer(trainer1xml);
            out.completeTask(true);

            out.startTask("LOADING TRAINER TWO DATA...");
            trainers[1] = xmlloader.loadTrainer(trainer2xml);
            out.completeTask(true);
        } catch (Exception e) {
            DIYPSGame.out.println(e);
            throw e;
        }
    }

    public void start() {
        out.println("LOADED TRAINER DATA: ");
        out.println("\t" + trainers[0].name());
        for (Pokemon p : trainers[0].getParty()) {
            out.println("\t * " + p.toString());
        }

        out.println("\t" + trainers[1].name());
        for (Pokemon p : trainers[1].getParty()) {
            out.println("\t * " + p.toString());
        }

        out.println("------------------------------------------");
    }

    private void mainLoop() {

    }
}
