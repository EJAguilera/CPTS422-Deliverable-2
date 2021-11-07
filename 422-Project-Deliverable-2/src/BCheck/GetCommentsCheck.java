package BCheck;

import com.puppycrawl.tools.checkstyle.api.*;

public class GetCommentsCheck extends AbstractCheck {
	private static int count = 0, lineCount = 0;
	
	public int getCommentsCount() {
		return count;
	}
	
	public int getCommentLineCount() {
		return lineCount;
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		count++;
		
		if (ast.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
			lineCount++;
		} else if (ast.getType() == TokenTypes.BLOCK_COMMENT_END) {
			int startLine = (ast.getParent() != null) ? ast.getParent().getLineNo() : ast.getLineNo(),
				endLine = ast.getLineNo(),
				difference = startLine - endLine + 1;
			lineCount += difference;
		}
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		count = lineCount = 0;
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
		return new int[] {
			TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_END
		};
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Number of comments: %s", this.getCommentsCount()));
		log(ast, String.format("EJA ACHECK: Number of lines of comments: %s", this.getCommentLineCount()));
	}
}
