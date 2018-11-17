# project-ACDC

## Site web statique dynamique ##

*Ce projet est réalisé dans le cadre d'un projet de première année (SEMESTRE 1) en tant qu'ingénieur logiciel à l'IMT ATLANTIQUE (France, Nantes).*

**********

## A propos du projet ##
### Présentation ###
Le but du projet est de pouvoir facilement ajouter de nouveaux articles sur un site web utilisant jekyll (plus d'informations sur https://jekyllrb.com).

J'ai choisi d'implémenter une solution qui se base sur le diagramme de classe suivant : 
![alt-text](diagramme-de-classe.png)


### Mise en place ###
Afin que tout fonctionne correctement il faudra lier votre dépôt github correspondant à votre pc à l'aide d'une clé ssh. Pour se faire, suivre les étapes énnoncées dans la partie [Set up 'git push' with an ssh key](#ssh-key).

Une fois cela fait et le dépôt cloné sur votre machine, vous avez deux choix : 
- run le programme `createNewPost.jar` depuis un terminal (à l'aide de la commande : `java -jar createNewPost.jar`)
- ouvrir le projet dans un IDE et le lancer depuis cet IDE en effectuant les changements mentionnés dans la partie [Configurer le programme pour son propre pc](#configure-your-project).

**********

### Configurer le programme pour son propre pc ###
__Premièrement :__ il faudra commenter les lignes indiquées dans les commentaires des classes `Main` et `Tools` (lignes utilisées pour le fonctionnement du fichier .jar) et dé-commenter les lignes indiquées (afin de définir le 'path' pour un fonctionnement dans le terminal d'eclipse).

__Deuxièmement :__ définir un chemin adéquat dans l'objet `Tools outil` créé dans la classe `Main` permettant au programme de se rendre dans le dossier `your-path-from-home/project-ACDC/BLOG/`.

Vous pouvez désormais lancer le programme, celui-ci fonctionnera correctement si vous avez bien installé jekyll et bien rentré une clé ssh pour votre dépôt GitHub.

**********

### Set up 'git push' with an ssh key ###
Create a repo.
Make sure there is at least one file in it (even just the README)
Generate ssh key:
```
ssh-keygen -t rsa -C "your_email@example.com"
```
Copy the contents of the file ~/.ssh/id_rsa.pub to your SSH keys in your GitHub account settings.
Test SSH key:
```
ssh -T git@github.com
clone the repo:
git clone git://github.com/username/your-repository
```
Now cd to your git clone folder and do:
```
git remote set-url origin git@github.com:username/your-repository.git
```
Now try editing a file (try the README) and then do:

```
git add -A
git commit -am "my update msg"
git push
```
