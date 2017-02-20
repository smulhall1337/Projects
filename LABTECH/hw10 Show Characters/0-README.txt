SOME NOTES ABOUT MAKING YOUR CHARACTERS

1) "showchars.c" #includes all the files in the "Chars" directory.
   It does not read them at runtime, they are incorporated at
   compile time.  Your character files are part of the program
   source code.

   When you are working on your characters, you have to recompile
   "showchars" in order to see what you did.  Otherwise, the output
   of the program won't change.

   Remember to run "make showchars" after every change to your
   assigned character.


2) If you are assigned character "D", and you look it up in the man
   page for ascii, you'll see:

    Oct   Dec   Hex   Char                        Oct   Dec   Hex   Char
    ------------------------------------------------------------------------
    000   0     00    NUL '\0'                    100   64    40    @
    001   1     01    SOH (start of heading)      101   65    41    A
    002   2     02    STX (start of text)         102   66    42    B
    003   3     03    ETX (end of text)           103   67    43    C
    004   4     04    EOT (end of transmission)   104   68    44    D
    005   5     05    ENQ (enquiry)               105   69    45    E
    006   6     06    ACK (acknowledge)           106   70    46    F
    007   7     07    BEL '\a' (bell)             107   71    47    G

    [ rest deleted ]
   
   Be sure you take the "Hex" entry for the filename, not the "Dec"
   entry.  "D" is Hex 44, so you would edit "Chars/Char_44.h".

   If you misread and get the "Dec" value, you will end up changing
   "Chars/Char_68.h" to look like a "D", when that file is actually
   for the lower-case "h".


3) Be sure to set the comment at the top of the file to the actual
   character (most of them say "Space" right now), and to add your
   name.


4) Don't try to make "showchars" do multiple characters until after
   you are satisfied with your glyphs.  If you create some bugs in
   it, you might be creating good letters but not know because you
   have goofed up the program.

   If you get your characters right, and then working on showchars
   it starts showing things wrong, you'll know that the error is in
   the changes you made to showchars.



