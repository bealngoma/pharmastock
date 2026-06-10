# Modèle Physique de Données (MPD)

## Table : UTILISATEUR

| Colonne | Type | Contraintes |
|---------|------|-------------|
| id | Entier long | Clé primaire, auto-incrémenté |
| nom | Texte | Non nul |
| login | Texte | Non nul, unique |
| hashMotDePasse | Texte | Non nul |
| role | Texte | Non nul |
| zone | Texte | Non nul |

---

## Table : PRODUIT

| Colonne | Type | Contraintes |
|---------|------|-------------|
| id | Entier long | Clé primaire, auto-incrémenté |
| nom | Texte | Non nul |
| dosage | Texte | Non nul |
| codeBarre | Texte | Nul autorisé |
| stockTotal | Entier | Non nul, valeur par défaut 0 |

---

## Table : LOT

| Colonne | Type | Contraintes |
|---------|------|-------------|
| id | Entier long | Clé primaire, auto-incrémenté |
| numeroLot | Texte | Non nul, unique |
| produitId | Entier long | Clé étrangère vers PRODUIT(id), non nul |
| dateFabrication | Date | Non nul |
| dateExpiration | Date | Non nul |
| quantite | Entier | Non nul |

**Règle de gestion** : Si un produit est supprimé, ses lots sont également supprimés (CASCADE).

---

## Table : MOUVEMENT_STOCK

| Colonne | Type | Contraintes |
|---------|------|-------------|
| id | Entier long | Clé primaire, auto-incrémenté |
| type | Texte | Non nul (valeurs possibles : "ENTREE", "SORTIE") |
| quantite | Entier | Non nul |
| date | Date | Non nul |
| produitId | Entier long | Clé étrangère vers PRODUIT(id), non nul |
| lotId | Entier long | Clé étrangère vers LOT(id), nul autorisé |
| utilisateurId | Entier long | Clé étrangère vers UTILISATEUR(id), nul autorisé |
| destination | Texte | Nul autorisé |

**Règles de gestion** :
- Si un produit est supprimé → suppression des mouvements (CASCADE)
- Si un lot est supprimé → lotId devient nul (SET NULL)
- Si un utilisateur est supprimé → utilisateurId devient nul (SET NULL)

---

## Table : TACHE_URGENTE

| Colonne | Type | Contraintes |
|---------|------|-------------|
| id | Entier long | Clé primaire, auto-incrémenté |
| titre | Texte | Non nul |
| description | Texte | Non nul |
| priorite | Entier | Non nul (1 = haute, 5 = basse) |
| dateCreation | Date | Non nul |

---

## Liste des clés étrangères

| Table | Colonne | Référence | Comportement suppression |
|-------|---------|-----------|-------------------------|
| LOT | produitId | PRODUIT(id) | CASCADE |
| MOUVEMENT_STOCK | produitId | PRODUIT(id) | CASCADE |
| MOUVEMENT_STOCK | lotId | LOT(id) | SET NULL |
| MOUVEMENT_STOCK | utilisateurId | UTILISATEUR(id) | SET NULL |

---

## Liste des index recommandés

| Table | Colonne(s) | Raison |
|-------|------------|--------|
| LOT | produitId | Recherche des lots par produit |
| LOT | numeroLot | Recherche rapide par numéro unique |
| MOUVEMENT_STOCK | produitId | Recherche des mouvements par produit |
| MOUVEMENT_STOCK | lotId | Recherche des mouvements par lot |
| MOUVEMENT_STOCK | date | Tris et recherches par date |
| UTILISATEUR | login | Recherche par identifiant de connexion |

---

## Correspondance des types

| Type MPD | Type SQLite |
|----------|-------------|
| Entier long | INTEGER |
| Entier | INTEGER |
| Texte | TEXT |
| Date | TEXT (format ISO YYYY-MM-DD) |