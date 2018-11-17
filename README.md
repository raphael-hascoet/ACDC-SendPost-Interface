# project-ACDC

## Site web statique dynamique ##

*Ce projet est réalisé dans le cadre d'un projet de première année (SEMESTRE 1) en tant qu'ingénieur logiciel à l'IMT ATLANTIQUE (France, Nantes).*

L'object de celui-ci est de pouvoir facilement ajouter de nouveaux articles sur un site web utilisant jekyll (plus d'informations sur https://jekyllrb.com) 

## A propos du projet
Afin que tout fonctionne correctement il faudra lier votre dépôt github correspondant à votre pc à l'aide d'une clé ssh. Pour se faire, suivre les étapes énnoncées dans la partie [#Set-up-'git-push'-avec-clé-ssh].

### Set up 'git push' avec clé ssh ###
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

