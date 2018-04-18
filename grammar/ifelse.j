.source ifelse.ir
.class public ifelse
.super java/lang/Object

.method public static less(II)V
	.limit locals 4
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  Z from L_0 to L_1
	.var 3 is T3  Ljava/lang/String; from L_0 to L_1
	.limit stack 35
L_0:
	ldc 0
	istore 2
	aconst_null
	astore 3

.line 9
;				T2 := T0 I< T1;
	iload 0
	iload 1
	isub
	iflt L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 2
.line 10
;				T2 := Z! T2;
	iload 2
	ldc 1
	ixor
	istore 2
.line 11
;				IF T2 GOTO L0;
	iload 2
	ifne L0
.line 12
;				PRINTLNI T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 0
	invokevirtual java/io/PrintStream/println(I)V
.line 13
;				GOTO L1;
	goto L1
.line 14
;				L0:;
L0:
.line 15
;				T3 := "yolo";
	ldc "yolo"
	astore 3
.line 16
;				PRINTLNU T3;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 3
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 17
;				GOTO L1;
	goto L1
.line 18
;				L1:;
L1:
.line 19
;				PRINTLNI T1;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 1
	invokevirtual java/io/PrintStream/println(I)V
.line 20
;				RETURN;
	return
L_1:
.end method

.method public static __main()V
	.limit locals 2
	.var 0 is T0  I from L_4 to L_5
	.var 1 is T1  I from L_4 to L_5
	.limit stack 35
L_4:
	ldc 0
	istore 0
	ldc 0
	istore 1

.line 28
;				T0 := 2;
	ldc 2
	istore 0
.line 29
;				T1 := 3;
	ldc 3
	istore 1
.line 30
;				CALL less(T0T1)
	iload 0
	iload 1
	invokestatic ifelse/less(II)V
.line 31
;				RETURN;
	return
L_5:
.end method


;------boilerplate------;

.method public static main([Ljava/lang/String;)V
	.limit locals 1
	.limit stack 4
	invokestatic ifelse/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
