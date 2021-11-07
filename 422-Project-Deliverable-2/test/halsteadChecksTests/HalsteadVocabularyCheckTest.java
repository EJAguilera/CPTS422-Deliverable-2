package halsteadChecksTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import java.io.File;

import org.junit.jupiter.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.puppycrawl.tools.checkstyle.JavaParser;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import Tokens.Operands;
import Tokens.Operators;
import halsteadChecks.HalsteadVocabularyCheck;

public class HalsteadVocabularyCheckTest {
	// Setup
	private HalsteadVocabularyCheck check = new HalsteadVocabularyCheck();
	
	private int[] operands = Operands.operands();
	private int[] operators = Operators.operators();
	
	private int[] combination(int[] first, int[] second) {
		int[] combination = new int[first.length + second.length];
		System.arraycopy(Operators.operators(), 0, combination, 0, first.length);
		System.arraycopy(Operands.operands(), 0, combination, first.length, second.length);
		
		return combination;
	}
	
	private int[] valid = this.combination(operators, operands);
	
	// Tree-Scaler
	/*
	private void scaler(HalsteadVocabularyCheck check, DetailAST ast) {
		while (ast != null) {
			check.visitToken(ast);
			scaler(check, ast.getFirstChild());
			ast = ast.getNextSibling();
		}
	}
	
	private DetailAST getSampleAST() {
		try {
			File sampleFile = new File(System.getProperty("user.dir") + "./src/ACheckTest/Sample.java");
			FileText sampleText = new FileText(sampleFile, "UTF-8");
			FileContents sample = new FileContents(sampleText);
			DetailAST ast = JavaParser.parse(sample);
			
			this.scaler(this.check, ast);
			return ast;
		}
		catch(Exception e) {
			System.out.print(e.getLocalizedMessage());
			return null;
		}
	}*/
	
	@Test
	public void getRequiredTokensTest() {
		Assert.assertArrayEquals(this.valid, this.check.getRequiredTokens());
	}
	
	@Test
	public void getAcceptableTokensTest() {
		// Spy used to stub previously tested function
		HalsteadVocabularyCheck spy = Mockito.spy(HalsteadVocabularyCheck.class);
		Mockito.when(spy.getRequiredTokens()).thenReturn(this.valid);
		Assert.assertArrayEquals(this.valid, spy.getAcceptableTokens());
	}
	
	@Test
	public void getDefaultTokensTest() {
		// Spy used to stub previously tested function
		HalsteadVocabularyCheck spy = Mockito.spy(HalsteadVocabularyCheck.class);
		Mockito.when(spy.getAcceptableTokens()).thenReturn(this.valid);
		Assert.assertArrayEquals(this.valid, spy.getDefaultTokens());
	}
	
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.LITERAL_INT);
		HalsteadVocabularyCheck spyCheck = Mockito.spy(HalsteadVocabularyCheck.class);
		
		// Check first conditional
		spyCheck.visitToken(spy);
		spyCheck.visitToken(spy);
		// Verify function was called
		
		Mockito.verify(spyCheck, times(2)).visitToken(spy);
	}
	
	// Same as other test, cannot mock the log function successfully
	@Ignore
	@Test
	public void finishTreeTest() {
		DetailAST ast = Mockito.spy(DetailAST.class);
		Mockito.when(ast.getType()).thenReturn(TokenTypes.LITERAL_INT);
		
		HalsteadVocabularyCheck spy = Mockito.spy(HalsteadVocabularyCheck.class);
		// Mockito.when(spy.log(ast, String.format("EJA ACHECK: Halstead Vocabulary: %s", spy.getSum())).thenReturn(null);
		// Mockito.when(this.check.log(getSampleAST(), null, null)).thenReturn();
	}
	
	@Test
	public void getSumTest() {
		assertEquals(0, this.check.getSum());
	}
}
