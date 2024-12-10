package org.example.webservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.models.Adress;
import org.example.models.Avocat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@WebService(serviceName = "AvocatWS")
public class ServiceAvocat {
    private List<Avocat> avocats = new ArrayList<>();
    private Avocat avocat;

    public ServiceAvocat() {
        // Initialisation des données à partir du fichier JSON
        avocats = loadAvocatsFromJson("data.json");
    }

    // Méthode pour lire les avocats depuis le fichier JSON avec Jackson
    private List<Avocat> loadAvocatsFromJson(String filePath) {
        List<Avocat> avocatsList = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            System.out.println("Fichier JSON non trouvé: " + filePath);
            return avocatsList;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Avocat[] avocatsArray = objectMapper.readValue(inputStream, Avocat[].class);
            for (Avocat avocat : avocatsArray) {
                avocatsList.add(avocat); // Ajouter chaque avocat à la liste
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avocatsList;
    }

    // Méthode exposée pour récupérer tous les avocats
    @WebMethod
    public List<Avocat> getAllAvocats() {
        return avocats;
    }

    // Méthode exposée pour rechercher un avocat par son nom complet
    @WebMethod
    public Avocat getAvocatByName(@WebParam(name = "fullName") String fullName) {
        return avocats.stream()
                .filter(avocat -> avocat.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }

    // Méthode exposée pour ajouter un nouvel avocat
    @WebMethod
    public String addAvocat(@WebParam(name = "fullName") String fullName,
                            @WebParam(name = "address") Adress address,
                            @WebParam(name = "telephone") String telephone,
                            @WebParam(name = "email") String email) {
        avocat = new Avocat();
        avocat.setFullName(fullName);
        avocat.setAddress(address);
        avocat.setTelephone(telephone);
        avocat.setEmail(email);

        // Ajouter le nouvel avocat à la liste des avocats
        avocats.add(avocat);

        // Sauvegarder la liste mise à jour d'avocats dans le fichier JSON
        saveAvocatsToJson("data.json");

        return "Avocat ajouté avec succès !";
    }

    // Méthode pour enregistrer les données des avocats dans le fichier JSON
    private void saveAvocatsToJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Réécrire la liste complète d'avocats dans le fichier JSON
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), avocats);
        } catch (IOException e) {
            e.printStackTrace(); // Capture de l'exception si l'écriture échoue
        }
    }
}
/*

@WebService
public class ServiceAvocat {
    private List<Avocat> avocats = new ArrayList<>();
    private Avocat avocat;

    public ServiceAvocat() {
        // Initialisation des données à partir du fichier JSON
        avocats = loadAvocatsFromJson("data.json");
    }

    // Méthode pour lire les avocats depuis le fichier JSON avec Jackson
    private List<Avocat> loadAvocatsFromJson(String filePath) {
        List<Avocat> avocatsList = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            System.out.println("Fichier JSON non trouvé: " + filePath);
            return avocatsList;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Avocat[] avocatsArray = objectMapper.readValue(inputStream, Avocat[].class);
            avocatsList = List.of(avocatsArray); // Convertir le tableau en liste
        } catch (IOException e) {
            e.printStackTrace();
        }

        return avocatsList;
    }


    // Méthode exposée pour récupérer tous les avocats
    @WebMethod
    public List<Avocat> getAllAvocats() {
        return avocats;
    }

    // Méthode exposée pour rechercher un avocat par son nom complet
    @WebMethod
    public Avocat getAvocatByName(@WebParam(name = "fullName")String fullName) {
        return avocats.stream()
                .filter(avocat -> avocat.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }

    // Méthode exposée pour ajouter un nouvel avocat
    @WebMethod
    public String addAvocat(@WebParam(name = "fullName")String fullName,
                            @WebParam(name = "address") Adress address,
                            @WebParam(name = "telephone") String telephone,
                            @WebParam(name = "email") String email) {
        avocat = new Avocat();
        avocat.setFullName(fullName);
        avocat.setAddress(address);
        avocat.setTelephone(telephone);
        avocat.setEmail(email);
        avocats.add(avocat);
        System.out.println(avocat);

        //avocats.add(newAvocat);

        // Enregistrer les modifications dans le fichier JSON
        saveAvocatsToJson("receivedJsonData.json");

        return "Avocat ajouté avec succès !";
    }

    // Méthode pour enregistrer les données des avocats dans le fichier JSON
    private void saveAvocatsToJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), avocat);
        } catch (IOException e) {
            e.printStackTrace(); // Capture de l'exception si l'écriture échoue
        }
    }
}

*/