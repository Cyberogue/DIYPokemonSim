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

import diyps.game.*;
import diyps.data.*;
import diyps.data.Moves.MoveRequestHandler;

/**
 * Class containing data and methods related to the central core game
 * functionality
 *
 * @author Alice Quiros
 */
public abstract class DIYPSGame {

    public final GameLogger out = new GameLogger();

    protected final Trainer[] trainers;
    protected Pokemon[] activePokemon;
    private Trainer winner = null;
    private MoveRequestHandler mrh;

    public DIYPSGame() {
        trainers = new Trainer[2];
        activePokemon = new Pokemon[2];
        mrh = null;
    }

    public void setRequestHandler(MoveRequestHandler handler) {
        this.mrh = handler;
    }

    public void loadCombatants(String trainer1xml, String trainer2xml) throws Exception {
        PokemonXMLLoader xmlloader;
        try {
            xmlloader = new PokemonXMLLoader();

            out.startTask("LOADING TRAINER ONE DATA...");
            trainers[0] = xmlloader.loadTrainer(trainer1xml);
            activePokemon[0] = trainers[0].getPokemon(0);
            out.completeTask(true);

            out.startTask("LOADING TRAINER TWO DATA...");
            trainers[1] = xmlloader.loadTrainer(trainer2xml);
            activePokemon[1] = trainers[1].getPokemon(0);
            out.completeTask(true);
        } catch (Exception e) {
            out.println(e);
            throw e;
        }
    }

    public boolean hasWinner() {
        return (winner != null);
    }

    public Trainer getWinner() {
        return winner;
    }

    public void setWinner(Trainer trainer) {
        this.winner = trainer;
    }

    public final void start() {
        onStart();
        while (winner == null) {
            onLoop();
            mrh.handleRequests();
        }
        onEnd();
    }

    protected void onStart() {

    }

    protected abstract void onLoop();

    protected void onEnd() {

    }
}
