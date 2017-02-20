/*
 *multiread.c - read and display information about presidents
 *
 *Sean Mulhall
 *Computer Lab Techniques
 *Fall 2016
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>


//define our president info
typedef struct
{
  char inits[5];
  unsigned char num;
} prezinfo;

int main (int argc, char *argv[])
{
  prezinfo person;
  FILE    *readIn;
  int      read;
 

  //open the file for reading
  if ( (readIn = fopen(argv[1], "r")) == NULL )
    {
      perror("File does not exist!");
      exit(1);
    } 

  printf("Num  Inits\n");
  //read the elements in the file
  while ( (read = fread(&person, sizeof(person), 1, readIn)) == 1)
    {
      //numscanned = scanf("%s", &inits[0]);
      //display
      printf("%d   ", person.num);
      printf("%s \n", person.inits); 	
    }
  //cleanup
  fclose(readIn);
  printf("End of data\n");
  return 0;
}
