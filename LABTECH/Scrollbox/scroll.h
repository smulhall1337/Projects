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

extern int display_mode;
extern char the_string[256];


#define MODE_NORMAL 0x00;
#define MODE_CLOCK 0x01;
#define MODE_DATE 0x02;
#define MODE_TEST 0x03; 

/* defined in "scroll.c" */
void tick(int);
void show_time();
void show_date();
void show_test();

/* defined in "model.c" */
void start_timer(int);
void setup(char *);
char *display_string();

/* defined in "view.c" */
void show(char *);


/* debug flag */

extern int debug;
