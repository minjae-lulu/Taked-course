
.global matmul

matmul:
/* save return address (in x1) in stack*/
/* first argument (x10): the address of output buffer */
/* second argument (x11): the start address of a */
/* third argument (x12): the start address of b */
/* fourth argument (x13): the dimension */

	addi    x2, x2, -8
	sd      x1, 0(x2)
	
	li      x28, 0
	add     x28, x28, x13		/* x28 = n */
	li      x5, 0				/* i=0 */
	
L1:   
	li      x6, 0				/* j=0 */

L2:   
	li      x7, 0				/* k=0 */
	mul     x30, x5, x28		/* x30 = in  */
	add     x30, x30, x6		/* x30 = in + j */
	slli    x30, x30, 2			/* x30 = [in+j] */
	add     x30, x10, x30		/* x30 = *C[in+j] */
	ld      x18, 0(x30)			/* x18 = C[in+j] */
	li		x18, 0 				/* initial */

L3:   
	mul     x29, x7, x28		/* x29 = kn  */
	add     x29, x29, x6		/* x29 = kn + j */
	slli    x29, x29, 2			/* x29 = [kn+j] */
	add     x29, x12, x29		/* x29 = *B[kn+j] */
	ld      x19, 0(x29)			/* x19 = B[kn+j] */

	mul     x31, x5, x28   		/* x31 =  in*/
	add     x31, x31, x7		/* x31 =  in+k */
	slli    x31, x31, 2			/* x31 =  [in+k] */
	add     x31, x11, x31		/* x31 =  *A[in+k] */
	ld      x20, 0(x31)			/* x20 =  A[in+k] */

	mul     x19, x20, x19		/* x18 +=  A[in+k]B[kn+j] */
	add     x18, x18, x19		

	addi   x7, x7, 1 			/* k++ */
	blt   x7, x28, L3			/* if(k<n) go to L3  */
	sd     x18, 0(x30)			/* x18 push C[in+j] */

	addi   x6, x6, 1			/* j++ */
	blt   x6, x28, L2			/* if(j<n) go to L2 */
	addi   x5, x5, 1			/* i++ */
	blt   x5, x28, L1			/* if(i<n) go to L1 */
   

/* your matmul code to here */
	ld     x1, 0(x2)
	addi   x2, x2, 8
	jr x1





.global matmul_idx
matmul_idx:
/* Recommanded arguments */
/* first argument (x10): the address of output buffer */
/* second argument (x11): the start address of a */
/* third argument (x12): the start address of b */
/* fourth argument (x13): the dimension */
/* fourth argument (x14): row index of the result matrix to fill out */
/* fourth argument (x15): column index of the result matrix to fill out */
/* your matmul_idx (helper function) code from here */

/* your matmul_idx (helper function) code to here */
   jr x1