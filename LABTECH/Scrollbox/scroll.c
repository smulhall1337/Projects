/* scroll.c -- scrolling display
 *
 * Darren Provine, 20 March 2012
 */

#include <time.h>
#include <stdio.h>

// sleep needs this
#include <unistd.h>

#include "scroll.h"

// debug flag - changed via options
int debug = 0;
int display_mode = MODE_NORMAL;
int reset; //time until the mode resets to normal
char the_string[256]; // used to store the string of text
                      // the user has entered if they switch
                      // to time or date mode (256 - 9(2) = 238)


const static char rcsid[] =
    "$Id: scroll.c,v 1.2 2016/11/29 20:39:24 mulhalls1 Exp $";

static char bugaddress[]="kilroy@elvis.rowan.edu";

// version -- say which version this is and exit
// (note simple output; you could also print the rcsid too)
void version()
{
    fprintf(stderr, "scroll version 1\n");
    exit(0);
}

// usage -- print brief summary and instructions
void usage(char *progname)
{
    fprintf(stderr, "This program shows a scrolling box.\n");
    fprintf(stderr, "Usage: %s [-dDvh] text\n", progname);
    fprintf(stderr,
            "  -d # : set scroll delay to # milliseconds (default=1000)\n");
    fprintf(stderr,
            "  -D   : turn on debug messages (more Ds = more debugging)\n");
    fprintf(stderr, "  -v   : show version information\n");
    fprintf(stderr, "  -h   : this help message\n");
    fprintf(stderr, "Use quotes for multi-word messages, 'like this'.\n");
    fprintf(stderr, "report bugs to %s \n", bugaddress);
    exit (0);
}

void exit_scroll()
{
    end_display();
    exit(0);
}

// this is the scroll delay.
// It can be changed keys/mouse, so has to be global.
int delay = 1000; 

// keyboard and mouse handling
void process_key(keybits KeyCode)
{
  int row, col;

  if ( KeyCode & 0x80 ) { // mouse click
        row = (KeyCode & 0x70) >> 4;
        col = (KeyCode & 0x0f);

        if (row == 0 && col == 4) { // "off"
	  exit_scroll();
        }
	
	else if (row == 0 && col == 0) { // faster
	  if ( delay != 100 ) {
	    delay = delay - 100;
	    start_timer(delay);
	  }
	}
	
	else if (row == 0 && col == 1){ // slower 
	  if ( delay != 5000 ){
	    delay = delay + 100;
	    start_timer(delay);
	  }
	}

	else if (row == 0 && col == 2){ // clock
	  
	  if (display_mode == 0){
	    show_time();
	    //only executes when we're in normal mode
	    //dont want it to change if we're already in clock
	  }
	}
	
	else if (row == 0 && col == 3){ // test
	  if (display_mode == 0){
	  show_test();
	  }
	}

	else if (row == 1 && col == 0){//date
	    if(display_mode == 0){
	      show_date();
	    }
	  }
	
    } else { // keyboard press
        switch(KeyCode) {
	case 'q': exit_scroll(); break;
	case 'c': display_mode = MODE_CLOCK; break;
	case 'f':
	  if (delay != 100){
	    delay = delay - 100;
	    start_timer(delay);
	  }
	  break;
	case 's':
	  if (delay != 5000){
	    delay = delay + 100;
	    start_timer(delay);
	  }
	  break;
	case 't':
	  if (display_mode == 0){
	  show_time();
	  }
	  break;
	case 'd':
	  if (display_mode == 0){
	    show_date();
	  }
	  break;
	}
    }
}
    

