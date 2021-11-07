package BCheck;

import com.puppycrawl.tools.checkstyle.api.*;

//A limited implementation of the number of comments check.
public class BCheck extends AbstractCheck {
	
	// Number of comments
	private int numComments = 0;
	
	// Error-fix for unexpected override
	@Override
	public boolean isCommentNodesRequired() {
		return true;
	}

	@Override
	public int[] getDefaultTokens() {

		// Gets all tokens considered 'comments' by Checkstyle.
		return new int[] {
			TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_END
		};
	}
	
	@Override
	public int[] getAcceptableTokens() {

		// Gets all tokens considered 'comments' by Checkstyle.
		return new int[] {
			TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_END
		};
	}
	
	@Override
	public int[] getRequiredTokens() {
		// Stubbed for compliance
		return new int[0];
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		// Increment number of comments
		numComments += 1;
	}
	
	// Automatically log 'violation' for display purposes 
	@Override
	public void finishTree(DetailAST root) {
		// Output incremented and logged to console
		log(root, String.format("EJA BCHECK: Number of comments: %s", numComments));
	}
}
