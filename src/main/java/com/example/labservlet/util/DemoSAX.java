package com.example.labservlet.util;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoSAX {
    public List<Client> parseXML(String filePath, String searchQuery) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ClientHandler handler = new ClientHandler();
            parser.parse(new File(filePath), handler);

            return handler.getClients().stream()
                    .filter(client -> client.getClientName().contains(searchQuery))
                    .collect(Collectors.toList());

        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    static class ClientHandler extends DefaultHandler {
        private List<Client> clients;
        private Client currentClient;
        private Address currentAddress;
        private StringBuilder content;

        public List<Client> getClients() {
            return clients;
        }

        @Override
        public void startDocument() throws SAXException {
            clients = new ArrayList<>();
            content = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Client")) {
                currentClient = new Client();
                currentClient.setAddresses(new ArrayList<Address>());
            } else if (qName.equals("Address")) {
                currentAddress = new Address();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            content.append(new String(ch, start, length).trim());
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("ClientId")) {
                currentClient.setClientId(Integer.parseInt(content.toString()));
            } else if (qName.equals("ClientName")) {
                currentClient.setClientName(content.toString());
            } else if (qName.equals("Type")) {
                currentClient.setType(ClientType.valueOf(content.toString()));
            } else if (qName.equals("Added")) {
                currentClient.setAdded(LocalDate.parse(content.toString()));
            } else if (qName.equals("Address")) {
                currentClient.getAddresses().add(currentAddress);
                currentAddress = null;
            } else if (qName.equals("id")) {
                currentAddress.setId(Integer.parseInt(content.toString()));
            } else if (qName.equals("ip")) {
                currentAddress.setIp(content.toString());
            } else if (qName.equals("mac")) {
                currentAddress.setMac(content.toString());
            } else if (qName.equals("model")) {
                currentAddress.setModel(content.toString());
            } else if (qName.equals("address")) {
                currentAddress.setAddress(content.toString());
            } else if (qName.equals("Client")) {
                clients.add(currentClient);
                currentClient = null;
            }

            content.setLength(0);
        }
    }
}
