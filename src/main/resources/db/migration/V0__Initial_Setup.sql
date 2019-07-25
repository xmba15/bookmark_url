CREATE TABLE IF NOT EXISTS tags(
       id INT AUTO_INCREMENT,
       title VARCHAR(50) NOT NULL,
       description VARCHAR(100) DEFAULT '',
       created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       PRIMARY KEY(id),
       UNIQUE (title)
);

INSERT INTO tags(title) VALUES ('science');
INSERT INTO tags(title) VALUES ('technology');
INSERT INTO tags(title) VALUES ('news');
INSERT INTO tags(title) VALUES ('entertainment');
INSERT INTO tags(title) VALUES ('books');
INSERT INTO tags(title) VALUES ('miscellaneous');

CREATE TABLE IF NOT EXISTS urls(
       id BIGINT AUTO_INCREMENT,
       address VARCHAR(2083) NOT NULL,
       description VARCHAR(500) DEFAULT '',
       created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       PRIMARY KEY(id),
       UNIQUE(address)
);

CREATE TABLE IF NOT EXISTS url_tag(
       id BIGINT AUTO_INCREMENT,
       url_id BIGINT NOT NULL,
       tag_id INT NOT NULL,
       PRIMARY KEY(id),
       FOREIGN KEY(tag_id) REFERENCES tags(id),
       FOREIGN KEY(url_id) REFERENCES urls(id),
       UNIQUE (url_id, tag_id)
);
