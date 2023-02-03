# CPE-2223-3ICS-POO_FilRouge

Ce dépôt contient le projet de départ pour les ateliers du module :

"Programation Orientée Objet (POO) / Conception Orientée Objet (COO)"

## IDE-specific tips

### IntelliJ

Directement à partir d'IntelliJ, importer le projet depuis GitHub.  
> __Répondre OUI__ à la question "__Trust and open Maven Project__" ?


Le projet vient avec une "Run configuration" __ics3filrouge [javafx:run]__  
qui permet de lancer 
la cible maven `javafx:run` ; à utiliser pour lancer le projet.

> Pour la créer à la main :
>  * Run > Edit Configurations...
>  * [+] (_Add New Configuration_) Maven
>  * "Run Command line" : `javafx:run`