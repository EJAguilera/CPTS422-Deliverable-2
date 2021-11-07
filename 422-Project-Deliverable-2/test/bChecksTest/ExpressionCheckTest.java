package bChecksTest;

import org.junit.jupiter.api.Assertions;
import static org.mockito.Mockito.times;

import org.junit.*;
import org.mockito.Mockito;
import BCheck.*;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes; 

public class ExpressionCheckTest {
	
	ExpressionCheck check = new ExpressionCheck();
	
	@Test
	public void getDefaultTokensTest() {
		Assertions.assertArrayEquals(new int[] {TokenTypes.EXPR}, this.check.getDefaultTokens());
	}
	
	@Test
	public void getExpressionsTest() {
		Assertions.assertEquals(0, this.check.getExpressions());
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.EXPR);
		ExpressionCheck spyCheck = Mockito.spy(ExpressionCheck.class);
		spyCheck.visitToken(spy);
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	// Finish Tree Skipped
}
