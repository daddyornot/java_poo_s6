# CPE-2223-3ICS-POO_FilRouge

Ce dépôt contient le projet de départ pour les ateliers du module :

"Programation Orientée Objet (POO) / Conception Orientée Objet (COO)"

# Environnement

Afin de garantir un minimum de travail de configuration du projet, il est **très** vivement recommandé d'employer le [JDK avec JavaFX intégré](#jdk-avec-javafx-intégré) détaillé ci-dessous.

Pour faciliter sa reconnaissance par un maximum d'IDE, l'arborescence du projet suit les conventions Maven [[1](https://maven.apache.org/)].  
En employant le JDK recommandé, il n'est pas nécessaire d'employer `mvn` pour compiler ou lancer le projet : vous devriez pouvoir employer les outils habituels de votre IDE.

    TODO : à confirmer pour ce qui concerne les test unitaires (et leur dépendance à JUnit)
     -- mais leur présence dans le projet n'est de toute manière qu'expérimentale.

## JDK avec JavaFX intégré

Employer le JDK _**FX**_ 11  
d'[Azul Systems][azul-jdk] correspondant à votre plate-forme :

| Environnement | JDK FX 11 |
| --- | ---: |
| Postes CPE <sub>(Ubuntu)</sub> | `/softwares/sync/zulu11.50.19-ca-fx-jdk11.0.12-linux_x64` |
| Linux <sub>x86-64</sub> | à choisir : [.tar.gz, .deb ou .rpm](https://www.azul.com/downloads-new/?version=java-11-lts&os=linux&architecture=x86-64-bit&package=jdk-fx#zulu) |
| OSX <sub>x86-64</sub> | à choisir : [.zip, .tar.gz ou .dmg](https://www.azul.com/downloads-new/?version=java-11-lts&os=macos&architecture=x86-64-bit&package=jdk-fx#zulu) |
| OSX <sub>AArch64 ("Apple Silicon")</sub> | à choisir : [.zip, .tar.gz ou .dmg](https://www.azul.com/downloads-new/?version=java-11-lts&os=macos&architecture=arm-64-bit&package=jdk-fx#zulu) |
| Windows <sub>x86-64</sub> | à choisir : [.zip ou .msi](https://www.azul.com/downloads-new/?version=java-11-lts&os=windows&architecture=x86-64-bit&package=jdk-fx#zulu) |
| Autres       | [à choisir selon les disponibilités][azul-jdk]

[azul-jdk]: https://www.azul.com/downloads-new/?version=java-11-lts&package=jdk-fx#zulu

NB : Le projet est également parfaitement compatible avec un [JDK 1.8 + JavaFX](https://www.azul.com/downloads-new/?version=java-8-lts&package=jdk-fx#zulu).  
La version 11 étant disponible pour davantage de plate-formes (Macs à processeurs ARM, notamment), elle a été préférée.

<!--
Direct links would be (as of today, 2023-02-07, for latest Zulu release 62.17): 

https://cdn.azul.com/zulu/bin/

- zulu11.62.17-ca-fx-jdk11.0.18-linux.i686.rpm             2023-01-30 11:02  228M  
- zulu11.62.17-ca-fx-jdk11.0.18-linux.x86_64.rpm           2023-01-30 11:03  250M  
- zulu11.62.17-ca-fx-jdk11.0.18-linux_amd64.deb            2023-01-30 11:02  239M  
- zulu11.62.17-ca-fx-jdk11.0.18-linux_i386.deb             2023-01-30 11:02  218M  
- zulu11.62.17-ca-fx-jdk11.0.18-linux_i686.tar.gz          2023-01-30 11:02  257M  
- zulu11.62.17-ca-fx-jdk11.0.18-linux_x64.tar.gz           2023-01-30 11:03  282M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_aarch64.dmg         2023-01-30 11:03  262M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_aarch64.tar.gz      2023-01-30 11:04  259M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_aarch64.zip         2023-01-30 11:04  264M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_x64.dmg             2023-01-30 11:04  270M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_x64.tar.gz          2023-01-30 11:04  267M  
- zulu11.62.17-ca-fx-jdk11.0.18-macosx_x64.zip             2023-01-30 11:04  272M  
- zulu11.62.17-ca-fx-jdk11.0.18-win_i686.zip               2023-01-30 11:05  241M  
- zulu11.62.17-ca-fx-jdk11.0.18-win_x64.msi                2023-01-30 11:05  233M
- zulu11.62.17-ca-fx-jdk11.0.18-win_x64.zip                2023-01-30 11:05  264M  

We are unsure it would be considered acceptable to hard-link them,
and that would not provide the ability to get the latest-patch version anyway.

Just hope Azul will not make these inaccessible someday soon...

-->
