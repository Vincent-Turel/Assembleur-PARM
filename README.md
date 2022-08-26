# Assembleur-PARM

## Context 

This project dates back to October 2020.

It has been realized within the scope of a course of processor architecture at Polytech Nice Sophia.

## Description

This in an assembler which turns an assembly program (*.s* extension) into hexadecimal (*.bin* extensin).
It is then readable for our home-made PARM processor by injecting it into its ROM.

It has been developped in Java.

## How to execute

To transform an assembly program in hexadecimal, you need to :
 - 

1.Transformer le fichier .c en fichier assembleur avec le compilateur Clang, on obtient un .s
Commande à utiliser sur un terminal : clang -S -target arm-none-eabi -mcpu=cortex-m0 -O0 -mfloat-abi=soft nomdufichier.c
2.Passer le fichier assembleur obtenu dans notre assembleur, pour cela, il faut :
	-Télécharger notre assembleur .jar
	-Déplacer le fichier .s à assembler au niveau de la racine de l'assembleur : Assembleur_PARM\ici
	-En restant au même endroit, ouvrir le terminal et tapez la commande : java -jar target\Assembleur_PARM-1.0-SNAPSHOT.jar nomdufichier.s
Vous obtiendrez au même endroit (Assembleur_PARM\ici) nomdufichier.bin, fichier qu'on peut charger dans logisim dans la ROM.
