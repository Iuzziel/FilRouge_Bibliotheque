# FilRouge Bibliotheque

### Table des matières :

1. [Exercice de restitution](#exercice-de-restitution-)
1. [Acteurs](#acteurs-)
1. [Liste des « Use Case »](#liste-des--use-case--)
1. [Réalistation](#réalistation-)
1. [Divers](#divers-)
   
### ToDo List :

* - [X] Analyse
* - [X] Interface
* - [X] Conception BDD
* - [X] Réalisation

## Exercice de restitution :

Le service culturel de la municipalité de *insérer_ville_fictive* veut améliorer la gestion des livres de ses **bibliothèques municipales** : faire en sorte que la circulation des livres d’un adhérent à l’autre soit maîtrisée et accélérée, le suivi des livres fiabilisé, les cotisations annuelles payées dans les délais. Les consultations du fonds (par titre, par auteur ou par thème) doivent être faciles à effectuer par les adhérents sur des micro-ordinateurs en libre-service, qui leur indiquent la disponibilité et l’emplacement du livre recherché, pour ce qui concerne les exemplaires stockés dans la bibliothèque où il se trouve physiquement. Pour un livre qui n’est pas en stock sur place, l’adhérent aura la possibilité de savoir s’il est en stock dans une autre bibliothèque de la ville. Il devra alors se déplacer pour l'emprunter.

### Acteurs :

Acteurs | Description | Fonctions
------------ | ------------- | -------------
Client|C’est un visiteur de la bibliothèque, il peut consulter la disponibilité des livres dans le système.| -Consulter la disponibilité d’un livre. -Consulter l’emplacement d’un livre.
Adhérent | Est définit par un nom, un prénom, une adresse complète, un indicateur pour connaitre si sa cotisation annuelle est encore valide (ainsi que la date à laquelle il l’a réglé), le nombre d’exemplaire en cours d’emprunt (maximum 3 en même temps). On inclut aussi un historique de la date de chaque emprunt qu’il a pu faire, pour d’éventuelles statistiques. | Consulter la disponibilité d’un livre; Consulter l’emplacement d’un livre.
Bibliothécaire | C’est l’interlocuteur courant des adhérents qui viennent à la bibliothèque. Il peut donc créer des comptes adhérents, effectuer les enregistrements des mouvements de livre (emprunt, et retour). | Enregistrer un emprunt; Enregistrer un retour; Créer les comptes adhérents; Met à jour les cotisations adhérentes.-Gérer les amandes; Met à jour l’état des exemplaires.
Responsable | Il s’occupe de consulter les informations sur l’état général du stock. Il gère aussi l’envoi des courriers de relance, à partir de la liste des retards que lui aura fourni le système. | Change le nombre maximum de livre emprunté; Consulte les listes de l’état du stock, ou des livres en retard.
Gestionnaire de Fond | Il s’occupe de la réception des nouveaux livres, de leur référencements, et de leur trouver la place adéquat dans la bibliothèque. | Enregistre un nouveau livre dans le fond; Attribue le thème d'un livre; Attribue l’emplacement d’un livre.
Administrateur système | Il s’occupera de la gestion des comptes employés et des éventuelles modifications ponctuelles du système. | Créer, Regarde, Met à jour, et Supprime les comptes  employés; Change les données règles (ex : Nombre max d’emprunt)
Employé | Regroupe tous les employés de la municipalité travaillant pour la bibliothèque. | Se connecte au système.

### Liste des « Use Case » :
* UC 1. – Consulter la bibliothèque des livres
* UC 2. - Enregistrer un emprunt
* UC 3. – Enregistrer un retour
* UC 4. - Gérer les adhérents
* UC 5. – Gérer le fond
* UC 6. - Afficher le statut du stock
* UC 7. - Gérer des comptes employés
* UC 8. - Se connecter

### Réalistation :
* Partie client en Java
* Partie serveur Oracle

#### Divers :
[Repo du projet](https://github.com/Iuzziel/FilRouge_Bibliotheque/)
