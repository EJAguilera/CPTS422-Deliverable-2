package BCheck;

import Tokens.Operators;
import com.puppycrawl.tools.checkstyle.api.*;

// Get the count of the operators present in the passed AST.
public class GetOperatorsCheck extends AbstractCheck {
	private static int count = 0;
	
	public static int getOperators() {
		return count;
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		count++;
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		count = 0;
	}

	@Override
	public int[] getDefaultTokens() {
		return this.getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		return this.getRequiredTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return Operators.operators();
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Number of operators: %s", this.getOperators()));
	}
}
