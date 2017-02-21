/* LEDisplay.h -- header file for 7segment clock library file
 *
 * Sean Mulhall
 * Computer Lab Techniques
 * Fall 2016
 */

#include <strings.h>
#include <stdio.h>
#include <ctype.h>
#include <ncurses.h>
#include <term.h>
#include <stdlib.h>

typedef unsigned char digit;

digit *get_display_location(void);

void start_display(void);
void end_display(void);

void display(void);
