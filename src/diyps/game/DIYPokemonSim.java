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
            loader.loadTrainer("Data/pikachu.xml");
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        Pokemon pikachu = new Pokemon("Pikachu", 35, 55, 40, 90, ElementType.Electric);
        Pokemon charmander = new Pokemon("Charmander", 39, 52, 43, 65, ElementType.Fire);
        Pokemon gyarados = new Pokemon("Gyarados", 95, 125, 79, 81, ElementType.Water, ElementType.Flying);
    }
}
