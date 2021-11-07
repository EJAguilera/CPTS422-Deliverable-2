package ACheckTest;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.utils.*;

import ACheck.ACheck;

public class ACheckTest {
	
	// Test object
	private ACheck aCheckUnit = new ACheck();
	
	// First, test that specified tokens are accepted.
	private int[] acceptableTokens = new int[] {
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
	
	// Conduct test
	@Test
	public void testGetAcceptableTokens() {
		Assert.assertArrayEquals(this.acceptableTokens, this.aCheckUnit.getDefaultTokens());
		Assert.assertArrayEquals(this.acceptableTokens, this.aCheckUnit.getRequiredTokens());	
	}
	
	// Creating source for test
	@Test
	public void visitTokenTest() throws CheckstyleException, IOException {
		ACheck aCheckUnitTokens = new ACheck();
		File sampleFile = new File(System.getProperty("user.dir") + "./src/ACheckTest/Sample.java");
		
		try {
			FileText sampleText = new FileText(sampleFile, "UTF-8");
			FileContents sample = new FileContents(sampleText);
			
			// System.out.print(sample.getText());
			
			DetailAST astUnit = JavaParser.parse(sample);
			
			aCheckUnitTokens.configure(new DefaultConfiguration("Local"));
			aCheckUnitTokens.contextualize(new DefaultContext());
			
			scaler(aCheckUnitTokens, astUnit);
			
			// aCheckUnitTokens.(astUnit);
		}
		catch(Exception e) {
			System.out.print(e.getLocalizedMessage());
		}
	}
	
	// Tree-Scaler
	private void scaler(ACheck check, DetailAST ast) {
		while (ast != null) {
			check.visitToken(ast);
			scaler(check, ast.getFirstChild());
			ast = ast.getNextSibling();
		}
	}
}
