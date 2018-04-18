.source towersOfHanoi.ir
.class public towersOfHanoi
.super java/lang/Object

.method public static towersOfHanoi(ICCC)V
	.limit locals 11
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  C from L_0 to L_1
	.var 2 is T2  C from L_0 to L_1
	.var 3 is T3  C from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  Z from L_0 to L_1
	.var 6 is T6  Ljava/lang/String; from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.var 8 is T8  Ljava/lang/String; from L_0 to L_1
	.var 9 is T9  Ljava/lang/String; from L_0 to L_1
	.var 10 is T10  I from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 4
	ldc 0
	istore 5
	aconst_null
	astore 6
	ldc 0
	istore 7
	aconst_null
	astore 8
	aconst_null
	astore 9
	ldc 0
	istore 10

.line 16
;				T4 := 1;
	ldc 1
	istore 4
.line 17
;				T5 := T0 I== T4;
	iload 0
	iload 4
	isub
	ifeq L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 5
.line 18
;				T5 := Z! T5;
	iload 5
	ldc 1
	ixor
	istore 5
.line 19
;				IF T5 GOTO L0;
	iload 5
	ifne L0
.line 20
;				T6 := "Move disk 1 from rod ";
	ldc "Move disk 1 from rod "
	astore 6
.line 21
;				PRINTU T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 6
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 22
;				PRINTC T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 1
	invokevirtual java/io/PrintStream/print(C)V
.line 23
;				T6 := " to rod ";
	ldc " to rod "
	astore 6
.line 24
;				PRINTU T6;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 6
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 25
;				PRINTLNC T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 2
	invokevirtual java/io/PrintStream/println(C)V
.line 26
;				GOTO L1;
	goto L1
.line 27
;				L0:;
L0:
.line 28
;				T4 := 1;
	ldc 1
	istore 4
.line 29
;				T7 := T0 I- T4;
	iload 0
	iload 4
	isub
	istore 7
.line 30
;				CALL towersOfHanoi(T7T1T3T2)
	iload 7
	iload 1
	iload 3
	iload 2
	invokestatic towersOfHanoi/towersOfHanoi(ICCC)V
.line 31
;				T8 := "Move disk ";
	ldc "Move disk "
	astore 8
.line 32
;				PRINTU T8;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 8
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 33
;				PRINTI T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/print(I)V
.line 34
;				T8 := " from rod ";
	ldc " from rod "
	astore 8
.line 35
;				PRINTU T8;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 8
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 36
;				PRINTC T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 1
	invokevirtual java/io/PrintStream/print(C)V
.line 37
;				T9 := " to rod ";
	ldc " to rod "
	astore 9
.line 38
;				PRINTU T9;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 9
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 39
;				PRINTLNC T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 2
	invokevirtual java/io/PrintStream/println(C)V
.line 40
;				T7 := 1;
	ldc 1
	istore 7
.line 41
;				T10 := T0 I- T7;
	iload 0
	iload 7
	isub
	istore 10
.line 42
;				CALL towersOfHanoi(T10T3T2T1)
	iload 10
	iload 3
	iload 2
	iload 1
	invokestatic towersOfHanoi/towersOfHanoi(ICCC)V
.line 43
;				GOTO L1;
	goto L1
.line 44
;				L1:;
L1:
.line 45
;				RETURN;
	return
L_1:
.end method

.method public static __main()V
	.limit locals 5
	.var 0 is T0  I from L_4 to L_5
	.var 1 is T1  I from L_4 to L_5
	.var 2 is T2  C from L_4 to L_5
	.var 3 is T3  C from L_4 to L_5
	.var 4 is T4  C from L_4 to L_5
	.limit stack 35
L_4:
	ldc 0
	istore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4

.line 56
;				T1 := 4;
	ldc 4
	istore 1
.line 57
;				T0 := T1;
	iload 1
	istore 0
.line 58
;				T2 := 'A';
	ldc 39
	istore 2
.line 59
;				T3 := 'C';
	ldc 39
	istore 3
.line 60
;				T4 := 'B';
	ldc 39
	istore 4
.line 61
;				CALL towersOfHanoi(T0T2T3T4)
	iload 0
	iload 2
	iload 3
	iload 4
	invokestatic towersOfHanoi/towersOfHanoi(ICCC)V
.line 62
;				RETURN;
	return
L_5:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic towersOfHanoi/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
