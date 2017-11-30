package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_SP_B extends Instruction {
	public LD_SP_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld sp, b");
	}

	@Override
	public void exec(Context ctx) {
		ctx.sp.val = ctx.b.val;
		ctx.pc.val++;
	}
}
