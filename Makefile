
.PHONY: all build install clean uninstall

all: clean build

build:
		mvn package

install: 
		# copy binaries
		mkdir -p $(DESTDIR)/opt/
		cp -r target/openrqm-server  $(DESTDIR)/opt/openrqm-server
		# copy database queries
		cp -r sql $(DESTDIR)/opt/openrqm-server/sql
		# copy export templates
		cp -r templates $(DESTDIR)/opt/openrqm-server/templates
		# copy systemd service

clean:
		rm -f -r target
		rm -f -r debian/openrqm-server

uninstall:
		rm -r  $(DESTDIR)/opt/openrqm-server