# CPE-2223-3ICS-POO_FilRouge

Ce dépôt contient le projet de départ pour les ateliers du module :

"Programation Orientée Objet (POO) / Conception Orientée Objet (COO)"

## IDE-specific tips

### IntelliJ

Au lancement, "_Trust Maven config_" devrait suffir.

Le projet vient avec une "Run configuration" __ics3filrouge [javafx:run]__  
qui permet de lancer 
la cible maven `javafx:run` ; à utiliser pour lancer le projet.

> Pour la créer à la main :
>  * Run > Edit Configurations...
>  * [+] (_Add New Configuration_) Maven
>  * "Run Command line" : `javafx:run`