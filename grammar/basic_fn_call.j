.source basic_fn_call.ir
.class public basic_fn_call
.super java/lang/Object

.method public static add(II)I
	.limit locals 3
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 2

.line 8
;				T2 := T0 I+ T1;
	iload 0
	iload 1
	iadd
	istore 2
.line 9
;				RETURN T2;
	iload 2
	ireturn
.line 10
;				RETURN;
	return
L_1:
.end method

.method public static mul(FF)F
	.limit locals 3
	.var 0 is T0  F from L_2 to L_3
	.var 1 is T1  F from L_2 to L_3
	.var 2 is T2  F from L_2 to L_3
	.limit stack 35
L_2:
	ldc 0.0
	fstore 2

.line 19
;				T2 := T0 F* T1;
	fload 0
	fload 1
	fmul
	fstore 2
.line 20
;				RETURN T2;
	fload 2
	freturn
.line 21
;				RETURN;
	return
L_3:
.end method

.method public static factorial(F)F
	.limit locals 9
	.var 0 is T0  F from L_4 to L_5
	.var 1 is T1  F from L_4 to L_5
	.var 2 is T2  I from L_4 to L_5
	.var 3 is T3  F from L_4 to L_5
	.var 4 is T4  Z from L_4 to L_5
	.var 5 is T5  F from L_4 to L_5
	.var 6 is T6  I from L_4 to L_5
	.var 7 is T7  F from L_4 to L_5
	.var 8 is T8  F from L_4 to L_5
	.limit stack 35
L_4:
	ldc 0.0
	fstore 1
	ldc 0
	istore 2
	ldc 0.0
	fstore 3
	ldc 0
	istore 4
	ldc 0.0
	fstore 5
	ldc 0
	istore 6
	ldc 0.0
	fstore 7
	ldc 0.0
	fstore 8

.line 36
;				T2 := 1;
	ldc 1
	istore 2
.line 37
;				T3 := I2F T2;
	iload 2
	i2f
	fstore 3
.line 38
;				T4 := T0 F== T3;
	fload 0
	fload 3
	fcmpg
	ifeq L_6
	ldc 0
	goto L_7
L_6:
	ldc 1
L_7:
	istore 4
.line 39
;				T4 := Z! T4;
	iload 4
	ldc 1
	ixor
	istore 4
.line 40
;				IF T4 GOTO L0;
	iload 4
	ifne L0
.line 41
;				T2 := 1;
	ldc 1
	istore 2
.line 42
;				T3 := I2F T2;
	iload 2
	i2f
	fstore 3
.line 43
;				T1 := T3;
	fload 3
	fstore 1
.line 44
;				GOTO L1;
	goto L1
.line 45
;				L0:;
L0:
.line 46
;				T6 := 1;
	ldc 1
	istore 6
.line 47
;				T7 := I2F T6;
	iload 6
	i2f
	fstore 7
.line 48
;				T8 := T0 F- T7;
	fload 0
	fload 7
	fsub
	fstore 8
.line 49
;				T5 := CALL factorial(T8)
	fload 8
	invokestatic basic_fn_call/factorial(F)F
	fstore 5
.line 50
;				T7 := T0 F* T5;
	fload 0
	fload 5
	fmul
	fstore 7
.line 51
;				T1 := T7;
	fload 7
	fstore 1
.line 52
;				GOTO L1;
	goto L1
.line 53
;				L1:;
L1:
.line 54
;				RETURN T1;
	fload 1
	freturn
.line 55
;				RETURN;
	return
L_5:
.end method

.method public static __main()V
	.limit locals 7
	.var 0 is T0  I from L_8 to L_9
	.var 1 is T1  I from L_8 to L_9
	.var 2 is T2  I from L_8 to L_9
	.var 3 is T3  F from L_8 to L_9
	.var 4 is T4  F from L_8 to L_9
	.var 5 is T5  F from L_8 to L_9
	.var 6 is T6  Ljava/lang/String; from L_8 to L_9
	.limit stack 35
L_8:
	ldc 0
	istore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0.0
	fstore 3
	ldc 0.0
	fstore 4
	ldc 0.0
	fstore 5
	aconst_null
	astore 6

.line 68
;				T1 := 2;
	ldc 2
	istore 1
.line 69
;				T2 := 3;
	ldc 3
	istore 2
.line 70
;				T0 := CALL add(T1T2)
	iload 1
	iload 2
	invokestatic basic_fn_call/add(II)I
	istore 0
.line 71
;				PRINTLNI T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/println(I)V
.line 72
;				T4 := 2.1;
	ldc 2.1
	fstore 4
.line 73
;				T5 := 3.5;
	ldc 3.5
	fstore 5
.line 74
;				T3 := CALL mul(T4T5)
	fload 4
	fload 5
	invokestatic basic_fn_call/mul(FF)F
	fstore 3
.line 75
;				PRINTLNF T3;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 3
	invokevirtual java/io/PrintStream/println(F)V
.line 76
;				T6 := "Factorial of 3 is ";
	ldc "Factorial of 3 is "
	astore 6
.line 77
;				PRINTU T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 6
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 78
;				T0 := 3;
	ldc 3
	istore 0
.line 79
;				T4 := I2F T0;
	iload 0
	i2f
	fstore 4
.line 80
;				T3 := CALL factorial(T4)
	fload 4
	invokestatic basic_fn_call/factorial(F)F
	fstore 3
.line 81
;				PRINTLNF T3;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 3
	invokevirtual java/io/PrintStream/println(F)V
.line 82
;				RETURN;
	return
L_9:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic basic_fn_call/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
