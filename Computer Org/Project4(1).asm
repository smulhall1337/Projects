.data
hex: 	.byte 0:200 #hex buffer
str1: 	.asciiz	"Enter in a Hexadecimal value\n"
str2:	.asciiz "Octal value: \n"
nl:	.asciiz "\n"

	.text
main:	addi	$v0, $zero, 4		# newline
	la	$a0, nl
	syscall
	addi	$v0, $zero, 4
	la	$a0, str1
	syscall				# prints prompt for hex
	addi	$s4, $zero, 0		#$s4 = counter for division
	add	$s2, $zero, $zero
	add	$s1, $zero, $zero
	
	addi	$v0, $zero, 8
	la	$a0, hex
	addi	$a1, $zero, 26
	syscall				# reads a hex sting
	addi	$v0, $zero, 4		# newline
	la	$a0, nl
	syscall
	la	$s0, hex		#$s0 = address of buffer
	addiu	$t2, $zero, 10 		# loads newline character into $t2
	
for:	lbu	$s3, 0($s0)		#$t0 = byte we are examinging
	beq	$s3, $zero, exit	#terminate on empty string
	beq	$s3, $t2, calc 		#stops if encounters newline
	sltiu	$t3, $s3, 48		# check to see if its a number 0 = 48
	bne	$t3, $zero, next	#if less than 48, we can just go to next character
	sltiu	$t3, $s3, 57		# 9 = 56 (ascii)
        beq	$t3, $zero, lettercheck	#if greater, we can check if its a letter
        subi	$s3, $s3, 48		#and the ascii value with 1111 to get the binary value of the digit
        sll 	$s1, $s1, 4		#$s1 will keep our binary value of the hex number
        or	$s1, $s1, $s3
        j	next
        
        #octal conversion goes here
calc:   addi $t4, $zero, 9 #i=9
        srl $t5, $s1, 30
        sll $s1, $s1, 2
        addi $v0, $zero, 1
        addu $a0, $zero, $t5
        syscall #print out the integer
calcfor:
        slt $t3, $t4, $zero
        bne $t3, $zero, remainder
        srl $t5, $s1, 29
        or $t5, $t5, $zero
        sll $s1, $s1, 3
        li $v0, 1 #printout integer
        addu $a0, $zero, $t5
        syscall
        addi $t4, $t4, -1 #i--
        j calcfor
	
remainder:
	mfhi	$t8
	beq	$t8, $zero, main	#no remainders, nothing to print out further 
	addi	$v0, $zero, 1
	add	$a0, $zero, $t8
	syscall
	j main
	
lettercheck: 	
	sltiu	$t3, $s3, 97		# a = 97
	bne	$t3, $zero, next	#if either one of these fail, we can move on to the next character
	sltiu	$t3, $s3, 102		# f = 102
        beq	$t3, $zero, next
        subi 	$s3, $s3, 97
        sll	$s1, $s1, 4
        or	$s1, $s1, $s3
        j	next
		
next:	addi	$s0, $s0, 1
	addi 	$s4, $s4, 1
	j for	
	
	
exit: 	addi	$v0, $zero, 10
	syscall			# exit
	
	
	
	
