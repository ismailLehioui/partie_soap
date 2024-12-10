package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adress {

    @JsonProperty("rue")
    private String rue;

    @JsonProperty("code_postal_ville")
    private String codePostalVille;

    @JsonProperty("complet")
    private String complet;

    // Getters et setters
    @XmlElement
    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }
    @XmlElement
    public String getCodePostalVille() {
        return codePostalVille;
    }

    public void setCodePostalVille(String codePostalVille) {
        this.codePostalVille = codePostalVille;
    }
    @XmlElement
    public String getComplet() {
        return complet;
    }
    @Override
    public String toString() {
        return "Adress{" +
                "rue='" + rue + '\'' +
                ", codePostalVille='" + codePostalVille + '\'' +
                ", complet='" + complet + '\'' +
                '}';
    }

    public void setComplet(String complet) {
        this.complet = complet;
    }
}
