VIRTUAL SCROLL BOX

As with the clock, we have a file "LED-layout.txt", which explains which
bits go where to turn on LEDs.  The manufacturer of the display has
also sent us a file "LEDots.h", which has prototypes and declarations
needed for our compiler, and "LEDots.o", which has all the object code.
(They did not send us the C source files, because they consider that to
be a trade secret.)

What we have to do is modify the Model and View portion of our scroll box
to use this display.  The Controller handles the flags about which display
to use, but it doesn't handle keystrokes.

I've already written the Makefile; you should read it to see what it's
doing.  (There are more macros than we had in earlier examples.)


BEFORE YOU START:

Copy this entire folder to your ~/LABTECH folder, and name it
"Scrollbox".  The grading script will look for a folder named
"/home/username/LABTECH/Scrollbox", so don't goof up the name.


WHAT YOU NEED TO DO:

1) Create an "RCS" folder and check in any file before you touch it.


2) Using the logic of "track start position and find next N characters"
   that we used on the simple scrollbox, modify the display_string()
   function in model.c so that it pulls out the correct 9 characters
   from the message to be shown.

   Note that the stringscroll.c program uses a loop; the display_string()
   function will NOT have a loop.  Each time it's called, it should
   advance one position in the string and return the characters it gets,
   and that's it.  The "do this lots of times" part is provided by the
   timer, which will call "display_string()" again when it's time to
   advance.


3) Modify show() in view.c so that it shows all 9 characters of the
   text it is passed.


4) Add code to handle keystrokes and mouse clicks.  Note that you
   can make the box scroll faster/slower by just lowering/raising
   the value of the "delay" variable and then re-calling the
   "start_timer()" function.  But put limits: don't let delay drop
   to less than 100, or rise to more than 5000.

   You can make up whatever you want for "time" and "test", as long
   as one shows the time for 5 seconds and the other tests all the
   LEDs in one way or another.


5) Get extra credit if you want.

A) Adding some things for the second row of buttons to do.

   You can use the second-row keys for whatever.  In the past, 
   students have created "DATE" buttons, which show the date.
   Others have done inverse video, or vertical scrolling for
   short messages.

   If you have an idea but aren't sure how to do it, or whether
   it'll be too hard, talk to me and I'll give suggestions.


B) Add your own option flags:

   Maybe "-c" for "just be a clock", and then set up the bottom row
   of buttons with AM/PM and 24hr buttons, and even re-use the "-o"
   offset code you wrote for the clock project.

   One student made a clock that counted for 10 seconds, and then
   showed the date for five seconds, and then went back to the time.
   (The date was shown with an abbreviation for the month, as "Dec 12".)

   One pair made the time infinitely scroll across the the display
   ("... 10:22:14 ... 10:22:15 ... 10:22:16 ..."), but they couldn't
   get it synced so that the seconds never changed while scrolling.


C) Other stuff

   Note that the title bar is 80 characters; so you can put in not only
   your name, but you could put the date in the upper-right.  You could
   use the sysinfo(2) system call to put the total number of running
   processes, or the system load, or some other such information in the
   title bar.

   You could even put the date/time in the title bar continually, updating
   that every second and/or minute while the message scrolls by.  (You
   could add an option flag for whether you want seconds or not.)



NOTE ON IMPLEMENTATION:

As with the clock, one way to make these features work is to create a
global variable "Box_Mode", and set up constants:

#define MODE_NORMAL  0x01
#define MODE_CLOCK   0x02
#define MODE_DATE    0x04
#define MODE_TEST    0x08

Then your Controller module, would have a switch statement where
you check which mode you're in and act accordingly.

Note that for modes which are supposed to be temporary, you can add
a counter for those and decrement it each time.

When someone clicks the "time" button:

    Box_Mode = MODE_CLOCK;
    Clock_expires = time() + 5;


Then when you handle updates:

    switch(Box_Mode) {
        case MODE_NORMAL:
            // etc, etc

        case MODE_CLOCK:
            if ( time() > Clock_expires ) {
                Box_Mode = MODE_NORMAL;
            }
            // now do some stuff to show the time
            // at the next time through this event handler,
            // it'll be back to normal mode.

        case MODE_TEST:
            // whatever...
    }


Note that these modes apply to what the box's function is: they
are about the Controller module.

If you've added a button for inverse video, that's about the View,
not the controller.  So you'd have a separate variable for that,
perhaps just as simple as "inverse_video", set to 1 or 0, which would
be set by the Controller but only read in the view.

If you need to change show() to take extra parameters, feel free to do
so.  Just be sure to change the prototype in scroll.h, or the compiler
will fuss at you.

