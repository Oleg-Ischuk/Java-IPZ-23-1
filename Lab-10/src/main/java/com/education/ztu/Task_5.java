package com.education.ztu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Task_5 {
    private static final Logger logger = LoggerFactory.getLogger(Task_5.class);
    private static final String XML_FILE = "teams.xml";
    private static final String OUTPUT_FILE = "teams_modified.xml";

    public static void main(String[] args) {
        logger.info("=== ЗАВДАННЯ 5: XML парсери (DOM) ===");

        try {
            logger.info("Reading XML file: {}", XML_FILE);
            Document document = readXMLFile(XML_FILE);

            logger.info("Parsing and displaying XML content");
            displayXMLContent(document);

            logger.info("Modifying XML document");
            modifyXMLDocument(document);

            logger.info("Saving modified XML to: {}", OUTPUT_FILE);
            saveXMLFile(document, OUTPUT_FILE);

            logger.info("Reading and displaying modified XML");
            Document modifiedDocument = readXMLFile(OUTPUT_FILE);
            displayXMLContent(modifiedDocument);

            logger.info("Task completed successfully!");

        } catch (Exception e) {
            logger.error("Error processing XML", e);
            e.printStackTrace();
        }
    }

    private static Document readXMLFile(String filename) throws Exception {
        logger.debug("Creating DocumentBuilder");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        logger.debug("Parsing XML file: {}", filename);
        Document document = builder.parse(new File(filename));
        document.getDocumentElement().normalize();

        logger.info("XML file parsed successfully");
        return document;
    }

    private static void displayXMLContent(Document document) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("XML CONTENT");
        System.out.println("=".repeat(80));

        Element root = document.getDocumentElement();
        System.out.println("Root element: " + root.getNodeName());

        NodeList teamList = document.getElementsByTagName("team");
        System.out.println("Total teams: " + teamList.getLength());
        System.out.println();

        for (int i = 0; i < teamList.getLength(); i++) {
            Node teamNode = teamList.item(i);

            if (teamNode.getNodeType() == Node.ELEMENT_NODE) {
                Element teamElement = (Element) teamNode;

                String id = teamElement.getAttribute("id");
                String type = teamElement.getAttribute("type");
                String name = getElementTextContent(teamElement, "name");
                String rating = getElementTextContent(teamElement, "rating");
                String wins = getElementTextContent(teamElement, "wins");

                System.out.println("Team #" + id + " [" + type + "]");
                System.out.println("  Name: " + name);
                System.out.println("  Rating: " + rating);
                System.out.println("  Wins: " + wins);

                NodeList participants = teamElement.getElementsByTagName("participant");
                System.out.println("  Participants (" + participants.getLength() + "):");

                for (int j = 0; j < participants.getLength(); j++) {
                    Element participant = (Element) participants.item(j);
                    String pName = getElementTextContent(participant, "name");
                    String pAge = getElementTextContent(participant, "age");
                    String pRole = getElementTextContent(participant, "role");

                    System.out.println("    - " + pName + " (Age: " + pAge + ", Role: " + pRole + ")");
                }
                System.out.println();
            }
        }
        System.out.println("=".repeat(80) + "\n");
    }

    private static void modifyXMLDocument(Document document) {
        logger.debug("Starting XML modification");

        NodeList teamList = document.getElementsByTagName("team");

        for (int i = 0; i < teamList.getLength(); i++) {
            Element teamElement = (Element) teamList.item(i);

            Element ratingElement = (Element) teamElement.getElementsByTagName("rating").item(0);
            int currentRating = Integer.parseInt(ratingElement.getTextContent());
            int newRating = currentRating + 50;
            ratingElement.setTextContent(String.valueOf(newRating));
            logger.debug("Updated rating for team {} from {} to {}",
                    teamElement.getAttribute("id"), currentRating, newRating);
        }

        Element newTeam = document.createElement("team");
        newTeam.setAttribute("id", "5");
        newTeam.setAttribute("type", "student");

        Element name = document.createElement("name");
        name.setTextContent("Intellect");
        newTeam.appendChild(name);

        Element rating = document.createElement("rating");
        rating.setTextContent("1000");
        newTeam.appendChild(rating);

        Element wins = document.createElement("wins");
        wins.setTextContent("0");
        newTeam.appendChild(wins);

        Element participants = document.createElement("participants");

        Element participant1 = document.createElement("participant");
        Element pName1 = document.createElement("name");
        pName1.setTextContent("Andriy");
        Element pAge1 = document.createElement("age");
        pAge1.setTextContent("22");
        Element pRole1 = document.createElement("role");
        pRole1.setTextContent("Captain");
        participant1.appendChild(pName1);
        participant1.appendChild(pAge1);
        participant1.appendChild(pRole1);

        Element participant2 = document.createElement("participant");
        Element pName2 = document.createElement("name");
        pName2.setTextContent("Kateryna");
        Element pAge2 = document.createElement("age");
        pAge2.setTextContent("19");
        Element pRole2 = document.createElement("role");
        pRole2.setTextContent("Player");
        participant2.appendChild(pName2);
        participant2.appendChild(pAge2);
        participant2.appendChild(pRole2);

        participants.appendChild(participant1);
        participants.appendChild(participant2);
        newTeam.appendChild(participants);

        document.getDocumentElement().appendChild(newTeam);
        logger.info("Added new team 'Intellect' to XML");

        System.out.println("\n✓ XML document modified:");
        System.out.println("  - Increased all team ratings by 50");
        System.out.println("  - Added new team 'Intellect'\n");
    }

    private static void saveXMLFile(Document document, String filename) throws Exception {
        logger.debug("Creating Transformer for XML output");
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filename));

        logger.debug("Writing XML to file: {}", filename);
        transformer.transform(source, result);

        logger.info("XML file saved successfully: {}", filename);
        System.out.println("✓ XML saved to: " + filename + "\n");
    }

    private static String getElementTextContent(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}