FROM gitpod/workspace-mysql

USER gitpod

# Install tex packages, non-interactive and quiet
RUN apt-get install -qq -y texlive-xetex
RUN apt-get install -qq -y texlive-fonts-recommended
RUN apt-get install -qq -y texlive-fonts-extra
RUN apt-get install -qq -y texlive-latex-extra
