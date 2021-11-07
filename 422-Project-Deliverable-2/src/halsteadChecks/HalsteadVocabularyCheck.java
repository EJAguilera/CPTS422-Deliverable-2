package halsteadChecks;

import com.puppycrawl.tools.checkstyle.api.*;

import Tokens.Operands;
import Tokens.Operators;

import java.util.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	
	// Contains mappings of unique operands and operators 
	private Hashtable<Integer, Integer> uniqueOperatorsOperands = new Hashtable<Integer, Integer>();
	private int sumUniqueOperatorOperands;

	// Stubbed with required token set
	@Override
	public int[] getDefaultTokens() {
		return this.getAcceptableTokens();
	}
	
	public int getSum() {
		return this.sumUniqueOperatorOperands;
	}

	// Stubbed with required token set
	@Override
	public int[] getAcceptableTokens() {
		return this.getRequiredTokens();
	}

	// Required Token Set
	@Override
	public int[] getRequiredTokens() {
		int[] combination = new int[Operators.operators().length + Operands.operands().length];
		System.arraycopy(Operators.operators(), 0, combination, 0, Operators.operators().length);
		System.arraycopy(Operands.operands(), 0, combination, Operators.operators().length, Operands.operands().length);
		
		return combination;
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		if (!this.uniqueOperatorsOperands.containsKey(ast.getType())) {
			// This is the first instance of a unique operator or operand
			this.uniqueOperatorsOperands.put(ast.getType(), 1);
		} else {
			// This is a repeated instance of a unique operator or operand
			this.uniqueOperatorsOperands.put(ast.getType(), this.uniqueOperatorsOperands.get(ast.getType() + 1));
		}
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		sumUniqueOperatorOperands = 0;
		for (Iterator<Integer> iterator = this.uniqueOperatorsOperands.keySet().iterator(); iterator.hasNext();) {
			sumUniqueOperatorOperands++;
		}
		
		log(ast, String.format("EJA ACHECK: Halstead Vocabulary: %s", sumUniqueOperatorOperands));
		this.uniqueOperatorsOperands.clear();
	}
	
}
