/* view.c -- view module for clock project
 *
 * Sean Mulhall
 * Computer Lab Techniques
 * Fall 2016
 */

#include "clock.h"

/* VIEW */

/* We get a pointer to a "struct tm" object, put it in a string, and
 * then send it to the screen.
 */
void ledshow(struct tm *dateinfo, int ampm)
{
    char       timestring[9];
    digit     *where = get_display_location();
    int i;
    int        displayHour = dateinfo -> tm_hour;
    int        pm = 0;
    
    /* Note that we have removed the colons to make this
     * part easier to write.
     */
    if ( (ampm == 1) && (displayHour > 12) )
      {
	displayHour = displayHour - 12;
	pm = 1;
      }

    sprintf(timestring,
            "%02d%02d%02d",
            displayHour,
            dateinfo->tm_min,
            dateinfo->tm_sec);

    for (i = 0; i < 6; i++) {
       switch ( timestring[i] ) {
           case ' ': where[i] = 0x00; break;
           case '1': where[i] = 0x24; break;
           case '2': where[i] = 0x5d; break;
           case '3': where[i] = 0x6d; break;
           case '4': where[i] = 0x2e; break;
           case '5': where[i] = 0x6b; break;
           case '6': where[i] = 0x7a; break;
           case '7': where[i] = 0x25; break;
           case '8': where[i] = 0x7f; break;
           case '9': where[i] = 0x2f; break;
           case '0': where[i] = 0x77; break;
       }
    }

    // colons + am/pm

    if (ampm == 1)
      {
	if (pm == 1)
	  {
	    where[6] = 0xf4;
	  }
	else
	  {
	    where[6] = 0xf2;
	  }
      }
    else
      {
	where[6] = 0xf1;
      }
    

    
    display();
    fflush(stdout);
}

void show(struct tm *dateinfo, int ampm)
{
    char       timestring[9];
    int        hour;
    

    hour = dateinfo -> tm_hour;
    

    if ((ampm == 1) && hour > 12)
      {
	hour = hour - 12;
	//dateinfo.tm_hour = hour;
	//still an idiot. I dont have to
	//put the new hour back into the struct.
	//I can just display the hour
      }
    
    sprintf(timestring,
            "%02d:%02d:%02d",
            hour,//tada!
            dateinfo->tm_min,
            dateinfo->tm_sec);
	
               

    printf("\r%s ", timestring);
    fflush(stdout);
}
