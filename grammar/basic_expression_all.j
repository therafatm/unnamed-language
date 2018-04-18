.source basic_expression_all.ir
.class public basic_expression_all
.super java/lang/Object

.method public static basic()I
	.limit locals 34
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  F from L_0 to L_1
	.var 4 is T4  F from L_0 to L_1
	.var 5 is T5  F from L_0 to L_1
	.var 6 is T6  Ljava/lang/String; from L_0 to L_1
	.var 7 is T7  Ljava/lang/String; from L_0 to L_1
	.var 8 is T8  C from L_0 to L_1
	.var 9 is T9  C from L_0 to L_1
	.var 10 is T10  C from L_0 to L_1
	.var 11 is T11  Z from L_0 to L_1
	.var 12 is T12  Z from L_0 to L_1
	.var 13 is T13  C from L_0 to L_1
	.var 14 is T14  I from L_0 to L_1
	.var 15 is T15  I from L_0 to L_1
	.var 16 is T16  F from L_0 to L_1
	.var 17 is T17  F from L_0 to L_1
	.var 18 is T18  Ljava/lang/String; from L_0 to L_1
	.var 19 is T19  Ljava/lang/String; from L_0 to L_1
	.var 20 is T20  F from L_0 to L_1
	.var 21 is T21  Z from L_0 to L_1
	.var 22 is T22  Ljava/lang/String; from L_0 to L_1
	.var 23 is T23  F from L_0 to L_1
	.var 24 is T24  Ljava/lang/String; from L_0 to L_1
	.var 25 is T25  Ljava/lang/String; from L_0 to L_1
	.var 26 is T26  Z from L_0 to L_1
	.var 27 is T27  C from L_0 to L_1
	.var 28 is T28  Ljava/lang/String; from L_0 to L_1
	.var 29 is T29  Ljava/lang/String; from L_0 to L_1
	.var 30 is T30  Z from L_0 to L_1
	.var 31 is T31  Ljava/lang/String; from L_0 to L_1
	.var 32 is T32  Z from L_0 to L_1
	.var 33 is T33  I from L_0 to L_1
	.limit stack 35
L_0:
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
	ldc 0.0
	fstore 16
	ldc 0.0
	fstore 17
	aconst_null
	astore 18
	aconst_null
	astore 19
	ldc 0.0
	fstore 20
	ldc 0
	istore 21
	aconst_null
	astore 22
	ldc 0.0
	fstore 23
	aconst_null
	astore 24
	aconst_null
	astore 25
	ldc 0
	istore 26
	ldc 0
	istore 27
	aconst_null
	astore 28
	aconst_null
	astore 29
	ldc 0
	istore 30
	aconst_null
	astore 31
	ldc 0
	istore 32
	ldc 0
	istore 33

.line 39
;				T13 := 'a';
	ldc 39
	istore 13
.line 40
;				T8 := T13;
	iload 13
	istore 8
.line 41
;				T13 := 'b';
	ldc 39
	istore 13
.line 42
;				T9 := T13;
	iload 13
	istore 9
.line 43
;				T14 := 1;
	ldc 1
	istore 14
.line 44
;				T0 := T14;
	iload 14
	istore 0
.line 45
;				T14 := 2;
	ldc 2
	istore 14
.line 46
;				T1 := T14;
	iload 14
	istore 1
.line 47
;				T15 := 3;
	ldc 3
	istore 15
.line 48
;				T2 := T15;
	iload 15
	istore 2
.line 49
;				T16 := 1.1;
	ldc 1.1
	fstore 16
.line 50
;				T3 := T16;
	fload 16
	fstore 3
.line 51
;				T16 := 2.2;
	ldc 2.2
	fstore 16
.line 52
;				T4 := T16;
	fload 16
	fstore 4
.line 53
;				T17 := 3.3;
	ldc 3.3
	fstore 17
.line 54
;				T5 := T17;
	fload 17
	fstore 5
.line 55
;				T18 := "hey ";
	ldc "hey "
	astore 18
.line 56
;				T6 := T18;
	aload 18
	astore 6
.line 57
;				T18 := "nw ";
	ldc "nw "
	astore 18
.line 58
;				T7 := T18;
	aload 18
	astore 7
.line 59
;				T19 := "float + int";
	ldc "float + int"
	astore 19
.line 60
;				PRINTLNU T19;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 19
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 61
;				T17 := I2F T0;
	iload 0
	i2f
	fstore 17
.line 62
;				T20 := T17 F+ T4;
	fload 17
	fload 4
	fadd
	fstore 20
.line 63
;				T4 := T20;
	fload 20
	fstore 4
.line 64
;				PRINTLNF T3;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 3
	invokevirtual java/io/PrintStream/println(F)V
.line 65
;				T19 := "int < float";
	ldc "int < float"
	astore 19
.line 66
;				PRINTLNU T19;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 19
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 67
;				T20 := I2F T0;
	iload 0
	i2f
	fstore 20
.line 68
;				T21 := T4 F< T20;
	fload 4
	fload 20
	fcmpg
	iflt L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 21
.line 69
;				T12 := T21;
	iload 21
	istore 12
