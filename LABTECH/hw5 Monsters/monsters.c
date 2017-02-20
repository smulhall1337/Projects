/* monsters.c - monster abilities and vulnerabilities.
 *
 * 
 * Sean Mulhall
 * Principles of digital computing
 * Fall 2016
 */

#include <stdio.h>

/* MONSTER ATTACKS */
#define  FIRE    0x01
#define  BITE    0x02
#define  CLAW    0x04
#define  ACID    0x08
#define  FROST   0x10
#define  POISON  0x20

/* MONSTER TYPES */
#define WOLF    ( BITE )
#define COUGAR  ( BITE | CLAW )
#define TIGER   ( BITE | CLAW )
#define COBRA   ( BITE | POISON )
#define DRAGON  ( FIRE | BITE | CLAW )
#define YETI    ( BITE | CLAW | FROST )
#define ALIEN   ( BITE | ACID )

/* DEFENSES */
#define  NONE        ( 0x00 )
#define  INSULATION  ( FIRE | FROST )
#define  ARMOR       ( BITE | CLAW )
#define  AMULET      ( POISON | ACID )


int main()
{
    // prototype for the "report_danger" function.
    void report_danger(int, int);
    int  equipment;
    int  monster;

    printf("Testing report_danger() function.\n");

    printf("Test  1: ");
    equipment = NONE;
    monster   = WOLF;
    report_danger(equipment, monster);

    printf("Test  2: ");
    equipment = ARMOR;
    monster   = WOLF;
    report_danger(equipment, monster);

    printf("Test  3: ");
    equipment = ARMOR;
    monster   = DRAGON;
    report_danger(equipment, monster);

    printf("Test  4: ");
    equipment = INSULATION;
    monster   = ALIEN;
    report_danger(equipment, monster);

    printf("Test  5: ");
    equipment = ARMOR;
    monster   = TIGER;
    report_danger(equipment, monster);

    printf("Test  6: ");
    equipment = ARMOR | AMULET | INSULATION;
    monster   = DRAGON;
    report_danger(equipment, monster);

    printf("Test  7: ");
    equipment = NONE;
    monster   = DRAGON;
    report_danger(equipment, monster);

    printf("Test  8: ");
    equipment = INSULATION;
    monster   = DRAGON;
    report_danger(equipment, monster);

    printf("Test  9: ");
    equipment = INSULATION;
    monster   = COBRA;
    report_danger(equipment, monster);

    printf("Test 10: ");
    equipment = AMULET;
    monster   = ALIEN;
    report_danger(equipment, monster);

    return 0;
}



/* void function - does not return anything
 *
 * report_danger takes two arguments:
 *   1) What defenses the user has
 *   2) What kind of monster it is (which attacks it has)
 *
 * report_danger should print out what things the
 * user has to worry about.
 */

void report_danger(int defenses, int monster)
{
  int theMonster = monster;
  int result = (defenses & monster); //'and' defenses and monsters together so
                                     //we can see which defenses counter
                                     //the monsters attack (if any)  
  
  printf("Beware of: ");

  for (int i = 1; i < 7; i++)
    {
      if ((theMonster & 0x01) && !(result & 0x01))//If the monsters bit is turned on, and theres no corrosponding
	{                                         //bit turned on in result, then the player has something to worry about
	  switch(i)
	    {
	    case 1://for example, the first bit corrosponds to fire
	      printf("Fire ");
	      break;
	    case 2:
	      printf("Bite ");
	      break;
	    case 3:
	      printf("Claw ");
	      break;
	    case 4:
	      printf("Acid ");
	      break;
	    case 5:
	      printf("Frost ");
	      break;
	    case 6:
	      printf("Poison ");
	      break;
	    default:
	      break; //nothing to worry about;
	    }
	}
      result = result >> 1;//move the bits over 1
      theMonster = theMonster >> 1;
    }


  printf("\n");
  return;
}
