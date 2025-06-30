

# Jeu du Pendu en Java

## Description du projet

Ce projet a été réalisé dans le cadre du *cours de Java*.  
Il implémente le jeu du *Pendu*

Le joueur doit deviner un mot choisi aléatoirement dans un fichier texte, avec un nombre d’essais limité en fonction du niveau sélectionné. À chaque erreur, une partie du pendu se dessine(directement ou pas en fonction du niveau).
---

## Architecture du projet

Le projet est structuré en deux grandes parties :

- **Partie logique (modele)**  
  Contient les règles du jeu, la sélection des mots à deviner et la vérification des propositions (classes JeuPendu et GestionnaireMots).

- **Partie graphique (vue)**  
  Gère l’interface utilisateur via Swing : fenêtres d’accueil, choix du niveau, affichage du pendu et fin de partie.

Un package audio a été ajouté pour jouer des sons lors des événements (lettre correcte, erreur, victoire, défaite).

---

## Compilation et exécution

### Compilation

Depuis le dossier racine du projet :

#### Windows (cmd)

javac -d bin src\Main.java src\modele*.java src\vue*.java src\audio*.java

#### Linux / Mac

javac -d bin src/Main.java src/modele/.java src/vue/.java src/audio/*.java

### Exécution

java -cp bin Main

Lancer les commandes depuis le dossier racine garantit que le programme trouve bien ressources/mots.txt et les fichiers audio.

---

## Répartition des tâches

Pour respecter les délais du cours et faciliter le développement, le projet a été réparti en deux grandes parties :

- *La logique du jeu*, centrée sur la gestion des règles et du mot à deviner.
- *L’interface graphique*, dédiée à l’interaction utilisateur via Swing et à la lecture des sons.

Chaque membre du groupe a travaillé sur une ou plusieurs classes. Le projet a ensuite été harmonisé et intégré pour former une application complète qui marche.

---

## Licence

Toute utilisation sans permission de ce compte va vous couter cher !!!!


