FROM gitpod/workspace-mysql:latest

USER root

RUN apt-get update

# Install tex packages, non-interactive and quiet
RUN apt-get install -qq -y texlive-xetex
RUN apt-get install -qq -y texlive-fonts-recommended
RUN apt-get install -qq -y texlive-fonts-extra
RUN apt-get install -qq -y texlive-latex-extra

# Install Apache
#RUN apt-get install -qq -y apache2 apache2-doc libexpat1 ssl-cert

# Install PHP
RUN apt-get install -qq -y php php-common libapache2-mod-php php-curl php-dev php-gd php-gettext php-imagick php-intl php-mbstring php-mysql php-pear php-pspell php-recode php-xml php-zip

# Installl phpMyAdmin
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/reconfigure-webserver multiselect apache2'"] # Select Web Server
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/dbconfig-install boolean true'"] # Configure database for phpmyadmin with dbconfig-common
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/mysql/app-pass password phpmyadmin'"] # Set MySQL application password for phpmyadmin
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/app-password-confirm password phpmyadmin'"] # Confirm application password
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/mysql/admin-pass password'"] # MySQL Root Password
RUN ["/bin/bash", "-c", "debconf-set-selections <<< 'phpmyadmin phpmyadmin/internal/skip-preseed boolean true'"]
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get install -qq -y phpmyadmin

# Enable Mods, set permissions, restart Apache
RUN a2enmod rewrite
RUN phpenmod mbstring
RUN chown -R www-data:www-data /var/www

RUN service apache2 restart
