@echo off
mvn install:install-file -DgroupId={project.groupId} -DartifactId={project.artifactId} -Dversion={project.version} -Dpackaging=jar -Dfile={project.artifactId}-{project.version}.jar