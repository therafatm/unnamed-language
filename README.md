# Assignment 3

## How do you build this thing?

- Run `make`
- You can use `make clean` to get rid of all the `object` and `class` files

## Ok, I've built it. But where are the test files?

- All test files live in the `/tests` folder.

## Cool. Now show me how to run it.

Make sure you have a classpath sourced correctly for both ANTLR and Jasmin.

Then:
-  `make`
- Run `java Compiler tests/filename.ul > filename.j`
This should print the generated JVM code to the file if successful, or write out the error. 
- Now you can run jasmin, `java jasmin.Main filename.j`
This should make a class file for `filename`
- Now you can run the program, `java filename`


## Extra Things I did

I implemented `int` as a `subtype` of `float`. So you can assign an integer to a float variable but not the converse. This also means that you can add an integer to a float, subtract an integer from a float, and mutiply an integer and a float. 

You can run the test runner `testRunnerCompiler.py` by doing `make`, and then invoking `python testRunnerCompiler.py` from within this directory.
It will print out the status, and then the output of the code.