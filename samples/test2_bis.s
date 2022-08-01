	sub	sp, #20
	movs	r0, #0
	str	r0, [sp, #16]
	movs	r1, #36
	str	r1, [sp]
	str	r0, [sp, #12]
	movs	r0, #2
	str	r0, [sp, #8]
	ldr	r0, [sp, #12]
	ldr	r1, [sp]
	adds	r0, r0, r1
	lsrs	r1, r0, #31
	adds	r0, r0, r1
	asrs	r0, r0, #1
	str	r0, [sp, #4]
	ldr	r0, [sp, #4]
	cmp	r0, #5
	blt	.LBB0_2
	b	.LBB0_1
.LBB0_1:
	ldr	r0, [sp, #12]
	ldr	r1, [sp, #4]
	lsls	r1, r1, #1
	adds	r0, r0, r1
	str	r0, [sp, #12]
	b	.LBB0_3
.LBB0_2:
	movs	r0, #100
	str	r0, [sp, #12]
	b	.LBB0_3
.LBB0_3:
	ldr	r0, [sp, #16]
	add	sp, #20