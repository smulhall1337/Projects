/* model.c -- Data and Timing for the Scroll Box
 *
 * Darren Provine, 7 August 2011
 */

#include "scroll.h"

/* the message that we're scrolling */
char scrollmessage[256];


/* pad scrollmessage with 9 spaces left and right */
void setup(char *text)
{
    sprintf(scrollmessage, "         %s         ", text);
    if (debug >= 2) {
        fprintf(stderr, "text is: |%s|\r\n", scrollmessage);
        sleep(1);
    }
}


/* declared outside function so pointer is valid when passed */
char viewport[9];
char *display_string()
{
  //returns a pointer to the 9 characters we care about(called viewport)
  
    int startpos;
    static int step = 0; // initialization only happens once
    //even though this variable is local, step will remain in memory after
    //this function returns. 
    static int prev_step;// where we were in the original string
                  // used for switching back to normal from another mode
    static int just_changed = 0; // did we just change modes?
    /* TODO: make this actually scroll through the string */
    //use stuff found in stringscroll.c!

    if (display_mode != 0 && just_changed == 0){
      prev_step = step;
      step = 0;
      just_changed = 1;
    }
      
    
    if (step == strlen(scrollmessage)){
      if (just_changed == 1){
	step = prev_step;
	just_changed = 0;
	//this should just scroll through the different mode
	//once before switching back to normal
	setup(the_string);
	display_mode = MODE_NORMAL;
      }
      else{
	step = 0;
      }
    }

    startpos = step;
    
    
    sprintf(viewport,                   
	   "%9s",
	    &(scrollmessage[startpos]) );
    // note2self: sprintf needs a pointer to wherever you're planning
    // on storing the string
    
    // strcpy(viewport, "testing");
    
    if (debug >= 4) {
        fprintf(stderr, "viewport: |%s|\r\n", viewport);
        sleep(1);
    }
    step++;
    return &viewport[0];
    //returns the pointer to the first character in viewport
}


/* Set up an interval timer for our scroll box.
 * This is part of the model; as with the clock, it's what actually
 * measures real time passing.
 * When the interval is over, notify tick(), which is part of the
 * controller for this program.
 */
void start_timer(int delay)
{
    struct itimerval interval; // interval object
    struct sigaction new_action, old_action;  // signal actions

    /* See the manual entry for signal.h(3HEAD) for a list of all
     * signals. */

    // blank out the signal mask
    sigemptyset( &new_action.sa_mask );
    // clear flags (our application is pretty simple)
    new_action.sa_flags = 0;
    // set tick() as the signal handler when we get the timer signal.
    new_action.sa_handler = tick;
 
    /* sigaction takes a pointer as an argument, so you have to
     * allocate space.  The declaration of new_action() up above
     * has no star - that's the actual object, not just a pointer.
     */
    if ( sigaction(SIGALRM, &new_action, &old_action) == -1 ) {
        perror("Could not set new handler for SIGALRM");
        exit(1);
    }

    /* set interval to the specified delay value. */
    if (delay >= 1000) {
        interval.it_value.tv_sec = delay / 1000; // seconds
        interval.it_value.tv_usec = 0;
        interval.it_interval = interval.it_value;
    } else {
        interval.it_value.tv_sec = 0;
        interval.it_value.tv_usec = delay * 1000; // we need useconds
        interval.it_interval = interval.it_value;
    }

    /* set a timer for the real time clock using the interval object */
    /* NOTE: takes a pointer, so no * in our declaration of "interval */
    setitimer(ITIMER_REAL, &interval, NULL);
}
