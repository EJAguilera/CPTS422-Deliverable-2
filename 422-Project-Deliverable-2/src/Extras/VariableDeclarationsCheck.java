package Extras;

import com.puppycrawl.tools.checkstyle.api.*;

public class VariableDeclarationsCheck extends AbstractCheck {
	
	private int count = 0;

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
		return new int[] {
				TokenTypes.VARIABLE_DEF
		};
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		count++;
	}
	
	public int getDeclarations() {
		return this.count;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Number of variable declarations: %s", this.getDeclarations()));
		this.count = 0;
	}
}