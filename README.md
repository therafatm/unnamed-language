# If you're stealing this for homework

You might as well drop the class and spend the $$$ on Fels.

# Unnamed-Language

Unnamed-Language is a simplistic java like language with very basic constructs.
This code snippet is an example of `.ul` syntax. Check out the `language-specs` 
document for more detailed insight into the language and its capabilities!

```java
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

This project is written in `java` and ANTLR, and is a result of 4th year class at my school. `java` was the recommended development language, particularly because it interfaces well the with parsing tool (`ANTLR` v3) used for this project. `ANTLR` is a language recognition tool, which was used extensively during the parsing and lexing phase of the compiler. `ANTLR` allowed for parsing the input, and creating an [Abstract Syntax Tree](https://en.wikipedia.org/wiki/Abstract_syntax_tree) with ease. The `ANTLR` grammar for the language is described in the `ulGrammar.g` file. 

The AST obtained via `ANTLR` was core data structure used to output machine code for the JVM. In short, I implemented the [visitor pattern](https://en.wikipedia.org/wiki/Visitor_pattern) (used extensively in compiler development) to traverse the AST, and output the appropriate JVM instructions. In a real life compiler, there would be significant optimizations applied within the IR, and final assembly generation phases (like reaching definition analysis, live variable analysis, constant propagation, etc), none of which have been implemented as part of this project, but would be one for the future. 

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

## Tests

You can run the test runner `testRunnerCompiler.py` by building, and then invoking `python testRunnerCompiler.py` from within this directory. It should print out the status, and then the output of the code.

## Improvements
- Abstract ASM instructions to objects, store them, and output them instead of storing the instructions in a list raw.
- Maybe add some liveliness analysis
- Change grammar to accept a little more syntax (for e.g. allow for var declarations anywhere and not just the beginning, functions as first class variables, etc)
- Refactor duplicate code
- This code is quite ugly. Make it less ugly.
