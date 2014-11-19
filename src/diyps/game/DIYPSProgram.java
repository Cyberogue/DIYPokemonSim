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
/**
 *
 * @author Alice Quiros
 */
package diyps.game;

import diyps.data.PokeCalculator;
import static diyps.data.DIYPokemonConstants.*;
import java.io.IOException;

/**
 * Class containing the main entry point for the program
 *
 * @author Rogue <Alice Q.>
 */
public class DIYPSProgram {

    /**
     * DIYPokemonSim entry point for the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            DIYPSGame.out.println("GAME START");
            DIYPSGame game = new DIYPSGame(TRAINER1_FILE, TRAINER2_FILE);
            game.loadCombatants(TRAINER1_FILE, TRAINER2_FILE);

            DIYPSGame.out.startTask("LOADING TYPE DATA...");
            PokeCalculator.loadTypeDataFromCSV("typeadv.csv", ",");
            DIYPSGame.out.completeTask(true);

            game.start();
        } catch (Exception e) {
            DIYPSGame.out.completeTask(false);
            DIYPSGame.out.println(e);
            e.printStackTrace();
        }
    }
}
