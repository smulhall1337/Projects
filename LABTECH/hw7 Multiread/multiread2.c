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



typedef struct
{
  char inits[5];
  unsigned char num;
} prezinfo;

int main (int argc, char *argv[])
{
  prezinfo person;
  FILE    *readIn;
  int      numscanned;
  int      num;
  char     inits[5];
 

  //open the file for reading
  if ( (readIn = fopen(argv[1], "r")) == NULL )
    {
      perror("File does not exist!\n");
      exit(1);
    } 

  
  while ( (numscanned = fread(&person, sizeof(person), 1, readIn)) != EOF)
    {
      if (feof(readIn) == 0)
	{
	  //get initials
	  numscanned = scanf("%s", &inits[0]);

	  // person.num = num;
	  // strcpy(person.inits, inits);

	  printf("%d", person.num);
	  printf("%s \n", person.inits); 
	}
      else
	{
	  printf("End of data\n");
	  exit(1);
	}

    }

  return 0;
}
