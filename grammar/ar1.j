.source ar1.ir
.class public ar1
.super java/lang/Object

.method public static returnArr()[I
	.limit locals 3
	.var 0 is T0  [I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.limit stack 35
L_0:
	aconst_null
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2

.line 8
;				T0 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 0
.line 9
;				T1 := 1;
	ldc 1
	istore 1
.line 10
;				T2 := 1;
	ldc 1
	istore 2
.line 11
;				T0[T1] := T2;
	aload 0
	iload 1
	iload 2
	iastore
.line 12
;				RETURN T0;
	aload 0
	areturn
.line 13
;				RETURN;
	return
L_1:
.end method

.method public static returnArrI()I
	.limit locals 3
	.var 0 is T0  [I from L_2 to L_3
	.var 1 is T1  I from L_2 to L_3
	.var 2 is T2  I from L_2 to L_3
	.limit stack 35
L_2:
	aconst_null
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2

.line 22
;				T0 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 0
.line 23
;				T1 := 1;
	ldc 1
	istore 1
.line 24
;				T2 := 1;
	ldc 1
	istore 2
.line 25
;				T0[T1] := T2;
	aload 0
	iload 1
	iload 2
	iastore
.line 26
;				T1 := 1;
.line 27
;				T2 := T0[T1];
	ldc 1
	istore 1
	aload 0
	iload 1
	iaload
	istore 2
.line 28
;				RETURN T2;
	iload 2
	ireturn
.line 29
;				RETURN;
	return
L_3:
.end method

.method public static __main()V
	.limit locals 13
	.var 0 is T0  [I from L_4 to L_5
	.var 1 is T1  I from L_4 to L_5
	.var 2 is T2  I from L_4 to L_5
	.var 3 is T3  I from L_4 to L_5
	.var 4 is T4  I from L_4 to L_5
	.var 5 is T5  I from L_4 to L_5
	.var 6 is T6  I from L_4 to L_5
	.var 7 is T7  I from L_4 to L_5
	.var 8 is T8  Z from L_4 to L_5
	.var 9 is T9  I from L_4 to L_5
	.var 10 is T10  I from L_4 to L_5
	.var 11 is T11  [I from L_4 to L_5
	.var 12 is T12  I from L_4 to L_5
	.limit stack 35
L_4:
	aconst_null
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	ldc 0
	istore 6
	ldc 0
	istore 7
	ldc 0
	istore 8
	ldc 0
	istore 9
	ldc 0
	istore 10
	aconst_null
	astore 11
	ldc 0
	istore 12

.line 48
;				T0 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 0
.line 49
;				T3 := 0;
	ldc 0
	istore 3
.line 50
;				T4 := 7;
	ldc 7
	istore 4
.line 51
;				T0[T3] := T4;
	aload 0
	iload 3
	iload 4
	iastore
.line 52
;				T3 := 1;
	ldc 1
	istore 3
.line 53
;				T4 := 24;
	ldc 24
	istore 4
.line 54
;				T0[T3] := T4;
	aload 0
	iload 3
	iload 4
	iastore
.line 55
;				T5 := 1;
	ldc 1
	istore 5
.line 56
;				T1 := T5;
	iload 5
	istore 1
.line 57
;				T5 := 1;
	ldc 1
	istore 5
.line 58
;				T6 := T1 I+ T5;
	iload 1
	iload 5
	iadd
	istore 6
.line 59
;				T7 := 2;
	ldc 2
	istore 7
.line 60
;				T0[T6] := T7;
	aload 0
	iload 6
	iload 7
	iastore
.line 61
;				T6 := 0;
	ldc 0
	istore 6
.line 62
;				T2 := T6;
	iload 6
	istore 2
.line 63
;				L0:;
L0:
.line 64
;				T7 := 3;
	ldc 3
	istore 7
.line 65
;				T8 := T2 I< T7;
	iload 2
	iload 7
	isub
	iflt L_6
	ldc 0
	goto L_7
L_6:
	ldc 1
L_7:
	istore 8
.line 66
;				T8 := Z! T8;
	iload 8
	ldc 1
	ixor
	istore 8
.line 67
;				IF T8 GOTO L1;
	iload 8
	ifne L1
.line 68
;				T9 := T0[T2];
	aload 0
	iload 2
	iaload
	istore 9
.line 69
;				PRINTLNI T9;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 9
	invokevirtual java/io/PrintStream/println(I)V
.line 70
;				T9 := 1;
	ldc 1
	istore 9
.line 71
;				T10 := T2 I+ T9;
	iload 2
	iload 9
	iadd
	istore 10
.line 72
;				T2 := T10;
	iload 10
	istore 2
.line 73
;				GOTO L0;
	goto L0
.line 74
;				L1:;
L1:
.line 75
;				T11 := CALL returnArr()
	invokestatic ar1/returnArr()[I
	astore 11
.line 76
;				T0[T2] := T11;
	aload 11
	astore 0
.line 77
;				T10 := 1;
.line 78
;				T12 := T0[T10];
	ldc 1
	istore 10
	aload 0
	iload 10
	iaload
	istore 12
.line 79
;				PRINTLNI T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(I)V
.line 80
;				T12 := CALL returnArrI()
	invokestatic ar1/returnArrI()I
	istore 12
.line 81
;				PRINTLNI T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(I)V
.line 82
;				RETURN;
	return
L_5:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic ar1/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
