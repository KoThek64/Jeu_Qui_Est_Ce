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

## 🚀 Installation et lancement

### Prérequis

- **JDK 21** ou supérieur
- **Gradle** (wrapper inclus dans le projet)

### Installation

```bash
# 1. Cloner le projet
git clone https://github.com/KoThek64/Jeu_Qui_Est_Ce.git
cd 2025.sae201.22

# 2. Compiler le projet
./gradlew build
```

---

### 🎮 Lancer le jeu

Le jeu fonctionne en **architecture client-serveur**. Vous devez démarrer le serveur avant de lancer le client.

#### 📋 Configuration initiale (à faire une seule fois)

Créer le lien symbolique pour les ressources du serveur :

```bash
ln -s resources/resources files
```

#### 🖥️ Étape 1 : Démarrer le serveur

**Ouvrez un terminal** et exécutez :

**Linux/Mac** :
```bash
# Option A : Avec le script (recommandé)
./start-server.sh

# Option B : Manuellement
sudo java -jar libs/server-all.jar
```

**Windows** :
```bash
# En tant qu'administrateur
java -jar libs\server-all.jar
```

Le serveur démarre sur `http://localhost:80`

> 💡 **Note importante** : Le script demandera votre mot de passe `sudo` car le serveur utilise le port 80 (privilèges administrateur requis sur Linux/Mac)

Vous devriez voir :
```
***** Server running on 0.0.0.0:80 using resources directory: files
```

> ⚠️ **Le serveur doit rester actif** pendant toute la durée du jeu ! Ne fermez pas ce terminal.

#### 🎮 Étape 2 : Lancer le client (le jeu)

**Ouvrez un SECOND terminal** et exécutez :

**Linux/Mac** :
```bash
./gradlew run
```

**Windows** :
```bash
gradlew.bat run
```

🎉 **L'interface graphique du jeu s'ouvre automatiquement !**

---

### 🌐 Pour jouer à plusieurs

Chaque joueur doit :
1. Lancer son propre client avec `./gradlew run` (sur son ordinateur)
2. Se connecter au même serveur (modifier l'IP dans `src/main/kotlin/Main.kt` si nécessaire)
3. Les joueurs peuvent être sur le même réseau local ou sur Internet si le serveur est accessible

---

### 🧪 Tests

```bash
# Exécuter tous les tests
./gradlew test

# Les tests nécessitent le serveur actif pour tous passer :
# Terminal 1 : sudo java -jar libs/server-all.jar
# Terminal 2 : ./gradlew test

# Voir le rapport de tests dans le navigateur
xdg-open build/reports/tests/test/index.html
```

> 💡 **Note** : Les tests peuvent échouer si le serveur n'est pas démarré, mais le build réussira quand même grâce à `ignoreFailures = true`.

---

### 📦 Compilation sans exécuter les tests

```bash
./gradlew assemble
```

---

### 🔧 Dépannage

#### Problème : "Permission non accordée" sur le port 80

**Solution** : Utilisez `sudo` pour lancer le serveur
```bash
sudo java -jar libs/server-all.jar
```

#### Problème : "FileNotFoundException: files/but1.csv"

**Solution** : Créez le lien symbolique
```bash
ln -s resources/resources files
```

#### Problème : "Connexion refusée" ou "ConnectTimeoutException"

**Solutions** :
1. Vérifiez que le serveur est bien démarré (Terminal 1)
2. Le serveur doit afficher `Server running on 0.0.0.0:80`
3. Vérifiez que le port dans `Main.kt` correspond (port 80 par défaut)

#### Problème : Le jeu ne démarre pas

**Solutions** :
1. Vérifiez que JavaFX est installé : `java --list-modules | grep javafx`
2. Recompilez le projet : `./gradlew clean build`
3. Vérifiez que le serveur tourne dans l'autre terminal

---

### 🔧 Configuration réseau (optionnel)

Si vous êtes sur le réseau de l'IUT, décommentez les lignes de proxy dans `gradle.properties` :

```properties
systemProp.http.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
systemProp.http.proxyPort=3128
systemProp.https.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
systemProp.https.proxyPort=3128
```

---

## 🎮 Comment jouer

1. **Créer un joueur** : Entrez votre pseudo
2. **Créer ou rejoindre une partie** : Créez une nouvelle partie ou rejoignez une partie existante
3. **Choisir votre personnage secret** : Sélectionnez le personnage que votre adversaire devra deviner
4. **Jouer** : Posez des questions et éliminez les personnages pour trouver celui de votre adversaire
5. **Gagner** : Soyez le premier à deviner le personnage secret !

---

## 📚 Documentation

La documentation du code est disponible dans le dossier [documentation/html](documentation/html/index.html).

---

<p align="center">
  Projet réalisé avec 🎯 dans le cadre du BUT Informatique
</p>