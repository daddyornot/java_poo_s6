#!/bin/bash
CPE_ARTEFACT_ID=ics3filrouge
CPE_PACKAGE=fr.cpe.$CPE_ARTEFACT_ID

# @see https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
mvn archetype:generate \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false \
  -DgroupId=fr.cpe.cpe2223 \
  -DartifactId=$CPE_ARTEFACT_ID \
  -Dpackage=$CPE_PACKAGE
# @see https://stackoverflow.com/questions/24399613/using-maven-archetype-generate-in-the-same-directory
echo "Moving artefact files to current directory"
mv ./$CPE_ARTEFACT_ID/* .
echo "Deleting $CPE_ARTEFACT_ID sub-directory"
rmdir ./$CPE_ARTEFACT_ID
echo "Done"
