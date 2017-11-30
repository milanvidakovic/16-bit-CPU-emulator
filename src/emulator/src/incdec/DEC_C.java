package emulator.src.incdec;

import emulator.engine.Context;
import emulator.src.Instruction;

public class DEC_C extends Instruction {
	public DEC_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("dec c");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.c.val - 1;
		ctx.c.val = (short)res;
		markFlags(res, ctx.c.val, ctx);
		ctx.pc.val++;
	}
}
