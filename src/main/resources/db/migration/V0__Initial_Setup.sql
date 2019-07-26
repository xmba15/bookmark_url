CREATE TABLE IF NOT EXISTS tags(
       id INT AUTO_INCREMENT,
       title VARCHAR(50) NOT NULL,
       description VARCHAR(100) DEFAULT '',
       created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       PRIMARY KEY(id),
       UNIQUE (title)
);

CREATE TABLE IF NOT EXISTS subtags(
       id INT AUTO_INCREMENT,
       title VARCHAR(50) NOT NULL,
       description VARCHAR(100) DEFAULT '',
       created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
       updated_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       PRIMARY KEY(id),
       UNIQUE (title)
);

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
       UNIQUE (url_id)
);

CREATE TABLE IF NOT EXISTS tag_subtag(
       id INT AUTO_INCREMENT,
       tag_id INT NOT NULL,
       subtag_id INT NOT NULL,
       PRIMARY KEY(id),
       FOREIGN KEY(tag_id) REFERENCES tags(id),
       FOREIGN KEY(subtag_id) REFERENCES subtags(id),
       UNIQUE (tag_id, subtag_id)
);

CREATE TABLE IF NOT EXISTS url_subtag(
       id BIGINT AUTO_INCREMENT,
       url_id BIGINT NOT NULL,
       subtag_id INT NOT NULL,
       PRIMARY KEY(id),
       FOREIGN KEY(subtag_id) REFERENCES subtags(id),
       FOREIGN KEY(url_id) REFERENCES urls(id),
       UNIQUE (url_id, subtag_id)
);

INSERT INTO tags(title) VALUES ('science');
INSERT INTO tags(title) VALUES ('technology');
INSERT INTO tags(title) VALUES ('news');
INSERT INTO tags(title) VALUES ('entertainment');
INSERT INTO tags(title) VALUES ('books');
INSERT INTO tags(title) VALUES ('miscellaneous');

INSERT INTO subtags(title) VALUES('computer vision');
INSERT INTO subtags(title) VALUES('machine learning');

INSERT INTO subtags(title) VALUES('robotics');
INSERT INTO subtags(title) VALUES('programming');

INSERT INTO subtags(title) VALUES('travelling');
INSERT INTO subtags(title) VALUES('music');
INSERT INTO subtags(title) VALUES('films');
INSERT INTO subtags(title) VALUES('camera');

INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'science'), (SELECT id from subtags WHERE title ='computer vision'));
INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'science'), (SELECT id from subtags WHERE title ='machine learning'));

INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'technology'), (SELECT id from subtags WHERE title ='robotics'));
INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'technology'), (SELECT id from subtags WHERE title ='programming'));

INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'entertainment'), (SELECT id from subtags WHERE title ='travelling'));
INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'entertainment'), (SELECT id from subtags WHERE title ='music'));
INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'entertainment'), (SELECT id from subtags WHERE title ='films'));
INSERT INTO tag_subtag(tag_id, subtag_id) VALUES((SELECT id from tags WHERE title = 'entertainment'), (SELECT id from subtags WHERE title ='camera'));

INSERT INTO urls(address) VALUES ('http://google.com');
INSERT INTO url_tag(url_id, tag_id) VALUES((SELECT id from urls WHERE address = 'http://google.com'), (SELECT id from tags WHERE title = 'technology'));
