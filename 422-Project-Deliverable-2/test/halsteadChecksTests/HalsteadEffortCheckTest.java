package halsteadChecksTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import halsteadChecks.*;

public class HalsteadEffortCheckTest {
	HalsteadEffortCheck check = new HalsteadEffortCheck();
	
	private int[] valid = {
		// Operators
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
		TokenTypes.UNARY_MINUS,	TokenTypes.UNARY_PLUS,
		// Operands
		TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
		TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
		TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
		TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
		TokenTypes.NUM_LONG,
	};
	
	@Test
	public void getDefaultTokensTest() {
		Assert.assertArrayEquals(this.valid, this.check.getDefaultTokens());
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.LITERAL_INT);
		HalsteadEffortCheck spyCheck = Mockito.spy(HalsteadEffortCheck.class);
		spyCheck.beginTree(spy);
		Mockito.verify(spyCheck, times(1)).beginTree(spy);
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.LITERAL_INT);
		HalsteadEffortCheck spyCheck = Mockito.spy(HalsteadEffortCheck.class);
		spyCheck.visitToken(spy);
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	@Test
	public void getHalsteadEffortTest() {
		assertEquals(0, this.check.getHalsteadEffort());
	}
	
	// Finish tree skipped
}
