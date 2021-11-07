package bChecksTest;

import Tokens.Operators;

import static org.mockito.Mockito.times;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.api.*;
import BCheck.*;

public class GetOperatorsCheckTest {
	GetOperatorsCheck check = new GetOperatorsCheck();
	
	@Test
	public void getDefaultTokensTest() {
		Assert.assertArrayEquals(
			new int[] {
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
			},
			this.check.getDefaultTokens()
		);
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.ASSIGN);
		GetOperatorsCheck spyCheck = Mockito.spy(GetOperatorsCheck.class);
		spyCheck.beginTree(spy);
		Mockito.verify(spyCheck, times(1)).beginTree(spy);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.ASSIGN);
		GetOperatorsCheck spyCheck = Mockito.spy(GetOperatorsCheck.class);
		spyCheck.visitToken(spy);
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	@Test
	public void getOperatorsTest() {
		Assert.assertEquals(1, this.check.getOperators());
	}
	
	// Finish Tree skipped
}
