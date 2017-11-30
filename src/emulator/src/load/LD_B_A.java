package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_B_A extends Instruction {
	public LD_B_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld b, a");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.b.val = ctx.a.val;
		ctx.pc.val++;
	}
}
