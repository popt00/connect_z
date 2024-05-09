CREATE DATABASE connect_z;
use connect_z;

CREATE TABLE z_user (
                        id   INTEGER    NOT NULL AUTO_INCREMENT,
                        username  VARCHAR(64) NOT NULL,
                        email  VARCHAR(128),
                        name  VARCHAR(64),
                        age INTEGER,
                        password VARCHAR(68) NOT NULL,
                        reso_id INTEGER,

                        PRIMARY KEY (id),
                        CONSTRAINT FK_ResonanceOrder FOREIGN KEY (reso_id)
                        REFERENCES z_user(id)
);


CREATE TABLE z_series (
                          id   INTEGER    NOT NULL,
                          name  VARCHAR(256) NOT NULL,
                          url  VARCHAR(512) NOT NULL,

                          PRIMARY KEY (id)
);

CREATE TABLE z_main (
                        id INTEGER NOT NULL AUTO_INCREMENT,
                        user_id   INTEGER,
                        series_id   INTEGER,
                        rating INTEGER,
                        series_status  VARCHAR(10),

                        PRIMARY KEY (id),

                        CONSTRAINT FK_UserOrder FOREIGN KEY(user_id)
                            REFERENCES z_user(id),
                        CONSTRAINT FK_SeriesOrder FOREIGN KEY(series_id)
                            REFERENCES z_series(id)
);

CREATE TABLE z_id_keeper (
                             max_user_id   INTEGER    NOT NULL,
                             max_series_id   INTEGER    NOT NULL
);

INSERT INTO  z_id_keeper(max_user_id, max_series_id) VALUES (0,0);

