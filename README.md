# Assembleur-PARM

## Context 

This project dates back to October 2020.

It has been realized within the scope of a course of processor architecture at Polytech Nice Sophia.

## Description

This in an assembler which turns an assembly program (*.s* extension) into hexadecimal (*.bin* extensin).
It is then readable for our home-made PARM processor by injecting it into its ROM.

It has been developped in Java.

## How to execute

1. Turn your C-file into a assembly file using Clang compiler
Use the following command line : clang -S -target arm-none-eabi -mcpu=cortex-m0 -O0 -mfloat-abi=soft nomdufichier.c

2. Download our code 
3. Drop your .s file in the root of the project (Assembleur_PARM\your_project.s)
4. Open the terminal in this same root location et enter the following command line : java -jar target\Assembleur_PARM-1.0-SNAPSHOT.jar nomdufichier.s

You'll get Assembleur_PARM\your_project.bin !
