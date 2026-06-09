# PharmaStock - Application Mobile de Gestion de Dépôt Pharmaceutique

**Version :** 3.0  
**Statut :** Livraison académique (Juin 2026)  
**Plateforme :** Android (Kotlin)  
**Architecture :** MVVM + Offline-First + API REST  

---

## Synopsis

**PharmaStock** est une application mobile Android native dédiée à la gestion rigoureuse des flux de stocks dans un dépôt de distribution pharmaceutique.  
Conçue pour fonctionner **prioritairement hors ligne**, elle permet aux magasiniers de :

- Scanner des codes-barres (GS1-128 / QR codes) à la réception des lots.
- Allouer automatiquement une zone de stockage en fonction des contraintes de conservation (ex. : chaîne du froid).
- Préparer les commandes destinées aux officines.
- Réaliser des inventaires tournants depuis un terminal mobile.

Côté supervision, l’application offre un tableau de bord en temps réel (après synchronisation) permettant à la direction d’identifier les **ruptures imminentes**, les **lots périmés** et l’activité des préparateurs.

---

## Objectif stratégique du projet

> Développer une solution logistique fiable, sécurisée et traçable, capable de fonctionner sans connexion réseau sur le terrain, puis de synchroniser automatiquement toutes les opérations avec un serveur central supervisé par la Maîtrise d’Ouvrage (MOA).

### Contraintes métiers principales

- **Authenticité des données** : simulation réaliste d’un catalogue pharmaceutique (≥15 à 20 mouvements initiaux) avec mentions de conservation (ex. : Insuline 2-8°C).
- **Visibilité immédiate** :
  - 🔴 **Rouge** : Périmé / Rupture de stock  
  - 🟠 **Orange** : Périme dans ≤3 mois  
  - 🟢 **Vert** : Conforme / Stock suffisant  
  - 🔵 **Bleu** : Chaîne du froid (2-8°C)  
  - ⚪ **Gris** : Température ambiante
- **Traçabilité par lot** : recherche instantanée d’un lot pour enquête sanitaire ou rappel national.

---

## Architecture technique (imposée par la MOA)

| Composant               | Technologie / Choix imposé                     |
|-------------------------|------------------------------------------------|
| **Frontend mobile**     | Android natif (Kotlin) – XML pour les layouts  |
| **Architecture mobile** | MVVM (Model-View-ViewModel)                    |
| **Base locale**         | PostgreSQL (via SQLite / Room)                 |
| **Backend**             | API REST (Node.js / PHP OO / Python)           |
| **Synchronisation**     | Offline-First avec reprise automatique         |
| **Hébergement Cloud**   | Render, Railway ou équivalent                  |
| **Authentification**    | Jeton sécurisé ou code PIN unique par agent    |

---

## Fonctionnalités clés (périmètre livré)

### 1. Module de réception & scan
- Ouverture caméra → scan code-barres → saisie lot + péremption → suggestion automatique de zone de stockage.

### 2. Dashboard interne (par zone logistique)
- Liste des tâches prioritaires (commandes à préparer, inventaires du jour).
- Cartes dynamiques cliquables avec emplacement précis des articles.

### 3. Moteur de recherche & traçabilité
- Recherche par **numéro de lot**.
- Affichage instantané de l’historique des mouvements (qui, quand, quelle étagère).

### 4. Mode hors ligne (Offline-First)
- Toute opération est enregistrée localement.
- Synchronisation automatique et silencieuse dès que le réseau est disponible.

---

## Livrables inclus dans ce dépôt

| Fichier / Dossier                     | Description                                                                 |
|---------------------------------------|-----------------------------------------------------------------------------|
| `/android-app`                        | Code source Android (Kotlin, layouts XML, ViewModels, Room DAO)            |
| `/backend-api`                        | API REST de synchronisation + scripts SQL (structure centrale)             |
| `README.md`                           | Ce fichier – documentation synthétique du projet                           |
| `MCD_MPD_pharmastock.png`             | Schéma de la base de données (modèle conceptuel / physique)                |
| `PharmaStock.apk`                     | Build de production (installable sur Android)                              |
| `LIEN_YOUTUBE_DEMO.txt`               | Lien vers la vidéo de démonstration (parcours complet offline → sync)      |

> **Vidéo de démonstration** : [cliquer ici pour voir le parcours magasinier (scan, stockage offline, synchronisation serveur)](https://www.youtube.com/...)

---

## Modèle de données simplifié (extrait)

```text
ZONE (id_zone, nom, type_conservation, responsable_id)
PRODUIT (id_produit, nom_molecule, dosage, condition_conservation)
LOT (id_lot, numero_lot, id_produit, date_fabrication, date_peremption)
MOUVEMENT (id_mvt, id_lot, id_zone_source, id_zone_destination, agent_id, horodatage, type_operation)
