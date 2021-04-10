# Mario Bros
   
 ## I. Fonctionnalitées implémentées.  
   
- Creation de la fenetre de jeu  
- Animations du personnage  
- Affichage d'une map  
- Défilement de la map  
- Gravité  
- Collisions avec la map  
- Saut (sur place, en marchant, en courant)  
- Vitesse incrémentale du saut  
- Musique de font  
- Minuteur
- Entitée: mob:  
    - Goomba  
- Gestion de la position des entité en fonction du mouvement de mario  
- Animation de Goomba  
- Objets :  
    - Grande pieces
#### Animations du personnage:  
    - Animation static droite  
    - Animation static gauche  
    - Animation marcher droite  
    - Animation marcher gauche  
    - Animation courir droite  
    - Animation courir gauche  
    - Animation sauter droite  
    - Animation sauter gauche  
#### Vitesse incrémentale du saut:  
    + 4/tick jusqu'à 30% du saut  
    + 2/tick de 30% à 50% du saut  
    + 1/tick de 50% à 70% du saut  
    + 0.5/tick jusqu'à la fin du saut  
    
```
if (mario.getY() <= intialBeforeJumpCoordonnees.getY() + 16 * 0.3 * hauteur) {  
	camera.position.y = camera.position.y + 4;  
	mario.setY(mario.getY() + 4);  
} else if (mario.getY() <= intialBeforeJumpCoordonnees.getY() + 16 * 0.5 * hauteur) {  
	camera.position.y = camera.position.y + 2;  
	mario.setY(mario.getY() + 2);  
} else if (mario.getY() <= intialBeforeJumpCoordonnees.getY() + 16 * 0.7 * hauteur) {  
	camera.position.y = camera.position.y + 1;  
	mario.setY(mario.getY() + 1);  
} else if (mario.getY() <= intialBeforeJumpCoordonnees.getY() + 16 * hauteur) {  
	camera.position.y = (float) (camera.position.y + 0.5);  
	mario.setY(mario.getY() + (float) 0.5);  
}
```