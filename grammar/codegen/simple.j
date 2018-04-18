.source simple.ir
.class public simple
.super java/lang/Object

.method public static __main()V
	.limit locals 3
	.var 0 is s Ljava/lang/String; from L_0 to L_1
	.var 1 is y Ljava/lang/String; from L_0 to L_1
	.var 2 is T2  Ljava/lang/String; from L_0 to L_1
	.limit stack 16
L_0:
	aconst_null 
	astore 0
	aconst_null 
	astore 1
	aconst_null 
	astore 2
.line 11
;				T2 := "Hi";
	ldc "Hi"
	astore 2
.line 12
;				T0 := T2;
	aload 2
	astore 0
.line 13
;				T2 := " there";
	ldc " there"
	astore 2
.line 14
;				T1 := T2;
	aload 2
	astore 1
.line 15
;				T2 := T0 U+ T1;
	new java/lang/StringBuffer
	dup
	invokenonvirtual java/lang/StringBuffer/<init>()V
	aload 0
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	aload 1
	invokevirtual java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;
	invokevirtual java/lang/StringBuffer/toString()Ljava/lang/String;
	astore 2
.line 16
;				T0 := T2;
	aload 2
	astore 0
.line 17
;				PRINTLNU T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 0
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 18
;				RETURN;
	return
L_1:
.end method

;--------------------------------------------;
;                                            ;
; Boilerplate                                ;
;                                            ;
;--------------------------------------------;

.method public static main([Ljava/lang/String;)V
	; set limits used by this method
	.limit locals 1
	.limit stack 4
	invokestatic simple/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
