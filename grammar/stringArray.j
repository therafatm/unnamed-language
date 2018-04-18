.source stringArray.ir
.class public stringArray
.super java/lang/Object

.method public static __main()V
	.limit locals 13
	.var 0 is T0  [Ljava/lang/String; from L_0 to L_1
	.var 1 is T1  Ljava/lang/String; from L_0 to L_1
	.var 2 is T2  Ljava/lang/String; from L_0 to L_1
	.var 3 is T3  Z from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  Ljava/lang/String; from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.var 7 is T7  Ljava/lang/String; from L_0 to L_1
	.var 8 is T8  I from L_0 to L_1
	.var 9 is T9  Ljava/lang/String; from L_0 to L_1
	.var 10 is T10  Ljava/lang/String; from L_0 to L_1
	.var 11 is T11  I from L_0 to L_1
	.var 12 is T12  Z from L_0 to L_1
	.limit stack 35
L_0:
	aconst_null
	astore 0
	aconst_null
	astore 1
	aconst_null
	astore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	aconst_null
	astore 5
	ldc 0
	istore 6
	aconst_null
	astore 7
	ldc 0
	istore 8
	aconst_null
	astore 9
	aconst_null
	astore 10
	ldc 0
	istore 11
	ldc 0
	istore 12

.line 18
;				T0 := NEWARRAY U 10;
	ldc 10
	anewarray java/lang/String
	astore 0
.line 19
;				T4 := 1;
	ldc 1
	istore 4
.line 20
;				T5 := "hey";
	ldc "hey"
	astore 5
.line 21
;				T0[T4] := T5;
	aload 0
	iload 4
	aload 5
	aastore
.line 22
;				T4 := 2;
	ldc 2
	istore 4
.line 23
;				T5 := " there";
	ldc " there"
	astore 5
.line 24
;				T0[T4] := T5;
	aload 0
	iload 4
	aload 5
	aastore
.line 25
;				T6 := 1;
.line 26
;				T7 := T0[T6];
	ldc 1
	istore 6
	aload 0
	iload 6
	aaload
	astore 7
.line 27
;				T8 := 2;
.line 28
;				T9 := T0[T8];
	ldc 2
	istore 8
	aload 0
	iload 8
	aaload
	astore 9
.line 29
;				T10 := T7 U+ T9;
	new java/lang/StringBuffer
	dup
	invokenonvirtual java/lang/StringBuffer/<init>()V
	aload 7
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	aload 9
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;
	astore 10
.line 30
;				T1 := T10;
	aload 10
	astore 1
.line 31
;				PRINTLNU T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 1
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 32
;				T6 := 2;
.line 33
;				T7 := T0[T6];
	ldc 2
	istore 6
	aload 0
	iload 6
	aaload
	astore 7
.line 34
;				T2 := T7;
	aload 7
	astore 2
.line 35
;				PRINTLNU T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 2
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 36
;				T8 := 1;
.line 37
;				T9 := T0[T8];
	ldc 1
	istore 8
	aload 0
	iload 8
	aaload
	astore 9
.line 38
;				T11 := 2;
.line 39
;				T10 := T0[T11];
	ldc 2
	istore 11
	aload 0
	iload 11
	aaload
	astore 10
.line 40
;				T12 := T9 U== T10;
	aload 9
	aload 10
	invokevirtual java/lang/String/compareTo(Ljava/lang/String;)I
	ifeq L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 12
.line 41
;				PRINTLNZ T12;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 12
	invokevirtual java/io/PrintStream/println(Z)V
.line 42
;				RETURN;
	return
L_1:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic stringArray/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
