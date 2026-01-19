# 🎮 Qui est-ce ?

> Jeu multijoueur en ligne inspiré du célèbre jeu de société "Qui est-ce ?" (Guess Who?).

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-2.1.21-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin 2.1.21">
  <img src="https://img.shields.io/badge/JavaFX-21-orange?logo=java&logoColor=white" alt="JavaFX 21">
  <img src="https://img.shields.io/badge/Gradle-KTS-02303A?logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/Ktor-3.1.3-087CFA?logo=ktor&logoColor=white" alt="Ktor">
</p>

---

## 📋 Contexte du projet

**Qui est-ce ?** est un projet réalisé dans le cadre de la **SAE 201** du BUT Informatique.

| | |
|---|---|
| 🎓 **Formation** | BUT Informatique |
| 👥 **Équipe** | 4 étudiants |
| 📅 **Année** | 2025-2026 |

### 👨‍💻 Membres de l'équipe

- DOUCET Axel
- LEBEAUPIN Loucas
- GONCALVES AMORIM Diego
- LACHAISE Mattys

---

## 🎯 Présentation

**Qui est-ce ?** est une adaptation numérique du jeu de société classique. Les joueurs s'affrontent en ligne pour deviner le personnage secret de leur adversaire en posant des questions stratégiques.

### ✨ Fonctionnalités principales

#### 🎲 Gameplay
- Création et gestion de joueurs
- Création et connexion à des parties multijoueurs
- Sélection de personnages secrets
- Système de questions/réponses en temps réel
- Grille de personnages interactive
- Détection automatique de victoire/défaite

#### 🌐 Mode multijoueur
- Communication client-serveur via Ktor
- Parties en temps réel
- Synchronisation des actions entre joueurs

#### 🎨 Interface utilisateur
- Interface graphique moderne avec JavaFX
- Navigation fluide entre les écrans
- Grille de personnages cliquable
- Système de cartes personnalisables

---

## 🛠️ Stack technique

| Composant | Technologie |
|-----------|-------------|
| **Langage** | Kotlin 2.1.21 |
| **Interface** | JavaFX 21 |
| **Build** | Gradle (KTS) |
| **Client HTTP** | Ktor 3.1.3 |
| **Sérialisation** | Kotlinx Serialization |
| **Tests** | JUnit 5 |
| **Logging** | Logback |

---

## 📁 Architecture du projet

```
2025.sae201.22/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       ├── Main.kt               # Point d'entrée de l'application
│   │       │
│   │       ├── components/           # Composants UI réutilisables
│   │       │   ├── CartePersonnage.kt
│   │       │   ├── Footer.kt
│   │       │   ├── Grille.kt
│   │       │   └── Header.kt
│   │       │
│   │       ├── controleur/           # Contrôleurs (logique événementielle)
│   │       │   ├── ControleuJeu.kt
│   │       │   ├── ControleurCreerJoueur.kt
│   │       │   ├── ControleurCreerPartie.kt
│   │       │   ├── ControleurRejoindrePartie.kt
│   │       │   └── ...
│   │       │
│   │       ├── modele/               # Modèles métier
│   │       │   ├── Modele.kt
│   │       │   ├── Partie.kt
│   │       │   ├── Grille.kt
│   │       │   ├── Question.kt
│   │       │   └── Reponse.kt
│   │       │
│   │       └── vue/                  # Vues (interface utilisateur)
│   │           ├── VueCreerJoueur.kt
│   │           ├── VueCreerRejoindrePartie.kt
│   │           ├── VueChoisirPersonnage.kt
│   │           ├── VuePartieLancee.kt
│   │           ├── VueGagner.kt
│   │           └── VuePerdu.kt
│   │
│   └── test/
│       └── kotlin/                   # Tests unitaires
│           ├── modele/
│           └── testsLibrairie/
│
├── libs/
│   └── sae-qui-est-ce-client-1.1.jar # Client réseau fourni
│
├── resources/
│   └── resources/
│       ├── but1.csv                  # Données des personnages
│       └── but1/                     # Images des personnages
│
├── documentation/
│   ├── Analyse.sdr
│   ├── ConceptionArchitecturale.puml
│   └── html/                         # Documentation générée
│
└── build.gradle.kts                  # Configuration Gradle
```

---

## ⚠️ Statut du projet

**Ce projet n'a malheureusement pas été terminé.**

Le jeu "Qui est-ce ?" devait être une application JavaFX client-serveur permettant de jouer au célèbre jeu de devinettes entre deux joueurs. Malgré les efforts investis dans la conception et le développement, plusieurs problèmes techniques n'ont pas pu être résolus dans les délais impartis, rendant le jeu non fonctionnel.

### Ce qui a été réalisé

