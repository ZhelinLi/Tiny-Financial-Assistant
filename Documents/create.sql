CREATE TABLE Type (
    typeID INT,
    name VARCHAR (40),
    PRIMARY KEY (typeID)
);

CREATE TABLE Food (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Transportation (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Study (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Housing (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Entertainment (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Shopping (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Cleaning (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Personal (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Hobby (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE Other (
    ID INT,
    price NUMERIC (8,2),
    date TIMESTAMP,
    note VARCHAR (60),
    PRIMARY KEY (ID)
);

CREATE TABLE List (
    typeID INT,
    ID INT,
    PRIMARY KEY (typeID, ID),
    FOREIGN KEY (typeID) REFERENCES Type,
    FOREIGN KEY (ID) REFERENCES Food,
    FOREIGN KEY (ID) REFERENCES Transportation,
    FOREIGN KEY (ID) REFERENCES Study,
    FOREIGN KEY (ID) REFERENCES Housing,
    FOREIGN KEY (ID) REFERENCES Entertainment,
    FOREIGN KEY (ID) REFERENCES Shopping,
    FOREIGN KEY (ID) REFERENCES Cleaning,
    FOREIGN KEY (ID) REFERENCES Personal,
    FOREIGN KEY (ID) REFERENCES Hobby,
    FOREIGN KEY (ID) REFERENCES Other,
)