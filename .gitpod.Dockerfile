FROM gitpod/workspace-mysql

#USER root

# Get packages in the cache
#RUN apt-get update

# Install JDK 11
#RUN apt-get install -qq -y default-jdk

# Install tex packages, non-interactive and quiet
#RUN apt-get install -qq -y texlive-xetex
#RUN apt-get install -qq -y texlive-fonts-recommended
#RUN apt-get install -qq -y texlive-fonts-extra
#RUN apt-get install -qq -y texlive-latex-extra

# Install maria-db
#RUN apt-get install -qq -y mariadb-server

# enable TCP in MariaDB installation
#RUN sed -i 's/#port                   = 3306/port                    = 3306/g' /etc/mysql/mariadb.conf.d/50-server.cnf

# add openrqm user to database
#RUN echo "CREATE USER 'openrqm'@'localhost' IDENTIFIED BY '';" | sudo mysql -u root
#RUN echo "GRANT ALL PRIVILEGES ON openrqm . * TO 'openrqm'@'localhost';" | sudo mysql -u root
#RUN echo "FLUSH PRIVILEGES;" | sudo mysql -u root

# execute sql files to local database
#RUN sudo mysql -u root < /opt/openrqm-server/sql/openrqm-data.sql
#RUN sudo mysql -u root --init-command="SET SESSION FOREIGN_KEY_CHECKS=0;" < /opt/openrqm-server/sql/openrqm-data.sql
