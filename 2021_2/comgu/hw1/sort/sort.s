.global sort
sort:
/* first  argument (x10): the address of output buffer */
/* second argument (x11): the start address of the incoming list */
/* third  argument (x12): the length of incoming list */

   addi    x28, x0, 0
   beq     x28, x12, Exit
loop:
     slli     x5, x28, 2
     add     x29, x11, x5         
   add     x30, x10, x5            
   lw      x31, 0(x29)
   sw      x31, 0(x30) 
   addi    x28, x28, 1
   bne    x28, x12, loop          


/* start */

   li      x18, 0         /* key = 0 */   
   li      x6, 0         /* j=0 */      
   li       x5, 1          /* i=1 */
   li       x30, 0
   li      x31, 0

L1:
   add     x30, x30, x5     /* x30 = i */
   slli    x30, x30, 2      /* x30 = [i] */
   add     x30, x10, x30    /* x30 = *A[i] */
   ld      x18, 0(x30)      /* key = A[i]; */
   addi    x6, x5, -1       /* j=i-1 */

L2:
   add      x31, x31, x6    /* x31 = j */
   slli  	x31, x31, 2     /* x31 = [j] */
   add      x31, x10, x31   /* x31 = *A[j] */
   ld       x19, 0(x31)     /* x19 = A[j]; */

   blt      x19, x18, L3    /* if(A[j]<k) goto L3 */
   beq      x19, x18, L3    /* if(A[j]=k) goto L3 */

   addi    x31, x31, 4
   sd      x19, 0(x31)      /* else A[j+1]=A[j] */
   addi    x31, x31, -4

L3:

   addi    x6, x6, -1       /* j-- */
   bge     x6, x0, L2       /* if(j>=0) goto L2: */

    sd      x18, 0(x31)      /* A[j+1] = key */

    addi    x5, x5, 1      	 /* i++ */
    blt     x5, x12, L1      /* if(i<Len) goto L1: */

Exit:
   jr   x1



.global insert
insert:
/* Recommanded arguments */
/* first argument (x10): the address of output buffer */
/* third  argument (x11): the current length of output buffer */
/* third  argument (x12): the integer to insert */
/* your insert code from here */
/* your insert code to here */	
	jr	x1
