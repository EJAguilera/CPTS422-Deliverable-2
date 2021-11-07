package extrasTest;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Extras.*;

public class VariablesDeclarationsCheckTest {
	VariableDeclarationsCheck check = new VariableDeclarationsCheck();
	
	@Test
	public void getDefaultTokensTest() {
		Assertions.assertArrayEquals(new int[] {TokenTypes.VARIABLE_DEF}, this.check.getDefaultTokens());
	}
	
	@Test
	public void getDeclarationsTest() {
		Assertions.assertEquals(0, this.check.getDeclarations());
	}
	
	@Test
	public void visitTokensTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
		
		VariableDeclarationsCheck spyCheck = Mockito.spy(VariableDeclarationsCheck.class);
		spyCheck.visitToken(spy);
		
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	// Finish Tree skipped
}
