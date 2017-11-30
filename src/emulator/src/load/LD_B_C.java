package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_B_C extends Instruction {
	public LD_B_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld b, c");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.b.val = ctx.c.val;
		ctx.pc.val++;
	}
}
