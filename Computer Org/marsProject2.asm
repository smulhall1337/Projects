	.data
acct: .word 205, 212, 200, 203, 201, 231, 209, 207, 211, 250
amt: .word 123, 5000, 4, 79, 987, 500, 40, 904, 33, 200
str1: .asciiz "Please enter in the account number\n" 
str2: .asciiz "please enter in a transaction type (0 for deposit, 1 for withdraw)\n"
str3: .asciiz "please enter in the transaction amount\n"
str4: .asciiz "Account number does not exist, please try again\n"
str5: .asciiz "Please enter in either a 0 for deposit or a 1 for withdraw\n"
str6: .asciiz "Insufficiant funds! Please enter in a lower value\n"
str7: .asciiz "New balance for Account number: "
str8: .asciiz " is: $"
newline: .asciiz "\n"
		.text
main:   	addi    $v0, $zero, 4           # print string for account number
        	la      $a0, str1		#change strings to a0??
        	syscall
        	la	$s4, acct
        	la	$s5, amt
		lw 	$s0, 0($s4)		#sets both arrays to start at position 0, account number
		lw 	$s1, 0($s5) 		#account amount
		
		addi $v0, $zero, 5		#read account number
		syscall
		add  $a1, $zero,$v0		#store integer in $a1
		addi $t0, $zero, -1		#checking to see if account number is -1, if it is, terminate 
		beq  $t0, $a1, end
		
		beq	$s0, $a0, type		#check if the first position in acct is equal to what the user put in
		
		#not in position 0
		add $t1, $zero, $zero    	#i = 0
acctfor:	slti $t2, $t1, 10
		beq $t2, $zero, erracct		#if i = 10, could not find account in array
		add	$t3, $t1, $t1		# 2i		#move to next spot in array 1
		add	$t3, $t3, $t3		# 4i
		add	$t4, $t3, $s4		#t3 = offset, t4 = actual address of array (i)
		lw $s0, 0($t4)
		#add	$t3, $t1, $t1		# 2i		#move to next spot in array 2
		#add	$t3, $t3, $t3		# 4i		#redundant(keeping for reference)
		add	$t3, $t3, $s5
		lw 	$s1, 0($t3)
		beq $s0, $a1, type
		addi $t1, $t1, 1
		j acctfor
		
erracct:	addi    $v0, $zero, 4           # print error for account not found
        	la      $a0, str4
        	syscall
		j main				#back to top


type:		addi    $v0, $zero, 4           # print string for transaction type
        	la      $a0, str2
        	syscall
		
		addi   $v0, $zero, 5 		#read type
		syscall
		add    $a2, $zero, $v0		#use $v0 instead of a2, since theyre equal(nevermind, we use the operation later in the code)
		addi   $t4, $zero, 1
		beq    $a2, $zero, amount
		beq    $a2, $t4, amount
		
		addi    $v0, $zero, 4           # not a 0 or 1, output error
        	la      $a0, str5
		j type						
		
amount:		addi    $v0, $zero, 4      	# print string for account amount
        	la      $a0, str3
        	syscall
		
		addi 	$v0, $zero, 5 		#read amount
		syscall
		add 	$a3, $zero, $v0
		
		#do the operation(s)
		#we'll make deposit the default action
		beq     $a2, $t4, withdraw	#break if $a1 is a one
		add     $s1, $s1, $a3		#add the amount that the user specified
		sw 	$s1, 0($s5)
		
		j status			#go back to top(but dont forget to output the new amount, I'll do that later)
		
withdraw:	 slt    $t5, $a3, $s1      	#check to see if there are available funds to withdraw
		 beq    $t5, $zero, werror
		 sub    $s1, $s1, $a3
		 sw     $s1, 0($s5)
		 j status
		 
werror:		addi    $v0, $zero, 4           # print string for withdraw error
        	la      $a0, str6
        	syscall
		j amount
		
status:		addi     $v0, $zero, 4           # print strinsg for updated balance
        	la       $a0, str7	
        	syscall				
        	addi     $v0, $zero, 1       	#output the account number
        	add      $a0, $s0, $zero	
        	syscall	
        	addi    $v0, $zero, 4       	#"Is: $..."
        	la      $a0, str8		
        	syscall
        	addi    $v0, $zero, 1       	#output the new account balance
        	add     $a0, $s1, $zero		
        	syscall
        	addi 	$v0, $zero, 4      
        	la    	$a0, newline     	#output the newline
        	syscall
        	j main
						

end:            addi    $v0, $zero, 10 		 # system call code for exit
                syscall           	         # exit
		
		
		
		
	
