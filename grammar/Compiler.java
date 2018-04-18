/*
 * Compiler.java
 *
 * A starting place for the unnamed language compiler for CSC 435/535
 *
 */

import org.antlr.runtime.*;

import AST.PrettyPrintVisitor;
import AST.Program;
import Semantic.SemanticException;
import Semantic.TypeCheckVisitor;
import IR.*;

import java.io.*;

public class Compiler {
	public static void main (String[] args) throws Exception {
		ANTLRInputStream input;

		if (args.length == 0 ) {
			System.out.println("Usage: Test filename.ul");
			return;
		}
		else {
			input = new ANTLRInputStream(new FileInputStream(args[0]));
		}

		// The name of the grammar here is "ulGrammar",
		// so ANTLR generates ulGrammerLexer and ulGrammarParser
		ulGrammarLexer lexer = new ulGrammarLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ulGrammarParser parser = new ulGrammarParser(tokens);
		TypeCheckVisitor tcv = new TypeCheckVisitor();
		IRGenerator irg = new IRGenerator();

		try {
			Program p = parser.program();
			p.accept(tcv);

			String[] split = args[args.length-1].split("/");
			String pname = split[split.length - 1];
			pname = pname.substring(0, pname.length()-3);

			p.accept(irg, pname);
		}
		catch (RecognitionException e )	{
			// A lexical or parsing error occured.
			// ANTLR will have already printed information on the
			// console due to code added to the grammar.  So there is
			// nothing to do here.
		}
		catch (Exception e) {
			if (!(e instanceof SemanticException) || !(e instanceof IRException)){
				System.out.println(e);
			}
			e.printStackTrace();
		}
	}
}