- ✅ **Architecture client-serveur** : Structure de base avec communication HTTP
- ✅ **Modèle de données** : Classes pour gérer les joueurs, parties, personnages
- ✅ **Interface graphique** : Plusieurs vues JavaFX (création joueur, sélection partie, grille de jeu)
- ✅ **Contrôleurs** : Logique de navigation entre les écrans
- ✅ **Tests unitaires** : Batterie de tests pour valider les requêtes et le modèle
- ✅ **Documentation** : Diagrammes UML, maquettes, documentation technique

### Problèmes non résolus

- ❌ **Synchronisation client-serveur** : Problèmes de timing lors de la connexion de deux joueurs
- ❌ **Gestion d'état** : Blocages dans l'interface lors des transitions entre états de partie
- ❌ **Stabilité** : L'application se bloque fréquemment et ne permet pas de jouer complètement une partie

## 📚 Documentation

La documentation complète du projet est disponible dans le dossier `documentation/` :

- **Analyse** : Document d'analyse du projet (`Analyse.sdr`)
- **Conception** : Diagrammes UML et architecture (`ConceptionArchitecturale.puml`, `modelesUML.pdf`)
- **Maquettes** : Interfaces graphiques prévues (`maquette.pdf`, `maquette-v2.pdf`)
- **Tests** : Documentation des tests unitaires (`Tests_unitaire_Approche_Fonctionnelle_Qui-Est-Ce-Client.pdf`)
- **Testabilité** : Approche de testabilité du code (`Testabilité.pdf`)
- **Documentation code** : Javadoc/Dokka dans `documentation/html/`

## 🛠️ Technologies utilisées

- **Langage** : Kotlin 2.1.0
- **Build** : Gradle 8.9
- **Interface** : JavaFX 21
- **Client HTTP** : Ktor Client 3.1.3
- **Sérialisation** : kotlinx-serialization
- **Tests** : JUnit 5

## 📁 Structure du projet

```
2025.sae201.22/
├── src/
│   ├── main/kotlin/          # Code source de l'application
│   │   ├── controleur/       # Contrôleurs JavaFX
│   │   ├── modele/           # Modèle de données
│   │   ├── vue/              # Vues JavaFX
│   │   └── components/       # Composants réutilisables
│   └── test/kotlin/          # Tests unitaires
├── libs/                     # Bibliothèques externes
│   ├── sae-qui-est-ce-client-1.1.jar
│   └── server-all.jar
├── resources/                # Ressources (images, CSV)
├── documentation/            # Documentation du projet
└── build.gradle.kts         # Configuration Gradle
```

## 📝 Travail réalisé

### Architecture

- Implémentation du pattern MVC (Modèle-Vue-Contrôleur)
- Communication client-serveur via requêtes HTTP
- Gestion des états de partie (ATTENTE, INITIALISATION, EN_COURS, TERMINE)

### Fonctionnalités implémentées

1. **Création de joueur** : Interface et logique pour créer un compte
2. **Création de partie** : Un joueur peut créer une nouvelle partie
3. **Rejoindre une partie** : Liste des parties disponibles et possibilité de rejoindre
4. **Sélection de personnage** : Grille interactive pour choisir son personnage secret
5. **Grille de jeu** : Affichage des 24 personnages avec leurs caractéristiques

### Tests

- 75 tests unitaires couvrant :
  - Création de joueur
  - Création et gestion de parties
  - Requêtes réseau (grilles, états, questions/réponses)
  - Validation des données

## 🎓 Contexte académique

Ce projet a été réalisé dans le cadre de la **SAE 2.01** du BUT Informatique, avec pour objectif d'appliquer les compétences en :
- Programmation orientée objet (Kotlin)
- Développement d'interfaces graphiques (JavaFX)
- Architecture client-serveur
- Tests unitaires et qualité logicielle
- Travail en équipe et gestion de projet

Bien que non terminé, ce projet a permis d'acquérir de l'expérience sur les défis du développement d'applications distribuées et de l'importance de la gestion des états asynchrones.

## 🔮 Pistes d'amélioration futures

Si le projet devait être repris, voici les axes prioritaires :

1. **Refonte de la synchronisation** : Utiliser WebSockets au lieu de polling HTTP pour une meilleure réactivité
2. **Gestion des états** : Implémenter une machine à états plus robuste avec gestion des erreurs
3. **Interface asynchrone** : Mieux gérer les tâches longues pour éviter les blocages de l'interface
4. **Tests d'intégration** : Ajouter des tests bout-en-bout avec un serveur de test
5. **Configuration** : Externaliser les paramètres (IP serveur, port) dans un fichier de configuration

## 📧 Contact

Projet réalisé dans le cadre du BUT Informatique - IUT de Nantes

---


## 📚 Documentation

La documentation du code est disponible dans le dossier [documentation/html](documentation/html/index.html).

---

<p align="center">
  Projet réalisé avec 🎯 dans le cadre du BUT Informatique
</p>