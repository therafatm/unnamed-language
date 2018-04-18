# Unnamed-Language

Unnamed-Language is a simplistic java like language with very basic constructs.
This code snippet is an example of `.ul` syntax. Checkout the `language-specs` 
document for more detailed insight into the language and its capabilities.

```
// recursive function to solve the tower of hanoi puzzles
void towersOfHanoi(int n, char fromRod, char toRod, char auxRod)
{
	if (n == 1)
	{
		print "Move disk 1 from rod ";
		print fromRod;
		print " to rod ";
		println toRod;
	}
	else {
		towersOfHanoi(n-1, fromRod, auxRod, toRod);
		print "Move disk ";
		print n;
		print " from rod ";
		print fromRod;
		print " to rod ";
		println toRod;
		towersOfHanoi(n-1, auxRod, toRod, fromRod);
	}
}

void main() {
    int n;
	n = 4; // Number of disks
	towersOfHanoi(n, 'A', 'C', 'B'); // A, B and C are names of rods
}
```

## What did you use to build this?

If you're reading this, you probably know that a compiler basically has two parts, a frontend and a backend.
In general, the frontend takes care of parsing the input, checking syntax, and type validation, while the
backend generates code for a specific target backend (which in our case was the [JVM](https://en.wikipedia.org/wiki/Java_virtual_machine)).

This project is written in `java` and ANTLR. This project was a result of 4th year class at my university, and `java` was suggested as a possible development language, particularly because it interfaces well the with parsing tool (`ANTLR` v3) we used for this project. `ANTLR` is a language recognition tool, which was used extensively during the parsing and lexing phase of the compiler. `ANTLR` allowed me to parse the input, and create an [Abstract Syntax Tree](https://en.wikipedia.org/wiki/Abstract_syntax_tree). The grammar for the language is described in the `ulGrammar.g` file. 

This AST was core data structure used to output machine code for the JVM. In short, I implemented the [visitor pattern](https://en.wikipedia.org/wiki/Visitor_pattern) (used extensively when building compilers) to traverse
the AST, and output the appropriate JVM instructions. In a real life compiler, there would be significant optimizations applied within the IR, and final assembly generation phases (like reaching definition analysis, live variable analysis, constant propagation, etc), none of which have been implemented as part of this project, but would be one for the future. 

The code generation source can be found within the  `/IR` directory, while the semantic analysis bits in `/Semantic`. There's also a little test runner I added in python, just to keep me sane during development time.

## How do you run this thing?

I don't know how to run this in windows sorry. If you really want to run it in windows, maybe the best thing
to do would be to get a little docker container going running `ubuntu`, ssh in, and try and run this there.
Linux and macOS should work though. Make sure you have the classpath sourced correctly for both ANTLR and Jasmin. You'll have to edit the `setclasspath` file to point to this directory.

Then:
-  `make`
- Run `java Compiler tests/filename.ul > filename.j`
This should print the generated JVM code to the file if successful, or write out the error. 
- Now you can run jasmin, `java jasmin.Main filename.j`
This should make a class file for `filename`
- Now you can run the program, `java filename`


Just fyi, jasmin is an assembler for the JVM. It takes assembly-ish instructions and converts it into class files. I should've also probably added a script to do all this, but lets just push that onto the tech debt stack for now (:

## Extra Things I did

I implemented `int` as a `subtype` of `float`. So you can assign an integer to a float variable but not the converse. This also means that you can add an integer to a float, subtract an integer from a float, and mutiply an integer and a float. 

You can run the test runner `testRunnerCompiler.py` by doing `make`, and then invoking `python testRunnerCompiler.py` from within this directory.
It will print out the status, and then the output of the code.