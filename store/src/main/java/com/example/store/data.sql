-- Catégories parentes
INSERT INTO category (name, description, parent_id)
VALUES ('Quincaillerie', 'Tout le matériel de quincaillerie', NULL);

INSERT INTO category (name, description, parent_id)
VALUES ('Outillage Manuel', 'Outils manuels', 1);

-- Catégorie principale
INSERT INTO category (name, description, parent_id)
VALUES ('Outils de Mesure', 'Télémètres, niveaux, jauges, etc.', 2);