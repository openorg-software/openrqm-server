
.PHONY: all build install clean uninstall

all: clean build

build:
		export JAVA_HOME=$$(readlink -f /usr/bin/javac | sed "s:/bin/javac::") && mvn package

install: 
		# copy binaries
		mkdir -p $(DESTDIR)/opt/openrqm-server/
		cp -r target/openrqm-server-1.0.0.jar  $(DESTDIR)/opt/openrqm-server/openrqm-server-1.0.0.jar
		# copy start script and set as executable
		cp systemd/run-server.sh $(DESTDIR)/opt/openrqm-server/run-server.sh
		chmod 755 $(DESTDIR)/opt/openrqm-server/run-server.sh
		# copy database queries
		cp -r sql $(DESTDIR)/opt/openrqm-server/sql
		# copy export templates
		cp -r templates $(DESTDIR)/opt/openrqm-server/templates
		# copy systemd service
		mkdir -p $(DESTDIR)/etc/systemd/system/
		cp -r systemd/openrqm-server.service $(DESTDIR)/etc/systemd/system/openrqm-server.service

clean:
		rm -f -r target
		rm -f -r debian/openrqm-server

uninstall:
		rm -r  $(DESTDIR)/opt/openrqm-server
		rm -r  $(DESTDIR)/etc/systemd/system/openrqm-server.service