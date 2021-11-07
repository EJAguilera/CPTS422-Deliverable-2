package halsteadChecks;

import com.puppycrawl.tools.checkstyle.api.*;
import Tokens.Operators;
import Tokens.Operands;

public class HalsteadLengthCheck extends AbstractCheck  {
	
	// private int operators = 0;
	// private int operands = 0;
	private int halsteadLength = 0;

	@Override
	public int[] getDefaultTokens() {

		// Gets all tokens considered 'operators' by Checkstyle.
		return this.getAcceptableTokens();
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		this.halsteadLength++;
	}

	@Override
	public int[] getAcceptableTokens() {

		// Gets all tokens considered 'operators' by Checkstyle.
		return this.getRequiredTokens();
	}
	
	public int getHalsteadLength() {
		return this.halsteadLength;
	}

	@Override
	public int[] getRequiredTokens() {
		int[] combination = new int[Operators.operators().length + Operands.operands().length];
		System.arraycopy(Operators.operators(), 0, combination, 0, Operators.operators().length);
		System.arraycopy(Operands.operands(), 0, combination, Operators.operators().length, Operands.operands().length);
		
		return combination;
	}

	// Automatically log 'violation' for display purposes 
	@Override
	public void finishTree(DetailAST root) {
		// Output incremented and logged to console
		log(root, String.format("EJA ACHECK: Halstead Length: %s", halsteadLength));
		// Reset values for next iteration of check
		// operators = operands = 
		this.halsteadLength = 0; 
	}
}
