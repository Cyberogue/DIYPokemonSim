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

/**
 * This is where constants are stored
 *
 * @author Alice Quiros
 */
public final class DIYPokemonConstants {

    // File handling
    public static final String TRAINER1_FILE = "Trainer1.xml";
    public static final String TRAINER2_FILE = "Trainer2.xml";

    public static final String LOG_FILE = "pokemon.log";

    public static final String DATA_FILEPATH = "data/";
    public static final String IMAGE_FILEPATH = "img/";

    // IMAGE LOCATIONS
    public static final String BATTLE_BACKGROUND_IMAGE = "battlebackground.png";
    public static final String PROGRAM_ICON_IMAGE = "pokeball.png";

    // Main node names
    public static final String NAME_NODE = "name";
    public static final String POKEMON_NODE = "pokemon";
    public static final String MOVE_NODE = "move";
    public static final String ATTRIBUTES_NODE = "attributes";
    public static final String TYPE1_NODE = "type";
    public static final String TYPE2_NODE = "type2";

    // These are listed in attributes attributes
    public static final String PK_HEALTH_ATTR = "health";
    public static final String PK_ATTK_ATTR = "attack";
    public static final String PK_DEF_ATTR = "defense";
    public static final String PK_SPD_ATTR = "speed";

    // These are listed in move attributes
    public static final String MOV_NAME_ATTR = "name";
    public static final String MOV_TYPE_ATTR = "type";
    public static final String MOV_MODE_ATTR = "mode";
    public static final String MOV_VALUE_ATTR = "value";

    // GUI
    public static final int OUTER_PADDING = 10;
    public static final int INNER_PADDING = 10;

    public static final String FIGHT_SELECT_TEXT = "Fight";
    public static final String SWITCH_SELECT_TEXT = "Swap";
    public static final String ITEM_SELECT_TEXT = "Item";
    public static final String END_TURN_TEXT = "End Turn";

    // UI
    public static final String RESPONSE_MARKER = "# ";
    public static final char LINEBREAK_CHAR = '-';
    public static final int PREFERRED_LINE_LENGTH = 64;

    public static final String QUOTES_IGNORE_REGEX = "\\s+(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)"; // WTF IS THIS SHIT

}
