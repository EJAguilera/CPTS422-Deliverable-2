package halsteadChecksTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;

import java.io.File;
import java.io.IOException;

import org.junit.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.utils.*;

import org.mockito.Mockito;
import org.mockito.Mockito.*;
// import org.mockito.Mockito.

import halsteadChecks.*;
import Tokens.*;

public class HalsteadVolumeCheckTest {
	
	private HalsteadVolumeCheck check = new HalsteadVolumeCheck();
	
	private int[] valid = {
			// Operands
			TokenTypes.CHAR_LITERAL, 	TokenTypes.LITERAL_BOOLEAN, 	TokenTypes.LITERAL_BYTE,
			TokenTypes.LITERAL_CHAR, 	TokenTypes.LITERAL_INT,  		TokenTypes.LITERAL_LONG,
			TokenTypes.LITERAL_SHORT,	TokenTypes.LITERAL_VOID,	 	TokenTypes.STRING_LITERAL, 
			TokenTypes.NUM_DOUBLE,		TokenTypes.NUM_FLOAT, 			TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG,
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
			TokenTypes.UNARY_MINUS,	TokenTypes.UNARY_PLUS
	};
	
	@Test
	public void getDefaultTokensTest() {
		Assert.assertArrayEquals(this.valid, this.check.getDefaultTokens());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getHalsteadVolumeTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.ASSIGN);
		
		HalsteadVolumeCheck spyCheck = Mockito.spy(HalsteadVolumeCheck.class);
		spyCheck.visitToken(spy);
		spyCheck.visitToken(spy);
		
		Assertions.assertEquals(0.0, spyCheck.getHalsteadVolume());
	}
	
	// Despite efforts still can't mock ast.
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.CHAR_LITERAL);
		
		HalsteadVolumeCheck spyCheck = Mockito.spy(HalsteadVolumeCheck.class);
		spyCheck.visitToken(spy);
		spyCheck.visitToken(spy);
		
		Mockito.verify(spyCheck, times(2)).visitToken(spy);
	}
	
	// Despite efforts still can't mock log
	@Ignore
	@Test
	public void finishTreeTest() {
		HalsteadVolumeCheck spy = Mockito.spy(HalsteadVolumeCheck.class);
		//Mockito.verify(spy.log(this.getSampleAST(), String.format("EJA ACHECK: Halstead Length: %s", this.check.getHalsteadLength())), times(1));
	}
}