int main(int argc, char *argv[])
{
    int   letter; // option char, if any
    // note C does automatic concatenation of long strings
    char  title[81] =
               "                                "
               "Copyright 2016 Mulhall industries"
               "                                ";
 
    // loop through all the options; getopt() can handle together or apart
    while ( ( letter = getopt(argc, argv, "d:Dvh")) != -1 ) {
        switch (letter) {
            case 'd':  delay = atoi(optarg);   break;

            case 'D':  debug++;                break;
            case 'v':  version();              break;
            case 'h':  usage(argv[0]);         break;

            case '?':  // unknown flag; fall through to next case
            default:   // shouldn't happen, but Just In Case
                       // note that getopt() warns about the unknown flag
                  fprintf(stderr, "run \"%s -h\" for help\n", argv[0]);
                  exit(1);
        }
    }

    // optind is the first argument after options are processed
    // if there aren't any, the user didn't give a message to scroll
    if (optind == argc) {
        usage(argv[0]);
    }

    // put the information from the command line into the module
    setup(argv[optind]);
    sprintf(the_string, "%s",argv[optind]);
    
    // set up the view
    register_keyhandler(process_key);
    start_display();
    set_title_bar(title);

    // turn on some keys in row 2
    set_key_text(0, "date");
    set_key_text(1, "Long");

    // start the model running
    start_timer(delay);

    // wait for the model to signal controller
    while ( 1 ) {
        get_key();
    }

    end_display();

    return 0;
}


/* This is the function the model uses to signal the controller
 * that there's work to do.
 */
void tick(int sig)
{
    char      *segment;
    struct tm *dateinfo;
    time_t     now;
    /* get the information from model about what chars to show
     * and how far over to slide them */
    segment = display_string();

    (void) time(&now);
    dateinfo = localtime(&now);

    
    if (debug >= 3) {
        fprintf(stderr, "display_string() returned |%s|\r\n", segment);
        sleep(2);
    }

    if (reset == time(NULL)){
      display_mode = MODE_NORMAL;
    }
    
    /* Send those chars to the view. */
    show(segment);
    get_key();
}
 
void show_time(){
  time_t rawtime;
  struct tm *timeinfo;
  char timestring[9];
  char ampm[2];
  int display_hour;
  
  time (&rawtime);
  timeinfo = localtime(&rawtime);
  
  display_hour = timeinfo -> tm_hour;
  sprintf(ampm, "%s", "am");
  
  if (display_hour > 12)
    {
      display_hour = display_hour - 12;
      sprintf(ampm, "%s", "pm");
    }
  display_mode = MODE_CLOCK;
  sprintf(timestring,
	  "%02d:%02d:%02d %s",
	  display_hour,
	  timeinfo -> tm_min,
	  timeinfo -> tm_sec,
	  ampm);
  setup(timestring);
 }

void show_date(){
  time_t rawtime;
  struct tm *dateinfo;
  char datestring[9];
  char month[3];
  int nummonth;
  
  time (&rawtime);
  dateinfo = localtime(&rawtime);
  nummonth = dateinfo -> tm_mon;

  switch(nummonth){
  case 0:
    sprintf(month, "%s", "Jan");
    break;
  case 1:
    sprintf(month, "%s", "Feb");
    break;
  case 2:
    sprintf(month, "%s", "Mar");
    break;
  case 3:
    sprintf(month, "%s", "Apr");
    break;
  case 4:
    sprintf(month, "%s", "May");
    break;
  case 5:
    sprintf(month, "%s", "Jun");
    break;
  case 6:
    sprintf(month, "%s", "Jul");
    break;
  case 7:
    sprintf(month, "%s", "Aug");
    break;
  case 8:
    sprintf(month, "%s", "Sep");
    break;
  case 9:
    sprintf(month, "%s", "Oct");
    break;
  case 10:
    sprintf(month, "%s", "Nov");
    break;
  case 11:
    sprintf(month, "%s", "Dec");
    break;
  }
  
  display_mode = MODE_DATE;
  sprintf(datestring,
          "%s %02d,%04d",
          month,
          dateinfo -> tm_mday,
          dateinfo -> tm_year+1900
          );
  setup(datestring);
}

void show_test()
{
  display_mode = MODE_TEST;
  //test all LEDS
  char test[9];
  sprintf(test, "%s", "~~~~~~~~");
  setup(test);
}
