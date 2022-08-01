	.text
	.syntax unified
	.eabi_attribute	67, "2.09"
	.cpu	cortex-m0
	.eabi_attribute	6, 12
	.eabi_attribute	7, 77
	.eabi_attribute	8, 0
	.eabi_attribute	9, 1
	.eabi_attribute	34, 0
	.eabi_attribute	17, 1
	.eabi_attribute	20, 1
	.eabi_attribute	21, 0
	.eabi_attribute	23, 3
	.eabi_attribute	24, 1
	.eabi_attribute	25, 1
	.eabi_attribute	38, 1
	.eabi_attribute	18, 4
	.eabi_attribute	26, 2
	.eabi_attribute	14, 0
	.file	"test2.c"
	.globl	main
	.p2align	1
	.type	main,%function
	.code	16
	.thumb_func
main:
	.fnstart
	.pad	#20
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
	bx	lr
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cantunwind
	.fnend

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",%progbits
	.addrsig
	.eabi_attribute	30, 6
