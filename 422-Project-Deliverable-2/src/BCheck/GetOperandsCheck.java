package BCheck;

import Tokens.Operands;
import Tokens.Operators;
import com.puppycrawl.tools.checkstyle.api.*;

// Get the count of the operands present in the passed AST.
public class GetOperandsCheck extends AbstractCheck {
	private int count = 0;
	
	public int getOperands() {
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
		return Operands.operands();
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Number of operands: %s", this.getOperands()));
	}
}
