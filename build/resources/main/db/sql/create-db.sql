CREATE TABLE note (
  id INTEGER PRIMARY KEY,
  nom VARCHAR(50),
  texte VARCHAR(100),
  listeId INTEGER
);

CREATE TABLE liste (
  id INTEGER NOT NULL PRIMARY KEY,
  nom VARCHAR(50),
  utilisateurID INTEGER,
  noteId INTEGER
);

ALTER TABLE note
ADD FOREIGN KEY (listeId) REFERENCES liste (id);