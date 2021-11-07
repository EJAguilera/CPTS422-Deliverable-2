package Tokens;

import com.puppycrawl.tools.checkstyle.api.*;

public class Operators {
	public static int[] operators() {
		return new int[] {
				TokenTypes.ASSIGN,		TokenTypes.BAND,		TokenTypes.BAND_ASSIGN,
				TokenTypes.BNOT,		TokenTypes.BOR,			TokenTypes.BOR_ASSIGN,
				TokenTypes.BSR,			TokenTypes.BSR_ASSIGN,	TokenTypes.BXOR,
				TokenTypes.BXOR_ASSIGN,	TokenTypes.COLON,		TokenTypes.COMMA,
				TokenTypes.DEC,			TokenTypes.DIV,			TokenTypes.DIV_ASSIGN,
				TokenTypes.DOT,			TokenTypes.EQUAL,		TokenTypes.GE,
				TokenTypes.GT,			TokenTypes.INC,			TokenTypes.INDEX_OP,
				TokenTypes.LAND,		TokenTypes.LE,			TokenTypes.LITERAL_INSTANCEOF,
				TokenTypes.LNOT,		TokenTypes.LOR,			TokenTypes.LT,
				TokenTypes.MINUS,		TokenTypes.MINUS_ASSIGN,TokenTypes.MOD,
				TokenTypes.MOD_ASSIGN,	TokenTypes.NOT_EQUAL,	TokenTypes.PLUS,
				TokenTypes.PLUS_ASSIGN,	TokenTypes.POST_DEC,	TokenTypes.POST_INC,
				TokenTypes.QUESTION,	TokenTypes.SL,			TokenTypes.SR,
				TokenTypes.SR_ASSIGN,	TokenTypes.STAR,		TokenTypes.STAR_ASSIGN,
				TokenTypes.UNARY_MINUS,	TokenTypes.UNARY_PLUS
		};
	}
}