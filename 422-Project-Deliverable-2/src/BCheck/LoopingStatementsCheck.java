package BCheck;

import com.puppycrawl.tools.checkstyle.api.*;
import org.apache.commons.*;
import org.apache.commons.lang3.ArrayUtils;

public class LoopingStatementsCheck extends AbstractCheck {
	
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
		return new int[] {TokenTypes.SLIST};
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		if (ArrayUtils.contains(new int[] {TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_FOR}, ast.getParent().getType())) {
			this.count += ast.getChildCount();
		}
	}
	
	public int getLoopingStatements() {
		return count;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Looping statements check: %s", this.getLoopingStatements()));
		this.count = 0;
	}
}