.line 70
;				PRINTLNZ T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(Z)V
.line 71
;				T22 := "int < int";
	ldc "int < int"
	astore 22
.line 72
;				PRINTLNU T22;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 22
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 73
;				T21 := T0 I< T1;
	iload 0
	iload 1
	isub
	iflt L_4
	ldc 0
	goto L_5
L_4:
	ldc 1
L_5:
	istore 21
.line 74
;				T12 := T21;
	iload 21
	istore 12
.line 75
;				PRINTLNZ T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(Z)V
.line 76
;				T22 := "float + float";
	ldc "float + float"
	astore 22
.line 77
;				PRINTLNU T22;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 22
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 78
;				T15 := T0 I+ T1;
	iload 0
	iload 1
	iadd
	istore 15
.line 79
;				T23 := I2F T15;
	iload 15
	i2f
	fstore 23
.line 80
;				T3 := T23;
	fload 23
	fstore 3
.line 81
;				PRINTLNF T3;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	fload 3
	invokevirtual java/io/PrintStream/println(F)V
.line 82
;				T24 := "string + string";
	ldc "string + string"
	astore 24
.line 83
;				PRINTLNU T24;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 24
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 84
;				T24 := T6 U+ T7;
	new java/lang/StringBuffer
	dup
	invokenonvirtual java/lang/StringBuffer/<init>()V
	aload 6
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	aload 7
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;
	astore 24
.line 85
;				T6 := T24;
	aload 24
	astore 6
.line 86
;				PRINTLNU T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 6
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 87
;				T25 := "string < string";
	ldc "string < string"
	astore 25
.line 88
;				PRINTLNU T25;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 25
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 89
;				T26 := T6 U< T7;
	aload 6
	aload 7
	invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I
	iflt L_6
	ldc 0
	goto L_7
L_6:
	ldc 1
L_7:
	istore 26
.line 90
;				T11 := T26;
	iload 26
	istore 11
.line 91
;				PRINTLNZ T11;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 11
	invokevirtual java/io/PrintStream/println(Z)V
.line 92
;				T25 := "char add";
	ldc "char add"
	astore 25
.line 93
;				PRINTLNU T25;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 25
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 94
;				T27 := T8 C+ T9;
	iload 8
	iload 9
	iadd
	i2c
	istore 27
.line 95
;				T10 := T27;
	iload 27
	istore 10
.line 96
;				PRINTLNC T10;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 10
	invokevirtual java/io/PrintStream/println(C)V
.line 97
;				T28 := "char sub";
	ldc "char sub"
	astore 28
.line 98
;				PRINTLNU T28;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 28
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 99
;				T27 := T8 C- T9;
	iload 8
	iload 9
	isub
	i2c
	istore 27
.line 100
;				T10 := T27;
	iload 27
	istore 10
.line 101
;				PRINTLNC T10;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 10
	invokevirtual java/io/PrintStream/println(C)V
.line 102
;				T28 := "char < char";
	ldc "char < char"
	astore 28
.line 103
;				PRINTLNU T28;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 28
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 104
;				T26 := T8 C< T9;
	iload 8
	iload 9
	isub
	iflt L_8
	ldc 0
	goto L_9
L_8:
	ldc 1
L_9:
	istore 26
.line 105
;				T11 := T26;
	iload 26
	istore 11
.line 106
;				PRINTLNZ T11;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 11
	invokevirtual java/io/PrintStream/println(Z)V
.line 107
;				T29 := "char == char";
	ldc "char == char"
	astore 29
.line 108
;				PRINTLNU T29;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 29
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 109
;				T30 := T8 C== T9;
	iload 8
	iload 9
	isub
	ifeq L_10
	ldc 0
	goto L_11
L_10:
	ldc 1
L_11:
	istore 30
.line 110
;				T12 := T30;
	iload 30
	istore 12
.line 111
;				PRINTLNZ T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(Z)V
.line 112
;				T29 := "bool == bool";
	ldc "bool == bool"
	astore 29
.line 113
;				PRINTLNU T29;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 29
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 114
;				T30 := T11 Z== T12;
	iload 11
	iload 12
	isub
	ifeq L_12
	ldc 0
	goto L_13
L_12:
	ldc 1
L_13:
	istore 30
.line 115
;				PRINTLNZ T30;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 30
	invokevirtual java/io/PrintStream/println(Z)V
.line 116
;				T31 := "bool < bool";
	ldc "bool < bool"
	astore 31
.line 117
;				PRINTLNU T31;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 31
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 118
;				T32 := T11 Z< T12;
	iload 11
	iload 12
	isub
	iflt L_14
	ldc 0
	goto L_15
L_14:
	ldc 1
L_15:
	istore 32
.line 119
;				PRINTLNZ T32;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 32
	invokevirtual java/io/PrintStream/println(Z)V
.line 120
;				T33 := 1;
	ldc 1
	istore 33
.line 121
;				RETURN T33;
	iload 33
	ireturn
.line 122
;				RETURN;
	return
L_1:
.end method

