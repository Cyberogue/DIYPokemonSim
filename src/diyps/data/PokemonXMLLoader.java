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

import static diyps.data.DIYPokemonConstants.*;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

/**
 * Class which loads Pokémon from a provided XML file
 *
 * @author Alice Quiros
 */
public class PokemonXMLLoader {

    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;

    public PokemonXMLLoader() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public Trainer loadTrainer(String filename) throws Exception {
        Document doc = dBuilder.parse(new File(DATA_FILEPATH + filename));
        doc.getDocumentElement().normalize();

        Node root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        Trainer trainer = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getNodeName();
                switch (name) {
                    case NAME_NODE:
                        trainer = new Trainer(node.getTextContent());
                        break;
                    case POKEMON_NODE:
                        if (trainer == null) {
                            continue;
                        }
                        trainer.addToParty(loadPokemon(trainer, node));
                        break;
                    default:
                        System.out.println("Unknown node " + name);
                        break;
                }
            }
        }

        return trainer;
    }

    private Pokemon loadPokemon(Trainer trainer, Node basenode) {
        NodeList nodes = basenode.getChildNodes();
        ArrayList<Move> pkMoves = new ArrayList(4);
        Attributes pkAttr = new Attributes();
        ElementType type1 = ElementType.UNKNOWN;
        ElementType type2 = null;
        String pkName = "MissingNo";

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getNodeName();
                switch (name) {
                    case NAME_NODE:
                        pkName = node.getTextContent();
                        break;
                    case TYPE1_NODE:
                        type1 = ElementType.valueOf(node.getTextContent().toUpperCase());
                        break;
                    case TYPE2_NODE:
                        type2 = ElementType.valueOf(node.getTextContent().toUpperCase());
                        break;
                    case ATTRIBUTES_NODE: {
                        NamedNodeMap attributes = node.getAttributes();
                        int health = Integer.parseInt(attributes.getNamedItem(PK_HEALTH_ATTR).getNodeValue());
                        int attack = Integer.parseInt(attributes.getNamedItem(PK_ATTK_ATTR).getNodeValue());
                        int defense = Integer.parseInt(attributes.getNamedItem(PK_DEF_ATTR).getNodeValue());
                        int speed = Integer.parseInt(attributes.getNamedItem(PK_SPD_ATTR).getNodeValue());
                        pkAttr.set(health, attack, defense, speed);
                    }
                    break;
                    case MOVE_NODE: {
                        NamedNodeMap attributes = node.getAttributes();
                        String movename = attributes.getNamedItem(MOV_NAME_ATTR).getNodeValue();
                        ElementType movetype = ElementType.valueOf(
                                attributes.getNamedItem(MOV_TYPE_ATTR).getNodeValue().toUpperCase());
                        Move.MoveMode movemode = Move.MoveMode.valueOf(
                                attributes.getNamedItem(MOV_MODE_ATTR).getNodeValue().toUpperCase());
                        int movevalue = Integer.parseInt(attributes.getNamedItem(MOV_VALUE_ATTR).getNodeValue());

                        Move move = new Move(movename, movetype, movemode, movevalue);
                        pkMoves.add(move);
                    }
                    break;

                    default:
                        System.out.println("Unknown node " + name);
                        break;
                }
            }
        }

        Pokemon pokemon = new Pokemon(trainer, pkName, pkAttr.health(), pkAttr.attack(), pkAttr.defense(), pkAttr.speed(), type1, type2);
        pokemon.addMoves(pkMoves);
        return pokemon;
    }
}
