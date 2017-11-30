package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_C_A extends Instruction {
	public LD_C_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld c, a");
	}

	@Override
	public void exec(Context ctx) {
		ctx.c.val = ctx.a.val;
		ctx.pc.val++;
	}
}
