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

public class HalsteadLengthCheckTest {
	
	// Setup
	private HalsteadLengthCheck check = new HalsteadLengthCheck();
	
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
	private void scaler(HalsteadLengthCheck check, DetailAST ast) {
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
	}
	
	@Test
	public void getHalsteadLengthTest() {
		assertEquals(0, this.check.getHalsteadLength());
	}
	
	
	@Test
	public void getRequiredTokensTest() {
		Assert.assertArrayEquals(this.valid, this.check.getRequiredTokens());
	}
	
	@Test
	public void getAcceptableTokensTest() {
		// Spy used to stub previously tested function
		HalsteadLengthCheck spy = Mockito.spy(HalsteadLengthCheck.class);
		Mockito.when(spy.getRequiredTokens()).thenReturn(this.valid);
		Assert.assertArrayEquals(this.valid, spy.getAcceptableTokens());
	}
	
	@Test
	public void getDefaultTokensTest() {
		// Spy used to stub previously tested function
		HalsteadLengthCheck spy = Mockito.spy(HalsteadLengthCheck.class);
		Mockito.when(spy.getAcceptableTokens()).thenReturn(this.valid);
		Assert.assertArrayEquals(this.valid, spy.getDefaultTokens());
	}
	
	// Visit token not working due to nature of DetailAST
	@Ignore
	@Test
	public void visitTokenTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		Mockito.when(spy.getType()).thenReturn(TokenTypes.ASSIGN);
		
		HalsteadLengthCheck spyCheck = Mockito.spy(HalsteadLengthCheck.class);
		spyCheck.visitToken(spy);
		
		Mockito.verify(spyCheck, times(1)).visitToken(spy);
	}
	
	// Finish tree log function cannot be mocked and verified for unknown reason
	@Ignore
	@Test
	public void finishTreeTest() {
		DetailAST spy = Mockito.spy(DetailAST.class);
		HalsteadLengthCheck spyCheck = Mockito.spy(HalsteadLengthCheck.class);
		// spyCheck.finishTree(spy);
		// Mockito.verify(spyCheck, times(1)).finishTree(spy);
	}
}
