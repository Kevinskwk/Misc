 /*
 * lab1a.c
 *
 *  Created on:
 *      Author:
 */

/* include helper functions for game */
#include "lifegame.h"

/* add whatever other includes here */

/* number of generations to evolve the world */
#define NUM_GENERATIONS 50

/* functions to implement */

/* this function should set the state of all
   the cells in the next generation and call
   finalize_evolution() to update the current
   state of the world to the next generation */
void next_generation(void);

/* this function should return the state of the cell
   at (x,y) in the next generation, according to the
   rules of Conway's Game of Life (see handout) */
int get_next_state(int x, int y);

/* this function should calculate the number of alive
   neighbors of the cell at (x,y) */
int num_neighbors(int x, int y);

int main(void)
{
	int n;

	/* TODO: initialize the world */
	initialize_world();

	for (n = 0; n < NUM_GENERATIONS; n++)
		next_generation();

	/* TODO: output final world state */
	output_world();

	return 0;
}

void next_generation(void) {
	/* TODO: for every cell, set the state in the next
	   generation according to the Game of Life rules

	   Hint: use get_next_state(x,y) */
	
	int x, y, lenx, leny;
	lenx = get_world_width();
	leny = get_world_height();

	for (x = 0; x < lenx; x++)
		for (y = 0; y < leny; y++)
			set_cell_state(x, y, get_next_state(x, y));

	finalize_evolution(); /* called at end to finalize */
}

int get_next_state(int x, int y) {
	/* TODO: for the specified cell, compute the state in
	   the next generation using the rules

	   Use num_neighbors(x,y) to compute the number of live
	   neighbors */
	
	int currState, neighbor;
	currState = get_cell_state(x, y);
	neighbor = num_neighbors(x, y);

	if (currState == ALIVE)
		return (neighbor == 2 || neighbor == 3) ? ALIVE : DEAD;
	else
		return (neighbor == 3) ? ALIVE : DEAD;
}

int num_neighbors(int x, int y) {
	/* TODO: for the specified cell, return the number of
	   neighbors that are ALIVE

	   Use get_cell_state(x,y) */

	#define NEIGHBORS 8 
	int n, neighbor = 0, nx, ny;
	int offxs[NEIGHBORS] = {-1, 0, 1, -1, 1, -1, 0, 1};
	int offys[NEIGHBORS] = {-1, -1, -1, 0, 0, 1, 1, 1};

	for (n = 0; n < NEIGHBORS; n++) {
		nx = x + offxs[n];
		ny = y + offys[n];
		if (get_cell_state(nx, ny) == ALIVE)
			neighbor++;
	}
	return neighbor;
}
