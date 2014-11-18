/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diyps.game;

import diyps.data.PokeCalculator;
import diyps.data.Pokemon;
import diyps.data.PokemonXMLLoader;
import diyps.data.ElementType;
import diyps.data.Move;
import diyps.data.Trainer;

/**
 * Class containing the main entry point for the program
 *
 * @author Rogue <Alice Q.>
 */
public class DIYPokemonSim {

    /**
     * DIYPokemonSim entry point for the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PokeCalculator.loadTypeDataFromCSV("Data/typeadv.csv", ",");
            System.out.println("Successfully loaded data");

            PokemonXMLLoader loader = new PokemonXMLLoader();
            Trainer alice = loader.loadTrainer("Data/pikachu.xml");

            System.out.println(alice);

            for (Pokemon pokemon : alice.getParty()) {
                System.out.println("* " + pokemon);
                for (Move move : pokemon.getMoveset()) {
                    System.out.println("\t* " + move);
                }
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}