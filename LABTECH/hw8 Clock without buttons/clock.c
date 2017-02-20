
/* clock.c -- controller part of the clock project
 *
 * Sean Mulhall
 *
 * Computer Lab Techniques
 * Fall 2016
 */

#include "clock.h"

/* CONTROLLER */

static char bugaddress[]="Smulhall1337@gmail.com";

// version -- say which version this is and exit
// (note simple output; you could also print the rcsid too)
void version()
{
    fprintf(stderr, "simple clock version 1\n");
    exit(0);
}

// usage -- print brief summary and instructions
void usage(char *progname)
{
    fprintf(stderr, "This program displays a realtime clock.\n");
    fprintf(stderr, "Usage: %s [-avh] [-o number]\n", progname);
    fprintf(stderr, "  -a    : am/pm instead of 24 hour\n");
    fprintf(stderr, "  -l    : use simulated LED display\n");
    fprintf(stderr, "  -o #  : offset the time by # seconds \n");
    fprintf(stderr, "  -v    : show version information\n");
    fprintf(stderr, "  -h    : this help message\n");
    fprintf(stderr, "report bugs to %s \n", bugaddress);
    exit (0);
}

/* These have to be global because tick() is called
 * automatically; we can't pass it any arguments because
 * we don't call it.
 *
 *any sort of time variable is represented is SECONDS!
 */
int offset = 0; //set offset to use for testing (primarily
                //our am-pm logic) (use stm.py to figure out seconds)
int ampm = 0;
int LED  = 0;

int main(int argc, char *argv[])
{
    int letter;  // option character

    // loop through all the options; getopt() can handle together or apart
    while ( ( letter = getopt(argc, argv, "alo:vh")) != -1 ) {
        switch (letter) {
	    case 'a':  ampm = 1;               break; //set to am-pm clock (defaiult is 24hr)
            case 'l':  LED  = 1;               break;
	    case 'o':  offset = atoi(optarg);  break; //run with offset
            case 'v':  version();              break;
            case 'h':  usage(argv[0]);         break;

            case '?':  // unknown flag; fall through to next case
            default:   // shouldn't happen, but Just In Case
                       // note that getopt() warns about the unknown flag
                  fprintf(stderr, "run \"%s -h\" for help\n", argv[0]);
                  exit(1);
        }
    }

    // All the flags have been scanned.
    // "optind" is now the index of the first non-option item

    if (LED)
        start_display();

    /* get the model running */
    start_timer();

    while (1) {
        pause(); /* wait for a signal */
    }

    /* no return because never reached */
}


/* This function is called when the timer ticks.
 * Then it calls the show() function, which is our View.
 * The Controller talks to the Model and the View.
 *
 * Note we ignore the argument!
 * sigaction() arranges to pass us the signal that caused the function
 * to be called, so you can use one signal handler for multiple signals.
 * But we only catch one signal, so no need to worry about it.
 */
void tick(int sig)
{
    time_t       now;
    struct tm   *dateinfo;  // localtime() returns a pointer, so it
                            // allocates space.  We just need a pointer.
    // TODO: adjust time by "offset" seconds

   
    /* get current time into "struct tm" object */
    (void) time(&now);
    

    now = now + offset;
    
    //I'm an idiot, ampm logic is handled in view
    //not here
    
    dateinfo = localtime( &now );

   
    /* call View to show the time */
    if (LED)
        ledshow(dateinfo, ampm);
    else
        show(dateinfo, ampm);
}
