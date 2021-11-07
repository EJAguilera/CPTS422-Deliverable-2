package halsteadChecks;

import halsteadChecks.HalsteadVolumeCheck;

import com.puppycrawl.tools.checkstyle.api.*;

import halsteadChecks.HalsteadDifficultyCheck;
import halsteadChecks.HalsteadVolumeCheck;

public class HalsteadEffortCheck extends AbstractCheck {
	
	private HalsteadVolumeCheck volumeCheck = new HalsteadVolumeCheck();
	private HalsteadDifficultyCheck difficultyCheck = new HalsteadDifficultyCheck();

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
		return difficultyCheck.getAcceptableTokens();
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		this.volumeCheck.beginTree(ast);
		this.difficultyCheck.beginTree(ast);
	}
	
	@Override
	public void visitToken(DetailAST ast) {
		this.volumeCheck.visitToken(ast);
		this.difficultyCheck.visitToken(ast);
	}
	
	// Compute results
	public double getHalsteadEffort() {
		return  Math.round(this.difficultyCheck.getHalsteadDifficulty())*Math.round(this.volumeCheck.getHalsteadVolume());
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		this.volumeCheck.finishTree(ast);
		this.volumeCheck.finishTree(ast);
		log(ast, String.format("EJA ACHECK: Halstead Effort: %s", this.getHalsteadEffort()));
	}
}
