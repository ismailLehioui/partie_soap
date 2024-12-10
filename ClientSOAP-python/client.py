import zeep

# URL du fichier WSDL (vous pouvez également charger un fichier WSDL local si nécessaire)
wsdl_url = 'http://localhost:7000/?wsdl'

# Créer un client zeep
client = zeep.Client(wsdl=wsdl_url)

# Appeler une méthode du service, par exemple, "getAllAvocats"
response = client.service.getAllAvocats()
print(response)

print("\n\n\n ***********l'ajout de l'avocat*************\n\n\n")
response2 = client.service.addAvocat(
    "ismail lehioui",
    {"rue": "123", "codePostalVille": "Paris","complet": "123 Main St"},
    "123456789",
    "jane.doe@example.com"
)
print(response2)

print("\n\n\n ***********recherche de l'avocat par son nom*************\n\n\n")

response3 = client.service.getAvocatByName('ismail lehioui')

# Afficher la réponse
print(response3)
