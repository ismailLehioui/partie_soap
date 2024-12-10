package org.example;

import jakarta.xml.ws.Endpoint;
import org.example.webservice.ServiceAvocat;

public class ServerJaxWS {
    public static void main(String[] args) {
        String url = "http://0.0.0.0:7000/";


        // Publier le service web
        ServiceAvocat sa = new ServiceAvocat(); // Publier la classe ServiceAvocat

        System.out.println("Liste des avocats récupérés depuis le fichier JSON :");
        //sa.getAllAvocats().forEach(System.out::println);
        // Publication du service web
        Endpoint.publish(url, sa);

        System.out.println("Web service déployé sur " + url);
    }
}
