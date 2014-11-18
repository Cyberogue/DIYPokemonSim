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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Singleton class containing data about the type effectiveness of different
 * types on each other
 *
 * @author Alice Quiros
 */
public abstract class PokeCalculator {

    public static final float GENERATION = 6.0f;

    private static Map<ElementType, Map<ElementType, Float>> multiplierMap;

    /**
     * Loads the type advantage data from a CSV containing a single header row
     * and a single header column, and float values for each of these. Any
     * missing data will be filled in with 1.0
     *
     * @param directory The location of the CSV file
     * @param delim The delimiter used in the CSV. By default, most use a comma.
     * @throws IOException When an IO exception occurs
     */
    public static void loadTypeDataFromCSV(String directory, String delim) throws IOException {
        // Open the file for reading
        File csvFile = new File(directory);
        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        // Read in the header to know where everything is
        String line = br.readLine();
        ArrayList<ElementType> header = new ArrayList(ElementType.values().length);

        String[] values = line.split(delim);
        for (int i = 1; i < values.length; i++) {
            header.add(ElementType.valueOf(values[i]));
        }

        EnumMap<ElementType, Map<ElementType, Float>> rows = new EnumMap(ElementType.class);
        // Load in each individuaul row
        for (line = br.readLine(); line != null; line = br.readLine()) {
            values = line.split(delim);
            EnumMap<ElementType, Float> row = new EnumMap(ElementType.class);
            ElementType rowName = ElementType.valueOf(values[0]);

            for (int i = 1; i < values.length; i++) {
                row.put(header.get(i - 1), Float.parseFloat(values[i]));
            }

            rows.put(rowName, row);
        }

        multiplierMap = Collections.unmodifiableMap(rows);
    }

    /**
     * Loads the type advantage data from a CSV containing a single header row
     * and a single header column, and float values for each of these. Any
     * missing data will be filled in with 1.0
     *
     * @param directory The location of the CSV file
     * @param delim The delimiter used in the CSV. By default, most use a comma.
     * @param ps After loading the data, the program will then print its entire
     * contents to the provided PrintStream
     * @throws IOException When an IO exception occurs
     */
    public static void loadTypeDataFromCSV(String directory, String delim, PrintStream ps) throws IOException {
        loadTypeDataFromCSV(directory, delim);

        ps.print("\n\t");
        for (ElementType type : ElementType.values()) {
            String name = (type.name().length() > 7 ? type.name().substring(0, 5) + ".." : type.name());
            ps.print(name + "\t");
        }

        for (ElementType attk : ElementType.values()) {
            String name = (attk.name().length() > 7 ? attk.name().substring(0, 5) + ".." : attk.name());
            ps.print("\n" + name + "\t");
            for (ElementType def : ElementType.values()) {
                ps.print(getTypeBonus(attk, def) + "\t");
            }
        }

        ps.println();
    }

    /**
     * Returns the pre-loaded damage multiplier when attacking
     *
     * @param attackType the type of the attack move
     * @param defenseType the type of the defender Pokémon
     * @return the pre-loaded damage multiplier when attacking
     */
    public static float getTypeBonus(ElementType attackType, ElementType defenseType) {
        Map<ElementType, Float> attackMap = multiplierMap.get(attackType);
        Float multiplier;
        if (defenseType == null || attackMap == null || ((multiplier = attackMap.get(defenseType)) == null)) {
            return 1f;
        } else {
            return multiplier;
        }
    }

    /**
     * calculates the total damage multiplier of an attack from one Pokémon to
     * another, taking into account type advantages, STAB and current
     * attack/defense values
     *
     * @param attackType The type of the attacking move
     * @param attacker the attributes of the attacking Pokémon
     * @param defender the attributes of the defending Pokémon
     * @return the total damage multiplier of an attack from one Pokémon to
     * another
     */
    public static float calculateDamageMultiplier(ElementType attackType, Pokemon attacker, Pokemon defender) {
        float multiplier = 1f;

        if (attackType == attacker.primaryType() || attackType == attacker.secondaryType()) {
            multiplier = 1.5f;
        }

        multiplier *= getTypeBonus(attackType, defender.primaryType());
        multiplier *= getTypeBonus(attackType, defender.secondaryType());
        multiplier *= (float) attacker.getAttributes().attack() / defender.getAttributes().defense();

        return multiplier;
    }
}
