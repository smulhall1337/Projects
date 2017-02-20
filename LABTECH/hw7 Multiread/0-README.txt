STRUCTURE READING HOMEWORK

The file "pres-SQ.dat" was created this way:

    [elvis 62] ./multiprez pres-SQ.dat
    1 GW
    4 JM
    9 WHH
    16 AL
    25 WM
    36 LBJ
    0       <- that's the sentinel value which ended input
    1
    1
    1
    1
    1
    1       <- this is a "1" for every president as written


The file "pres1980.dat" was created this way:

    [elvis 62] ./multiprez pres1980.dat
    39 JEC
    40 RWR
    41 GHWB
    0       <- that's the sentinel value again
    1
    1
    1       <- three presidents, three "1"s.


To see what's in the files, you can use "XXD"

    [elvis 71] xxd pres-SQ.dat 
    0000000: 4757 003f ff01 4a4d 0000 0004 5748 4800  GW.?..JM....WHH.
    0000010: 9809 414c 007f 0010 574d 0038 3119 4c42  ..AL....WM.81.LB
    0000020: 4a00 c224                                J..$

The struct for a person in the program has 5 bytes for characters
(4 initials and a null) and one byte for the number.  So the first line
of output:
   
    0000000: 4757 003f ff01 4a4d 0000 0004 5748 4800  GW.?..JM....WHH.

shows you the first six bytes:     47 57 00 3f ff 01

which are "G", "W", null, two bytes of garbage (because we only
used 3 bytes of the 5-byte array for initials) and then the
value 1, because we indicated that he was President #1.

The next six bytes: 4a 4d 00 00 00 04

are "J", "M", null, two bytes of garbage, and then value 4.

The next four bytes: 57 48 48 00

are WHH, a null, and that's the first 16 bytes of the file.
(The rest of the line is the printable characters in the data.)

The next line picks up with: 98 09, which is one byte of garbage
(we only used 4 bytes of the 5-byte array for initials) and the
number 9, because William Henry Harrison was President #9.

The rest of the file is similar, but note that when we get to
Lincoln, President #16, his byte is "10", because this is in
hexadecimal, not decimal, and the same applies to President 25
and 36.


For the 1980s, file, the same thing applies:

    [elvis 72] xxd pres1980.dat
    0000000: 4a45 4300 ff27 5257 5200 0028 4748 5742  JEC..'RWR..(GHWB
    0000010: 0029                                     .)

4a 45 43 are "JEC", there's a null, "ff" is just garbage, and "27"
means "2 * 16 + 7", which is 39.  (Note that because character 39
in the ASCII table is a single quote, xxd prints the single quote
in the list of characters.  To xxd, these bits are just bits, and
it has no way of knowing which ones are characters and which ones
are numeric data.)

When you write a file, that's what actually happens: the bits go on
the disk.


For the homework, you have to use "fread()" and the correct data
structure to read the bits off the disk and print out the values.


Here is the output of my program on the three files in this folder:

    [elvis 81] ./multiread pres1980.dat
    Num  Inits
     39   JEC
     40   RWR
     41   GHWB
    End of data
    [elvis 82] ./multiread pres-SQ.dat
    Num  Inits
     1   GW
     4   JM
     9   WHH
     16   AL
     25   WM
     36   LBJ
    End of data
    [elvis 83] ./multiread pres2000s.dat
    Num  Inits
     42   WJC
     43   GWB
     44   BHO
    End of data
    [elvis 84] ./multiread foo
    foo: No such file or directory
    [elvis 85] 



