# This program reads a string from the user and converts all the upper case
# letters in the string into corresponding lower case ones.

	.data
str1:	.asciiz	"Enter a string:\n"
str2:	.asciiz "The transformed string:\n"
buffer:	.byte 0:80

	.text
main:	addi	$v0, $zero, 4
	la	$a0, str1
	syscall			# prints prompt
	addi	$v0, $zero, 8	
	la	$a0, buffer
	addi	$a1, $zero, 80
	syscall			# reads a string
	la	$s0, buffer
	addiu	$t2, $zero, 10  # loads newline character into $t2
for:	lbu	$t1, 0($s0)
	beq	$t1, $t2, endfor # stops if encounters newline
	sltiu	$t3, $t1, 65	# A = 65
	bne	$t3, $zero, next
	sltiu	$t3, $t1, 91	# Z = 90
        beq	$t3, $zero, next
	addiu	$t1, $t1, 32	# a - A = 32
        sb	$t1, 0($s0)
next:	addi	$s0, $s0, 1
	j for
endfor:	addi    $v0, $zero, 4
        la      $a0, str2
        syscall			# prints the message str2
	addi	$v0, $zero, 4
	la	$a0, buffer
	syscall			# prints the transformed string
	j main
 	addi	$v0, $zero, 10
	syscall			# exit

