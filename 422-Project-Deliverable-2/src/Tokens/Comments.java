package Tokens;

import com.puppycrawl.tools.checkstyle.api.*;

public class Comments {
	public static int[] singleComments() {
		return new int[] {
				TokenTypes.SINGLE_LINE_COMMENT
		};
	}
	
	public static int[] multiComments() {
		return new int[] {
			TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END	
		};
	}
}
