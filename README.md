[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/openrqm/openrqm-server) 

# OpenRQM server
The OpenRQM server implementation.

[![Build Status](https://dev.azure.com/OpenRQM/OpenRQM/_apis/build/status/openrqm.openrqm-server?branchName=development)](https://dev.azure.com/OpenRQM/OpenRQM/_build/latest?definitionId=3&branchName=development) [![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fopenrqm%2Fopenrqm-server.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fopenrqm%2Fopenrqm-server?ref=badge_shield) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=openrqm_openrqm-server&metric=alert_status)](https://sonarcloud.io/dashboard?id=openrqm_openrqm-server)

## Content

## How to build and run

This project is written in [Java](openjdk.java.net) and can be build using [Maven](maven.apache.org). The source code for the API methods is generated with [Swagger](swagger.io), the corresponding api specification can be found in the `api` directory and at [SwaggerHub](https://app.swaggerhub.com/apis/OpenRQM/OpenRQM/1.0.0).

### Build

Run `mvn package` to build the project. The build artifacts will be stored in the `target` directory.

### Run

Run `java -jar target/openrqm-server-1.0.0.jar` to start the OpenRQM server. Navigate to `http://localhost:8090/api/v1/` for an overview of the api methods.

## Design & Architecture

The design and architecture is described in the documents in the `doc` directory.

## Features

| Feature                                    | Status  | Release |
| ------------------------------------------ | ------- | ------- |
| Basic workspace explorer & document viewer | in work | MVP     |
| Linking                                    | in work | MVP     |
| PDF export                                 | in work | MVP     |
| Multiple views per document                |         |         |
| Baselining                                 |         |         |
| Shared edit                                |         |         |
| Multimedia content                         |         |         |
| Tracing Graphs                             |         |         |

## License

SPDX-License-Identifier: GPL-2.0-only

[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fopenrqm%2Fopenrqm-server.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fopenrqm%2Fopenrqm-server?ref=badge_large)

## Copyright

Copyright (C) 2019 Marcel Jähn
