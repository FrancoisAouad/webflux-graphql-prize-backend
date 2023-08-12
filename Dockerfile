# /*******************************************************************************
#  *
#  * Copyright (c) {2022-2023} Francois J. Aouad.
#  * All rights reserved. This program and the accompanying materials
#  * are made available under the terms of the GNU General Public License v3.0
#  * which accompanies this distribution, and is available at
#  * https://www.gnu.org/licenses/gpl-3.0.en.html
#  *
#  *******************************************************************************/

FROM maven:3.8-amazoncorretto-17 AS maven
LABEL MAINTAINER="boilerplate@gmail.com"
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package
EXPOSE 9000
# For Java 17
FROM amazoncorretto:17-alpine
ARG JAR_FILE=app-1.0.0.jar
WORKDIR /opt/app
# Copy the jar from the maven stage to the /opt/app directory of the current stage.
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-jar","app-1.0.0.jar"]