.method public static arrays()I
	.limit locals 16
	.var 0 is T0  [Z from L_16 to L_17
	.var 1 is T1  Z from L_16 to L_17
	.var 2 is T2  [I from L_16 to L_17
	.var 3 is T3  I from L_16 to L_17
	.var 4 is T4  I from L_16 to L_17
	.var 5 is T5  Z from L_16 to L_17
	.var 6 is T6  I from L_16 to L_17
	.var 7 is T7  Z from L_16 to L_17
	.var 8 is T8  I from L_16 to L_17
	.var 9 is T9  I from L_16 to L_17
	.var 10 is T10  I from L_16 to L_17
	.var 11 is T11  I from L_16 to L_17
	.var 12 is T12  Ljava/lang/String; from L_16 to L_17
	.var 13 is T13  Z from L_16 to L_17
	.var 14 is T14  I from L_16 to L_17
	.var 15 is T15  I from L_16 to L_17
	.limit stack 35
L_16:
	aconst_null
	astore 0
	ldc 0
	istore 1
	aconst_null
	astore 2
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
	ldc 0
	istore 11
	aconst_null
	astore 12
	ldc 0
	istore 13
	ldc 0
	istore 14
	ldc 0
	istore 15

.line 144
;				T0 := NEWARRAY Z 3;
	ldc 3
	newarray boolean
	astore 0
.line 145
;				T2 := NEWARRAY I 3;
	ldc 3
	newarray int
	astore 2
.line 146
;				T4 := 0;
	ldc 0
	istore 4
.line 147
;				T3 := T4;
	iload 4
	istore 3
.line 148
;				T4 := 0;
	ldc 0
	istore 4
.line 149
;				T5 := FALSE;
	ldc 0
	istore 5
.line 150
;				T0[T4] := T5;
	aload 0
	iload 4
	iload 5
	bastore
.line 151
;				T6 := 1;
	ldc 1
	istore 6
.line 152
;				T5 := TRUE;
	ldc 1
	istore 5
.line 153
;				T0[T6] := T5;
	aload 0
	iload 6
	iload 5
	bastore
.line 154
;				T6 := 2;
	ldc 2
	istore 6
.line 155
;				T7 := FALSE;
	ldc 0
	istore 7
.line 156
;				T0[T6] := T7;
	aload 0
	iload 6
	iload 7
	bastore
.line 157
;				T8 := 0;
	ldc 0
	istore 8
.line 158
;				T9 := 1;
	ldc 1
	istore 9
.line 159
;				T2[T8] := T9;
	aload 2
	iload 8
	iload 9
	iastore
.line 160
;				T8 := 1;
	ldc 1
	istore 8
.line 161
;				T9 := 2;
	ldc 2
	istore 9
.line 162
;				T2[T8] := T9;
	aload 2
	iload 8
	iload 9
	iastore
.line 163
;				T10 := 2;
	ldc 2
	istore 10
.line 164
;				T11 := 3;
	ldc 3
	istore 11
.line 165
;				T2[T10] := T11;
	aload 2
	iload 10
	iload 11
	iastore
.line 166
;				T12 := "printing b_array";
	ldc "printing b_array"
	astore 12
.line 167
;				PRINTLNU T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 12
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 168
;				L0:;
L0:
.line 169
;				T10 := 3;
	ldc 3
	istore 10
.line 170
;				T7 := T3 I< T10;
	iload 3
	iload 10
	isub
	iflt L_18
	ldc 0
	goto L_19
L_18:
	ldc 1
L_19:
	istore 7
.line 171
;				T7 := Z! T7;
	iload 7
	ldc 1
	ixor
	istore 7
.line 172
;				IF T7 GOTO L1;
	iload 7
	ifne L1
.line 173
;				T13 := T0[T3];
	aload 0
	iload 3
	baload
	istore 13
.line 174
;				PRINTLNZ T13;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 13
	invokevirtual java/io/PrintStream/println(Z)V
.line 175
;				T11 := T2[T3];
	aload 2
	iload 3
	iaload
	istore 11
.line 176
;				PRINTLNI T11;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 11
	invokevirtual java/io/PrintStream/println(I)V
.line 177
;				T14 := 1;
	ldc 1
	istore 14
.line 178
;				T15 := T3 I+ T14;
	iload 3
	iload 14
	iadd
	istore 15
.line 179
;				T3 := T15;
	iload 15
	istore 3
.line 180
;				GOTO L0;
	goto L0
.line 181
;				L1:;
L1:
.line 182
;				T14 := 9;
	ldc 9
	istore 14
.line 183
;				RETURN T14;
	iload 14
	ireturn
.line 184
;				RETURN;
	return
L_17:
.end method

.method public static __main()V
	.limit locals 2
	.var 0 is T0  I from L_20 to L_21
	.var 1 is T1  I from L_20 to L_21
	.limit stack 35
L_20:
	ldc 0
	istore 0
	ldc 0
	istore 1

.line 192
;				T0 := CALL basic()
	invokestatic basic_expression_all/basic()I
	istore 0
.line 193
;				T1 := CALL arrays()
	invokestatic basic_expression_all/arrays()I
	istore 1
.line 194
;				RETURN;
	return
L_21:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic basic_expression_all/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
