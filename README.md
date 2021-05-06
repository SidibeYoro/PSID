

<a href="https://github.com/SidibeYoro/PSID/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/SidibeYoro/PSID"></a>
<a href="https://github.com/SidibeYoro/PSID/network"><img alt="GitHub forks" src="https://img.shields.io/github/forks/SidibeYoro/PSID"></a>
<a href="https://github.com/SidibeYoro/PSID"><img alt="GitHub license" src="https://img.shields.io/badge/licence-Apache%202.0-brightgreen"></a>
<a href="https://github.com/SidibeYoro/PSID"><img alt="GitHub RELEASE" src="https://img.shields.io/badge/release-v3-blue"></a>
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/50fb7bb16ec242fa91744440e90cd639)](https://www.codacy.com/gh/SidibeYoro/PSID/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=SidibeYoro/PSID&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/SidibeYoro/PSID/badge.svg?branch=main)](https://coveralls.io/github/SidibeYoro/PSID?branch=main)


# PSID

L’objectif de ce projet est de proposer une plateforme d'entraide entre particuliers appelée GiveMeAHand. Chaque particulier profite des services en offrant en retour des services fournis basés sur le système de SEL (système d'échange local) .
Lorsque l’on fournit un service, on obtient une ou plusieurs médailles, en parallèle lorsque l’on reçoit un service on donne une ou plusieurs médailles.
Une médaille ( ou des médailles ) représente un crédit de service pour un particulier sur son compte.
Par exemple :
- X propose un service de couture qui coûte 3 médailles
- Y voudrait avoir une couturière, il est nécessaire pour elle d’avoir un crédit de service dans son compte. Si elle en a 3, elle peut donc demander le service de X.
- X dispose à ce moment de 6 médailles et Y de 0 médailles.

A l’inscription l’utilisateur disposera d’un crédit de 10 médailles, ainsi lorsque ces 10 médailles seront épuisées, l’utilisateur devra fournir des services afin d’avoir plus de médailles dans son compte et pouvoir bénéficier d’autre service.
Notre plateforme va intégrer également un système d’intelligence de données. Nous allons détailler les différentes possibilités en dessous

# Dépendances 

## Réferentiel :
Nous utilisons GitHub.
Pour cloner le projet, il y'a deux manières de le faire : 
- en ligne de commande : **git clone NotreLienGit dossierDestination**, le dossierDestination est un répertoire que vous avez créer sur votre machine, cette commande permet de charger le contenue du repository dans le "dossierDestionation".
- directement sur votre IDE: 
*IntelliJ* : les étapes sont sur le lien suivant : https://www.jetbrains.com/help/idea/manage-projects-hosted-on-github.html#clone-from-GitHub

# Environement : 
### IDE: 
Nous utilisons IntelliJ pour le coté backend, et Visual Studio Code pour le frontend. 
Pour l'installation :
- télécharger IntelliJ sur https://www.jetbrains.com/fr-fr/idea/download/download-thanks.html?platform=windows&code=IIC (ATTENTION: il faut bien choisir le Community qui est gratuit)
- télécharger VScode sur https://code.visualstudio.com/download
### Stack technique : 
Nous utilisons **Spring Boot** pour le backend, et **AngularJs** pour le frontend.
Toutes les étapes d'installation seront détaillés dans notre fichier de configuration.  
Pour lancer le projet en local :
- Coté backend: si vous utilisez IntelliJ il suffit de Run le projet, sinon executer la classe GivemehandApplication. 
- Coté frontend: il suffit de taper la ligne de commande suivante : **ng serve**
## Running unit tests

## Intégration continue
Nous avons conteneuriser notre application à l'aide de **Docker**, ceci facilitera l'execution de notre site sur n'importe quel serveur ou machine physique ou virtuelLe.
Pour le déploiement, nous avons opter pour **Azure**. 

## Enregistrement des données
- Pour les données, nous utilisons **PostgreSQL** comme serveur getionnaire de notre base de donnée.
Télécharger PosgreSQL ici : https://www.postgresql.org/download/                                                                                                            
- Afin de tester notre API, nous utilisons **Insommnia**.
Télecharger  Insomnia ici : https://insomnia.rest/download

## Documentation
## Site web 



