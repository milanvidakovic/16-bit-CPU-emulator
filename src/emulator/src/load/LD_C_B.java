package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_C_B extends Instruction {
	public LD_C_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld c, b");
	}

	@Override
	public void exec(Context ctx) {
		ctx.c.val = ctx.b.val;
		ctx.pc.val++;
	}
}
