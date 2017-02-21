/* clock.h -- header file for clock program
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

#include "LEDisplay.h"

extern int display_mode;
//the 'extern' here says that this will be declared
//somewhere else (in this case, clock.c)
#define MODE_CLOCK 0x00;
#define MODE_DATE  0x01;
#define MODE_TEST  0x02;
#define MODE_GRAD  0x03;

/* model prototypes */
void start_timer(void);

/* controller prototypes */
void tick(int);

/* view prototypes */
void show(struct tm *, int);
void ledshow(struct tm *, int);

