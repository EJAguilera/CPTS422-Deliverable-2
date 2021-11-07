package bChecksTest;

import static org.mockito.Mockito.times;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import BCheck.ExpressionCheck;
import BCheck.GetOperandsCheck;

public class GetOperandsCheckTest {
	
	GetOperandsCheck check = new GetOperandsCheck();
	
	@Test
	public void getDefaultTokensTest() {
		Assert.assertArrayEquals(
			new int[] {
				TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
				TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
				TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
				TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
				TokenTypes.NUM_LONG
			},
			this.check.getDefaultTokens()
		);
	}
	
	@Test
	public void getOperandsTest() {
		Assert.assertEquals(0, this.check.getOperands());
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.CHAR_LITERAL);
		GetOperandsCheck spyCheck = Mockito.spy(GetOperandsCheck.class);
		spyCheck.visitToken(spy);
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	@Test
	public void beginTreeTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.CHAR_LITERAL);
		GetOperandsCheck spyCheck = Mockito.spy(GetOperandsCheck.class);
		spyCheck.beginTree(spy);
		Mockito.verify(spyCheck, times(1)).beginTree(spy);
	}
	
	// Finish Tree skip
}
