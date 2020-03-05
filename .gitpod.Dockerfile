FROM gitpod/workspace-full:latest

USER root

# Get packages in the cache
RUN apt-get update
# Install tex packages, non-interactive and quiet
RUN apt-get -qq -y install texlive-xetex
RUN apt-get -qq -y install texlive-fonts-recommended
RUN apt-get -qq -y install texlive-fonts-extra
RUN apt-get -qq -y install texlive-latex-extra

USER gitpod
