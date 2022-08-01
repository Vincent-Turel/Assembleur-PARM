sub sp, #12 // Agrandir la pile de 3*4 octets d’où le sp − 12
movs r0, #0 // Placer dans un registre la valeur contenue dans la variable a
str r0, [sp, #8] // Stocker cette valeur dans la pile
movs r1, #1 // Placer dans un registre la valeur contenue dans la variable b
str r1, [sp, #4] // Stocker cette valeur dans la pile
ldr r1, [sp , #8] // Charger dans le registre 1 la valeur contenue à la dernière adresse de la pile
ldr r2, [sp, #4] // Charger dans le registre 2 la valeur contenue à l’avant dernière adresse de la pile
adds r1, r1, r2 // Additionner les valeurs des registres 1 et 2, stocker le résultat dans le registre 1
str r1, [sp] // Stocker le contenu du registre 1 à l’adresse pointée par le pointeur de pile
add sp, #12 // Réduire la pile de 3*4 octets
