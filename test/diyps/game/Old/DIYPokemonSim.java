/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diyps.game.Old;

import diyps.data.ElementType;
import diyps.data.PokeCalculator;
import diyps.data.Pokemon;
import diyps.data.PokemonXMLLoader;
import diyps.data.Move;
import diyps.data.Trainer;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        try {/**
            PokeCalculator.loadTypeDataFromCSV("typeadv.csv", ",");
            System.out.println("Successfully loaded data");

            PokemonXMLLoader loader = new PokemonXMLLoader();
            Trainer trainer1 = loader.loadTrainer("Trainer1.xml");
            Trainer trainer2 = loader.loadTrainer("Trainer2.xml");

            DIYPokemonGame game = new DIYPokemonGame(trainer1, trainer2);
            DIYPokemonGUI frame = new DIYPokemonGUI(game, trainer1, trainer2);
            
            game.setGUI(frame);
            frame.setVisible(true);**/
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
