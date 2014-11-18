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
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * Singleton class containing data about the type effectiveness of different
 * types on each other
 *
 * @author Alice Quiros
 */
public class TypeAdvantageMap {

    public static final float GENERATION = 6.0f;

    private final static TypeAdvantageMap singleton = new TypeAdvantageMap();
    private Map<TypeEnum, Map<TypeEnum, Float>> values;

    /**
     * Returns the singleton instance of this construct
     *
     * @return he singleton instance of this construct
     */
    public static TypeAdvantageMap getInstance() {
        return singleton;
    }

    /**
     * Loads the type advantage data from a CSV containing a single header row
     * and a single header column, and float values for each of these. Any
     * missing data will be filled in with 1.0
     *
     * @param directory The location of the CSV file
     * @param delim The delimiter used in the CSV. By default, most use a comma.
     * @throws IOException When an IO exception occurs
     */
    public static void loadDataFromCSV(String directory, String delim) throws IOException {
        // Open the file for reading
        File csvFile = new File(directory);
        BufferedReader br = new BufferedReader(new FileReader(csvFile));

        // Read in the header to know where everything is
        String line = br.readLine();
        ArrayList<TypeEnum> header = new ArrayList(TypeEnum.values().length);

        String[] values = line.split(delim);
        for (int i = 1; i < values.length; i++) {
            header.add(TypeEnum.valueOf(values[i]));
        }

        System.out.println(header);

        for (line = br.readLine(); line != null; line = br.readLine()) {
            values = line.split(delim);
            EnumMap<TypeEnum, Float> row = new EnumMap(TypeEnum.class);
            TypeEnum rowName = TypeEnum.valueOf(values[0]);

        }
    }
}
