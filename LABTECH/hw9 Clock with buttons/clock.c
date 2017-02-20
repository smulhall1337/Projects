/* clock.c -- controller part of the clock project
 *
 * Sean Mulhall
 *
 * Computer Lab Techniques
 * Fall 2016
 */

#include "clock.h"

/* CONTROLLER */

static char bugaddress[]  = "Smulhall1337@gmail.com";
static char title_text[80]= "Copyright 2016 Mulhall Industries";

int display_mode = MODE_CLOCK;
int mode_expires; //holds the time when we switch
                  //back to normal time



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

void process_key(keybits KeyCode)
{
    void stop_clock(void);
    int KeyRow, KeyCol;

    if (KeyCode & 0x80) {  // mouse hit

        
      KeyRow = KeyCode & 0x70;
      KeyRow = KeyRow  >> 4;
      KeyCol = KeyCode & 0xf;
      
      
      if (KeyRow == 0){//row 1
            switch (KeyCol){
                case 0:
                    ampm = 0;
                    break;
                case 1:
                    ampm = 1;
                    break;
                case 2:
		  display_mode = MODE_DATE;
		  mode_expires = 5;
                    break;
                case 3:
		  display_mode = MODE_TEST;
		  mode_expires = 5;
                    break;
                case 4:
                    stop_clock();
                    break;
            }
      }
      else
	{//row 2
	  switch (KeyCol)
	    {
	    case 0:
	      display_mode = MODE_GRAD;
	      mode_expires = 5;
	      break;
	    case 1:
	      ampm = 1;
	      break;
	    case 2:
	      stop_clock();
	      break;
	    }
	}
    }
    else { // keystroke
      switch(KeyCode) {
      case '2':
	ampm = 0;
	break;
      case 'a':
	ampm = 1;
	break;
      case 'q':
	stop_clock();
	break;
      case 'd':
	display_mode = MODE_DATE;
	mode_expires = 5;
	break;
      case 't':
	display_mode = MODE_TEST;
	mode_expires = 5;
      break;
        }
    }
}

void stop_clock()
{
    end_display();
    exit(0);
}

int main(int argc, char *argv[])
{
    int letter;  // option character
    register_keyhandler(process_key);
    
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
      {
	start_display();
        set_title_bar(title_text);
        register_keyhandler(process_key);

        set_key_text(0, "Grad!");
        set_key_text(1, "Hello");
      }
	

    /* get the model running */
    start_timer();

    while (1) {
      get_key();
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

    if(mode_expires != 0)
      {
	mode_expires = mode_expires - 1;
      }

    else 
      {
	display_mode = MODE_CLOCK;
      }
    
    /* get current time into "struct tm" object */
    (void) time(&now);   
    now = now + offset;
    //I'm an idiot, ampm logic is handled in view
    //not here
    dateinfo = localtime( &now );

    /* call View to show the time */
    if (LED)
      { // set up the fancy display
	ledshow(dateinfo, ampm);
      }
    else
      show(dateinfo, ampm);
		
}



  	     
	   
