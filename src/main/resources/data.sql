INSERT INTO publishers (name, location, telephone, email) VALUES ('Tessa Bailey', 'Buzau', '0123456789', 'test@email.com');
INSERT INTO publishers (name, location, telephone, email) VALUES ('Josie Silver', 'Bucuresti', '0123456780', 'test1@email.com');
INSERT INTO publishers (name, location, telephone, email) VALUES ('Emily Stone', 'Maramures', '0123456782', 'test2@email.com');
INSERT INTO publishers (name, location, telephone, email) VALUES ('Mandy Baggot', 'Sibiu', '0123456781', 'test3@email.com');
INSERT INTO publishers (name, location, telephone, email) VALUES ('Josie Silver', 'Galati', '0123456786', 'test4@email.com');

INSERT INTO books (title, isbn, publisher_id) VALUES ('Window Shopping', '12356448556', (SELECT id FROM publishers WHERE name = 'Tessa Bailey' AND location = 'Buzau'));
INSERT INTO books (title, isbn, publisher_id) VALUES ('O zi de Decembrie', '4343299328', (SELECT id FROM publishers WHERE name = 'Josie Silver' AND location = 'Bucuresti'));
INSERT INTO books (title, isbn, publisher_id) VALUES ('Scrisoare de Craciun', '43435423328', (SELECT id FROM publishers WHERE name = 'Emily Stone' AND location = 'Maramures'));
INSERT INTO books (title, isbn, publisher_id) VALUES ('One New York Christmas', '234566328', (SELECT id FROM publishers WHERE name = 'Mandy Baggot' AND location = 'Sibiu'));
INSERT INTO books (title, isbn, publisher_id) VALUES ('One day in december', '5369299328', (SELECT id FROM publishers WHERE name = 'Josie Silver' AND location = 'Galati'));
