 /* view.c -- display for scrolling box
 * 
 * Darren Provine, 20 March 2012
 *
 * Edited by Sean Mulhall 
 * Fall 2016 
 */

#include "scroll.h"
#include "LEDots.h"
#include "Chars/All_Chars.h"

void show(char *text)
{
    int position; // where in the string are we?
    int line;     // which line are we on?
    byte *where;  // where in memory are we putting the bits?
    int c;        // what character are we doing?
    int index;    // where is the character in the glyph[] array?
    where = get_display_location();

    if (debug) {
        fprintf(stderr, " text:|%s|", text);
        fflush(stderr);
    }
    //       position is whats telling the LEDisplay what
    //       sections of the planel to display. how do we
    //       get it to display all the panels at once??
    position = 0;
    //       wih a loop!
   
    // Characters start at " ", which is ASCII 0x20
    // but is in the array at location 0.
    
    while ( position != 8 ){
      
      c = (int) text[position];
      index = c - 0x20;
      if (debug) {
	fprintf(stderr, "-%c", c);
	fflush(stderr);
      }
      
      
      for (line=0; line < 10; line++) {
	where[position + line * 9] =
	  (*glyph[index])[line];
      }
      position ++;
    }
    
    if (debug) {
      fprintf(stderr, "-\r");
      fflush(stderr);
    }
    display();
    fflush(stdout);
}

      
     
    


