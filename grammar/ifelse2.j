.source ifelse2.ir
.class public ifelse2
.super java/lang/Object

.method public static less(II)V
	.limit locals 7
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  Z from L_0 to L_1
	.var 5 is T5  Z from L_0 to L_1
	.var 6 is T6  Ljava/lang/String; from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	aconst_null
	astore 6

.line 12
;				T2 := 2;
	ldc 2
	istore 2
.line 13
;				T3 := 2;
	ldc 2
	istore 3
.line 14
;				T4 := T2 I< T3;
	iload 2
	iload 3
	isub
	iflt L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 4
.line 15
;				T4 := Z! T4;
	iload 4
	ldc 1
	ixor
	istore 4
.line 16
;				IF T4 GOTO L0;
	iload 4
	ifne L0
.line 17
;				PRINTI T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/print(I)V
.line 18
;				GOTO L1;
	goto L1
.line 19
;				L0:;
L0:
.line 20
;				T2 := 4;
	ldc 4
	istore 2
.line 21
;				T3 := 2;
	ldc 2
	istore 3
.line 22
;				T5 := T2 I< T3;
	iload 2
	iload 3
	isub
	iflt L_4
	ldc 0
	goto L_5
L_4:
	ldc 1
L_5:
	istore 5
.line 23
;				T5 := Z! T5;
	iload 5
	ldc 1
	ixor
	istore 5
.line 24
;				IF T5 GOTO L2;
	iload 5
	ifne L2
.line 25
;				GOTO L3;
	goto L3
.line 26
;				L2:;
L2:
.line 27
;				T6 := "eyyyy reached here";
	ldc "eyyyy reached here"
	astore 6
.line 28
;				PRINTLNU T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 6
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 29
;				GOTO L3;
	goto L3
.line 30
;				L3:;
L3:
.line 31
;				GOTO L1;
	goto L1
.line 32
;				L1:;
L1:
.line 33
;				PRINTI T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 1
	invokevirtual java/io/PrintStream/print(I)V
.line 34
;				RETURN;
	return
L_1:
.end method

.method public static __main()V
	.limit locals 2
	.var 0 is T0  I from L_6 to L_7
	.var 1 is T1  I from L_6 to L_7
	.limit stack 35
L_6:
	ldc 0
	istore 0
	ldc 0
	istore 1

.line 42
;				T0 := 2;
	ldc 2
	istore 0
.line 43
;				T1 := 3;
	ldc 3
	istore 1
.line 44
;				CALL less(T0T1)
	iload 0
	iload 1
	invokestatic ifelse2/less(II)V
.line 45
;				RETURN;
	return
L_7:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic ifelse2/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
