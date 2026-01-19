#!/bin/bash

# Script pour démarrer le serveur Qui-Est-Ce
# Date: 2026-01-19

set -e

echo "🎮 Démarrage du serveur Qui-Est-Ce"
echo "===================================="
echo ""

# Vérifier qu'on est dans le bon répertoire
if [ ! -f "libs/server-all.jar" ]; then
    echo "❌ Erreur: Le fichier libs/server-all.jar n'existe pas"
    echo "Assurez-vous d'être dans le répertoire racine du projet"
    exit 1
fi

# Créer le lien symbolique si nécessaire
if [ ! -e "files" ]; then
    echo "📁 Création du lien symbolique files -> resources/resources"
    ln -s resources/resources files
    echo "✅ Lien symbolique créé"
else
    echo "✅ Lien symbolique files existe déjà"
fi

echo ""
echo "🚀 Démarrage du serveur sur le port 80..."
echo "   (Nécessite les privilèges sudo)"
echo "   Utilisez Ctrl+C pour arrêter le serveur"
echo ""

# Démarrer le serveur avec sudo (port 80 nécessite root)
sudo java -jar libs/server-all.jar
