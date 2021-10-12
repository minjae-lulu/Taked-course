.global gcd
gcd:
	blt x10, x11, Change
	beq x0, x0, Euclid

Change:
	addi x12, x11, 0
	addi x11, x10, 0
	addi x10, x12, 0
	beq x0, x0, gcd

Euclid:
	div x12, x10, x11
	mul x13, x11, x12
	sub x14, x10, x13
	beq x14, x0, Exit
	addi x10, x11, 0
	addi x11, x14, 0
	beq x0, x0, Euclid

Exit:
	addi x10, x11, 0
	jr x1


.global lcm
lcm:
	mul x15, x10, x11
	blt x10, x11, Change2
	beq x0, x0, Euclid2

Change2:
	addi x12, x11, 0
	addi x11, x10, 0
	addi x10, x12, 0
	beq x0, x0, lcm

Euclid2:
	div x12, x10, x11
	mul x13, x11, x12
	sub x14, x10, x13
	beq x14, x0, Exit2
	addi x10, x11, 0
	addi x11, x14, 0
	beq x0, x0, Euclid2

Exit2:
	addi x10, x11, 0
	div x10, x15, x10
	jr x1