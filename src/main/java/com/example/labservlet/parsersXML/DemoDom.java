package com.example.labservlet.parsersXML;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import com.example.labservlet.models.enums.ClientType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DemoDom {
    public List<Client> parseXML(String filePath, String searchQuery) {
        List<Client> clients = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filePath);

            NodeList clientList = document.getElementsByTagName("Client");

            for (int i = 0; i < clientList.getLength(); i++) {
                Node clientNode = clientList.item(i);
                if (clientNode.getNodeType() == Node.ELEMENT_NODE ) {
                    Element clientElement = (Element) clientNode;
                    if (clientElement.getElementsByTagName("ClientName").item(0).getTextContent().toLowerCase().contains(searchQuery.toLowerCase())) {
                        String clientId = clientElement.getElementsByTagName("ClientId").item(0).getTextContent();
                        String clientName = clientElement.getElementsByTagName("ClientName").item(0).getTextContent();
                        String clientType = clientElement.getElementsByTagName("Type").item(0).getTextContent();
                        String addedDate = clientElement.getElementsByTagName("Added").item(0).getTextContent();
                        Client client = new Client();
                        client.setClientId(Integer.parseInt(clientId));
                        client.setClientName(clientName);
                        client.setType(ClientType.valueOf(clientType));
                        client.setAdded(LocalDate.parse(addedDate));
                        client.setAddresses(new ArrayList<Address>());

                        NodeList addressList = clientElement.getElementsByTagName("Address");
                        for (int j = 0; j < addressList.getLength(); j++) {
                            Node addressNode = addressList.item(j);
                            if (addressNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element addressElement = (Element) addressNode;
                                String addressId = addressElement.getElementsByTagName("id").item(0).getTextContent();
                                String addressIp = addressElement.getElementsByTagName("ip").item(0).getTextContent();
                                String addressMac = addressElement.getElementsByTagName("mac").item(0).getTextContent();
                                String addressModel = addressElement.getElementsByTagName("model").item(0).getTextContent();
                                String addressValue = addressElement.getElementsByTagName("address").item(0).getTextContent();

                                Address address = new Address();
                                address.setId(Integer.parseInt(addressId));
                                address.setIp(addressIp);
                                address.setMac(addressMac);
                                address.setModel(addressModel);
                                address.setAddress(addressValue);

                                client.getAddresses().add(address);
                            }
                        }
                        clients.add(client);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return clients;
    }
}
