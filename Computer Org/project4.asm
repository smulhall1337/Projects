.data
hex: 	.byte 0:200 #hex buffer
octal : .byte 0:200 #octal buffer
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
calc:	addi	$v0, $zero, 4
	la	$a0, str2
	syscall				# prints prompt for octal 
	addi 	$t4, $zero, 3
	div	$s4, $t4
	mflo 	$t4			#t4 stores the quotient(hi still stores remainder which we'll computer later	
	add	$t6, $zero, $zero
calcfor:slt  	$t5, $t4, $t6	
	add	$s2, $zero, $zero
	bne	$t5, $zero, remainder
	andi	$s2, $s1, 7
	srl	$s1, $s1, 3
	addi	$v0, $zero, 1
	add	$a0, $zero, $s2
	syscall
	addi	$t6, $t6, 1
	addi	$s7, $s7, 1
	j	calcfor
	
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
	
	
	
	
