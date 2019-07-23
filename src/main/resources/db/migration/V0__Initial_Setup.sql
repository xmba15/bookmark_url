CREATE TABLE IF NOT EXISTS url_bookmarks.tags(
       id INT AUTO_INCREMENT,
       title VARCHAR(50) NOT NULL,
       description VARCHAR(100) DEFAULT '',
       created_at DATETIME NOT NULL,
       updated_at DATETIME NOT NULL,
       PRIMARY KEY(id)
);
