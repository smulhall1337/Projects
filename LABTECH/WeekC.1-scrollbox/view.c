/* view.c -- display for scrolling box
 *
 * Sean Mulhall
 * Computer Lab Techniques
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

    /* RIGHT NOW, THIS ONLY DOES POSITION 0 */
    position = 0;

    // Characters start at " ", which is ASCII 0x20
    // but is in the array at location 0.
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
    
    if (debug) {
        fprintf(stderr, "-\r");
        fflush(stderr);
    }
    display();
    fflush(stdout);
}

