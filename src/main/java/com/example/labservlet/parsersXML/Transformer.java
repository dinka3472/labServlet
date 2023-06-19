package com.example.labservlet.parsersXML;

import com.example.labservlet.models.entitys.Address;
import com.example.labservlet.models.entitys.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class Transformer {
    public static void transformToXML(List<Client> clients, String filePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Clients");
            doc.appendChild(rootElement);

            for (Client client : clients) {
                Element clientElement = doc.createElement("Client");
                rootElement.appendChild(clientElement);

                Element clientIdElement = doc.createElement("ClientId");
                clientIdElement.appendChild(doc.createTextNode(client.getClientId().toString()));
                clientElement.appendChild(clientIdElement);

                Element nameElement = doc.createElement("ClientName");
                nameElement.appendChild(doc.createTextNode(client.getClientName()));
                clientElement.appendChild(nameElement);

                Element typeElement = doc.createElement("Type");
                typeElement.appendChild(doc.createTextNode(client.getType().toString()));
                clientElement.appendChild(typeElement);

                Element addedElement = doc.createElement("Added");
                addedElement.appendChild(doc.createTextNode(client.getAdded().toString()));
                clientElement.appendChild(addedElement);

                Element addressesElement = doc.createElement("Addresses");
                clientElement.appendChild(addressesElement);

                List<Address> addresses = client.getAddresses();

                for (Address address : addresses) {
                    Element addressElement = doc.createElement("Address");
                    addressesElement.appendChild(addressElement);

                    Element idElement = doc.createElement("id");
                    idElement.appendChild(doc.createTextNode(address.getId().toString()));
                    addressElement.appendChild(idElement);

                    Element ipElement = doc.createElement("ip");
                    ipElement.appendChild(doc.createTextNode(address.getIp()));
                    addressElement.appendChild(ipElement);

                    Element macElement = doc.createElement("mac");
                    macElement.appendChild(doc.createTextNode(address.getMac()));
                    addressElement.appendChild(macElement);

                    Element modelElement = doc.createElement("model");
                    modelElement.appendChild(doc.createTextNode(address.getModel()));
                    addressElement.appendChild(modelElement);

                    Element addressValueElement = doc.createElement("address");
                    addressValueElement.appendChild(doc.createTextNode(address.getAddress()));
                    addressElement.appendChild(addressValueElement);


                }
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
            //String filePath = "Clients.xml";
            File file = new File(filePath);

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

            System.out.println("XML-документ сохранен.");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
