.source expr2.ir
.class public expr2
.super java/lang/Object

.method public static foo(II)I
	.limit locals 13
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  [I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.var 8 is T8  I from L_0 to L_1
	.var 9 is T9  I from L_0 to L_1
	.var 10 is T10  I from L_0 to L_1
	.var 11 is T11  I from L_0 to L_1
	.var 12 is T12  I from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 2
	aconst_null
	astore 3
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
	ldc 0
	istore 11
	ldc 0
	istore 12

.line 18
;				T3 := NEWARRAY I 12;
	ldc 12
	newarray int
	astore 3
.line 19
;				T4 := 1;
	ldc 1
	istore 4
.line 20
;				T5 := 56;
	ldc 56
	istore 5
.line 21
;				T3[T4] := T5;
	aload 3
	iload 4
	iload 5
	iastore
.line 22
;				T4 := 5;
	ldc 5
	istore 4
.line 23
;				T5 := 12;
	ldc 12
	istore 5
.line 24
;				T3[T4] := T5;
	aload 3
	iload 4
	iload 5
	iastore
.line 25
;				T6 := T0 I* T1;
	iload 0
	iload 1
	imul
	istore 6
.line 26
;				T7 := 13;
	ldc 13
	istore 7
.line 27
;				T8 := T6 I* T7;
	iload 6
	iload 7
	imul
	istore 8
.line 28
;				T6 := 1;
.line 29
;				T7 := T3[T6];
	ldc 1
	istore 6
	aload 3
	iload 6
	iaload
	istore 7
.line 30
;				T9 := 3;
	ldc 3
	istore 9
.line 31
;				T10 := T7 I* T9;
	iload 7
	iload 9
	imul
	istore 10
.line 32
;				T9 := T8 I- T10;
	iload 8
	iload 10
	isub
	istore 9
.line 33
;				T8 := 5;
.line 34
;				T10 := T3[T8];
	ldc 5
	istore 8
	aload 3
	iload 8
	iaload
	istore 10
.line 35
;				T12 := 4;
	ldc 4
	istore 12
.line 36
;				T11 := CALL factorial(T12)
	iload 12
	invokestatic expr2/factorial(I)I
	istore 11
.line 37
;				T12 := T10 I* T11;
	iload 10
	iload 11
	imul
	istore 12
.line 38
;				T11 := T9 I+ T12;
	iload 9
	iload 12
	iadd
	istore 11
.line 39
;				T2 := T11;
	iload 11
	istore 2
.line 40
;				PRINTLNI T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 2
	invokevirtual java/io/PrintStream/println(I)V
.line 41
;				RETURN T2;
	iload 2
	ireturn
.line 42
;				RETURN;
	return
L_1:
.end method

.method public static factorial(I)I
	.limit locals 7
	.var 0 is T0  I from L_2 to L_3
	.var 1 is T1  I from L_2 to L_3
	.var 2 is T2  I from L_2 to L_3
	.var 3 is T3  Z from L_2 to L_3
	.var 4 is T4  I from L_2 to L_3
	.var 5 is T5  I from L_2 to L_3
	.var 6 is T6  I from L_2 to L_3
	.limit stack 35
L_2:
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

.line 55
;				T2 := 1;
	ldc 1
	istore 2
.line 56
;				T3 := T0 I== T2;
	iload 0
	iload 2
	isub
	ifeq L_4
	ldc 0
	goto L_5
L_4:
	ldc 1
L_5:
	istore 3
.line 57
;				T3 := Z! T3;
	iload 3
	ldc 1
	ixor
	istore 3
.line 58
;				IF T3 GOTO L0;
	iload 3
	ifne L0
.line 59
;				T2 := 1;
	ldc 1
	istore 2
.line 60
;				T1 := T2;
	iload 2
	istore 1
.line 61
;				GOTO L1;
	goto L1
.line 62
;				L0:;
L0:
.line 63
;				T5 := 1;
	ldc 1
	istore 5
.line 64
;				T6 := T0 I- T5;
	iload 0
	iload 5
	isub
	istore 6
.line 65
;				T4 := CALL factorial(T6)
	iload 6
	invokestatic expr2/factorial(I)I
	istore 4
.line 66
;				T5 := T0 I* T4;
	iload 0
	iload 4
	imul
	istore 5
.line 67
;				T1 := T5;
	iload 5
	istore 1
.line 68
;				GOTO L1;
	goto L1
.line 69
;				L1:;
L1:
.line 70
;				RETURN T1;
	iload 1
	ireturn
.line 71
;				RETURN;
	return
L_3:
.end method

.method public static __main()V
	.limit locals 23
	.var 0 is T0  F from L_6 to L_7
	.var 1 is T1  I from L_6 to L_7
	.var 2 is T2  I from L_6 to L_7
	.var 3 is T3  I from L_6 to L_7
	.var 4 is T4  F from L_6 to L_7
	.var 5 is T5  I from L_6 to L_7
	.var 6 is T6  Z from L_6 to L_7
	.var 7 is T7  Ljava/lang/String; from L_6 to L_7
	.var 8 is T8  I from L_6 to L_7
	.var 9 is T9  I from L_6 to L_7
	.var 10 is T10  I from L_6 to L_7
	.var 11 is T11  I from L_6 to L_7
	.var 12 is T12  I from L_6 to L_7
	.var 13 is T13  I from L_6 to L_7
	.var 14 is T14  I from L_6 to L_7
	.var 15 is T15  I from L_6 to L_7
	.var 16 is T16  I from L_6 to L_7
	.var 17 is T17  I from L_6 to L_7
	.var 18 is T18  I from L_6 to L_7
	.var 19 is T19  I from L_6 to L_7
	.var 20 is T20  I from L_6 to L_7
	.var 21 is T21  I from L_6 to L_7
	.var 22 is T22  I from L_6 to L_7
	.limit stack 35
L_6:
	ldc 0.0
	fstore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0.0
	fstore 4
	ldc 0
	istore 5
	ldc 0
	istore 6
	aconst_null
	astore 7
	ldc 0
	istore 8
	ldc 0
	istore 9
	ldc 0
	istore 10
	ldc 0
	istore 11
	ldc 0
	istore 12
	ldc 0
	istore 13
	ldc 0
	istore 14
	ldc 0
	istore 15
	ldc 0
	istore 16
	ldc 0
	istore 17
	ldc 0
	istore 18
	ldc 0
	istore 19
	ldc 0
	istore 20
	ldc 0
	istore 21
	ldc 0
	istore 22

.line 100
;				T2 := 2;
	ldc 2
	istore 2
.line 101
;				T3 := 3;
	ldc 3
	istore 3
.line 102
;				T1 := CALL foo(T2T3)
	iload 2
	iload 3
	invokestatic expr2/foo(II)I
	istore 1
.line 103
;				T4 := I2F T1;
	iload 1
	i2f
	fstore 4
.line 104
;				T0 := T4;
	fload 4
	fstore 0
.line 105
;				PRINTLNF T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 0
	invokevirtual java/io/PrintStream/println(F)V
.line 106
;				T1 := 2;
	ldc 2
	istore 1
.line 107
;				T2 := 4;
	ldc 4
	istore 2
.line 108
;				T5 := 2;
	ldc 2
	istore 5
.line 109
;				T3 := CALL factorial(T5)
	iload 5
	invokestatic expr2/factorial(I)I
	istore 3
.line 110
;				T5 := T2 I- T3;
	iload 2
	iload 3
	isub
	istore 5
.line 111
;				T6 := T1 I< T5;
	iload 1
	iload 5
	isub
	iflt L_8
	ldc 0
	goto L_9
L_8:
	ldc 1
L_9:
	istore 6
.line 112
;				T6 := Z! T6;
	iload 6
	ldc 1
	ixor
	istore 6
.line 113
;				IF T6 GOTO L0;
	iload 6
	ifne L0
.line 114
;				T7 := "complicated expression";
	ldc "complicated expression"
	astore 7
.line 115
;				PRINTLNU T7;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 7
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 116
;				T8 := 4;
	ldc 4
	istore 8
.line 117
;				T9 := 8;
	ldc 8
	istore 9
.line 118
;				T10 := T8 I* T9;
	iload 8
	iload 9
	imul
	istore 10
.line 119
;				T9 := 3;
	ldc 3
	istore 9
.line 120
;				T8 := CALL factorial(T9)
	iload 9
	invokestatic expr2/factorial(I)I
	istore 8
.line 121
;				T11 := T10 I- T8;
	iload 10
	iload 8
	isub
	istore 11
.line 122
;				PRINTLNI T11;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 11
	invokevirtual java/io/PrintStream/println(I)V
.line 123
;				GOTO L0;
	goto L0
.line 124
;				L0:;
L0:
.line 125
;				T10 := 4;
	ldc 4
	istore 10
.line 126
;				T12 := 3;
	ldc 3
	istore 12
.line 127
;				T11 := CALL factorial(T12)
	iload 12
	invokestatic expr2/factorial(I)I
	istore 11
.line 128
;				T12 := T10 I* T11;
	iload 10
	iload 11
	imul
	istore 12
.line 129
;				T13 := 2;
	ldc 2
	istore 13
.line 130
;				T14 := T12 I- T13;
	iload 12
	iload 13
	isub
	istore 14
.line 131
;				T13 := 1;
	ldc 1
	istore 13
.line 132
;				T15 := 3;
	ldc 3
	istore 15
.line 133
;				T16 := T13 I* T15;
	iload 13
	iload 15
	imul
	istore 16
.line 134
;				T17 := 2;
	ldc 2
	istore 17
.line 135
;				T15 := CALL factorial(T17)
	iload 17
	invokestatic expr2/factorial(I)I
	istore 15
.line 136
;				T17 := T16 I- T15;
	iload 16
	iload 15
	isub
	istore 17
.line 137
;				T16 := 4;
	ldc 4
	istore 16
.line 138
;				T18 := T17 I* T16;
	iload 17
	iload 16
	imul
	istore 18
.line 139
;				T19 := T14 I+ T18;
	iload 14
	iload 18
	iadd
	istore 19
.line 140
;				T20 := 3;
	ldc 3
	istore 20
.line 141
;				T22 := 2;
	ldc 2
	istore 22
.line 142
;				T21 := CALL factorial(T22)
	iload 22
	invokestatic expr2/factorial(I)I
	istore 21
.line 143
;				T22 := T20 I* T21;
	iload 20
	iload 21
	imul
	istore 22
.line 144
;				T18 := CALL factorial(T22)
	iload 22
	invokestatic expr2/factorial(I)I
	istore 18
.line 145
;				T14 := CALL factorial(T18)
	iload 18
	invokestatic expr2/factorial(I)I
	istore 14
.line 146
;				T20 := T19 I+ T14;
	iload 19
	iload 14
	iadd
	istore 20
.line 147
;				T4 := I2F T20;
	iload 20
	i2f
	fstore 4
.line 148
;				T0 := T4;
	fload 4
	fstore 0
.line 149
;				PRINTLNF T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 0
	invokevirtual java/io/PrintStream/println(F)V
.line 150
;				RETURN;
	return
.line 151
;				RETURN;
	return
L_7:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic expr2/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
