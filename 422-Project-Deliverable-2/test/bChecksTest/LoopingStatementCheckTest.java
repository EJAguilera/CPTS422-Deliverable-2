package bChecksTest;
import static org.mockito.Mockito.times;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import BCheck.*;
import halsteadChecks.HalsteadEffortCheck;

public class LoopingStatementCheckTest {
	
	private LoopingStatementsCheck check = new LoopingStatementsCheck();
	
	@Test
	public void getDefaultTokensTest() {
		Assert.assertArrayEquals(new int[] {TokenTypes.SLIST}, this.check.getDefaultTokens());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getLoopingStatementsTest() {
		Assert.assertEquals(0, (double)this.check.getLoopingStatements());
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.SLIST);
		LoopingStatementsCheck spyCheck = Mockito.spy(LoopingStatementsCheck.class);
		spyCheck.visitToken(spy);
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	// Finish tree skipped
}
