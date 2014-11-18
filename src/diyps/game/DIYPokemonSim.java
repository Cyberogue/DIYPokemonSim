/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diyps.game;

import diyps.data.TypeAdvantageMap;

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
            TypeAdvantageMap.loadDataFromCSV("Data/typeadv.csv", ",");
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

}
