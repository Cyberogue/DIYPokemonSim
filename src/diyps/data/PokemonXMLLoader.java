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

    public void loadTrainer(String directory) throws Exception {
        Document doc = dBuilder.parse(new File(directory));
        doc.getDocumentElement().normalize();

        Node root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        System.out.println(nodes.getLength());

        String trainerName;
        ArrayList party = new ArrayList();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getNodeName();
                switch (name) {
                    case NAME_NODE:
                        trainerName = node.getTextContent();
                        System.out.println(trainerName);
                        break;
                    case POKEMON_NODE:
                        loadPokemon(node);
                        break;
                }
            }
        }
    }

    private Pokemon loadPokemon(Node basenode) {
        NodeList nodes = basenode.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                String name = node.getNodeName();
                System.out.println(name);
                switch (name) {
                    case NAME_NODE:
                        break;
                    case POKEMON_NODE:
                        break;
                }
            }
        }
        return null;
    }
}
