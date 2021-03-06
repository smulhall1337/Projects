/* scroll.h -- header for the scroll box
 *
 * Sean Mulhall
 * Computer Lab Techniques
 * Fall 2016
 */

#include <sys/time.h>
#include <time.h>
#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include "LEDots.h"

/* defined in "scroll.c" */
void tick(int);

/* defined in "model.c" */
void start_timer(int);
void setup(char *);
char *display_string();

/* defined in "view.c" */
void show(char *);


/* debug flag */

extern int debug;
