# Spring-Boot Based Web Application to Store Web URL for Browsing Later #

## MySQL Database related ##
```bash
sudo mysql --password
```
- Create url_bookmarks database with the following commands:

```
mysql> create database url_bookmarks;
mysql> create user 'meuser'@'%' identified by 'SqlPassword1232:';
mysql> grant all on url_bookmarks.* to 'meuser'@'%';
```
