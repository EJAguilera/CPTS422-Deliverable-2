package halsteadChecks;

import java.util.*;
import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;
import org.apache.commons.*;
// import com.sun.tools.javac.util.*;
// import com.sun.tools.javac.util.ArrayUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Ignore;

import Tokens.Operands;
import Tokens.Operators;

public class HalsteadDifficultyCheck extends AbstractCheck {
	
	private Hashtable<Integer,Integer> uniqueOperators = new Hashtable<Integer,Integer>();
	private Hashtable<Integer,Integer> uniqueOperands = new Hashtable<Integer,Integer>();

	@Override
	public int[] getDefaultTokens() {
		return this.getAcceptableTokens();
	}

	@Override
	public int[] getAcceptableTokens() {
		return this.getRequiredTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		int[] combination = new int[Operators.operators().length + Operands.operands().length];
		System.arraycopy(Operators.operators(), 0, combination, 0, Operators.operators().length);
		System.arraycopy(Operands.operands(), 0, combination, Operators.operators().length, Operands.operands().length);
		
		return combination;
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		
		// Handles logging unique operators
		if (ArrayUtils.contains(Operators.operators(), ast.getType())) {
			if (!this.uniqueOperators.containsKey(ast.getType())) {
				this.uniqueOperators.put(ast.getType(), 1);
			} else {
				int previous = this.uniqueOperators.get(ast.getType());
				this.uniqueOperators.put(ast.getType(), previous + 1);
			}
		}
		
		// Handles logging unique operands
		if (ArrayUtils.contains(Operands.operands(), ast.getType())) {
			if (!this.uniqueOperands.containsKey(ast.getType())) {
				this.uniqueOperands.put(ast.getType(), 1);
			} else {
				int previous = this.uniqueOperands.get(ast.getType());
				this.uniqueOperands.put(ast.getType(), previous + 1);
			}
		}
	}
	
	public double getHalsteadDifficulty() {
		int uniqueOperators = 0, uniqueOperands = 0, operands = 0;
		
		Iterator<Integer> operatorsItr = this.uniqueOperators.keySet().iterator();
		Iterator<Integer> operandsItr = this.uniqueOperators.keySet().iterator();
		
		// Sets number of unique operators
		while (operatorsItr.hasNext()) {
			uniqueOperators++;
		}
		
		// Sets number of unique operands
		while (operandsItr.hasNext()) {
			uniqueOperands++;
		}
		
		// Totals number of operands
		for (Integer value : this.uniqueOperands.values()) {
			operands += value;
		}
		
		if (uniqueOperands == 0) {
			uniqueOperands = 1;
		}
		
		double difficulty = ((uniqueOperators / 2) * operands) / uniqueOperands;
		return difficulty;
	}
	
	@Ignore
	@Override
	public void finishTree(DetailAST ast) {
		log(ast, String.format("EJA ACHECK: Halstead Difficulty: %s", this.getHalsteadDifficulty()));
		uniqueOperands.clear();
		uniqueOperators.clear();
	}
}
