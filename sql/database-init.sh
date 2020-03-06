 #!/bin/sh

# add openrqm user to database
echo "CREATE USER 'openrqm'@'localhost' IDENTIFIED BY '';" | mysql -u root
echo "GRANT ALL PRIVILEGES ON openrqm . * TO 'openrqm'@'localhost';" | mysql -u root
echo "FLUSH PRIVILEGES;" | mysql -u root

# execute sql files to local database
mysql -u root < /workspace/openrqm-server/sql/openrqm.sql
mysql -u root --init-command="SET SESSION FOREIGN_KEY_CHECKS=0;" < /workspace/openrqm-server/sql/openrqm-data.sql
