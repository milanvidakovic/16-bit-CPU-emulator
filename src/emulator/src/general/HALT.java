package emulator.src.general;

import emulator.engine.Context;
import emulator.src.Instruction;

public class HALT extends Instruction {
	public HALT(short[]memory, int addr) {
		super(memory, addr);
		this.assembler = "halt";
		super.setContent();
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.engine.stop();
		ctx.pc.val++;
	}
}
