package halsteadChecks;

import com.puppycrawl.tools.checkstyle.api.*;

import java.util.*;

public class HalsteadVolumeCheck extends AbstractCheck {
	
	// Contains mappings of unique operands and operators 
	private Hashtable<Integer, Integer> operatorsOperands = new Hashtable<Integer, Integer>();
	private int sum;

	// Stubbed with required token set
	@Override
	public int[] getDefaultTokens() {
		return this.getAcceptableTokens();
	}

	// Stubbed with required token set
	@Override
	public int[] getAcceptableTokens() {
		return this.getRequiredTokens();
	}

	// Required Token Set
	@Override
	public int[] getRequiredTokens() {
		return new int[] {
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
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		if (!this.operatorsOperands.containsKey(ast.getType())) {
			// This is the first instance of a unique operator or operand
			this.operatorsOperands.put(ast.getType(), 1);
		} else {
			// This is a repeated instance of a unique operator or operand
			this.operatorsOperands.put(ast.getType(), this.operatorsOperands.get(ast.getType() + 1));
		}
	}
	
	public double getHalsteadVolume() {
		int uniqueOperatorsOperands = 0;
		for (int item : this.operatorsOperands.values()) {
			this.sum += item;
		}
		
		for(int item: this.operatorsOperands.keySet()) {
			uniqueOperatorsOperands += 1;
		}
		
		double volume = this.sum * (Math.log(uniqueOperatorsOperands) / Math.log(2));
		return volume;
	}
	
	// Iterates of number of operands and operators as defined in 'https://www.ibm.com/docs/en/raa/6.1?topic=metrics-halstead-effort'
	@Override
	public void finishTree(DetailAST ast) {
		double volume = this.getHalsteadVolume();
		log(ast, String.format("EJA ACHECK: Halstead Volume: %s", volume));
		this.operatorsOperands.clear();
		this.sum = 0;
	}
}
