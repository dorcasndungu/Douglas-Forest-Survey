CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals(id SERIAL PRIMARY KEY, name VARCHAR, age VARCHAR, health VARCHAR, rangerId INTEGER,dangerStatus VARCHAR,location VARCHAR,timeSeen TIMESTAMP);
CREATE TABLE rangerSighting(id SERIAL PRIMARY KEY,rangerName VARCHAR,kwsPin VARCHAR);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;