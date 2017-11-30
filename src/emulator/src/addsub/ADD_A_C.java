package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ADD_A_C extends Instruction {
	public ADD_A_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("add a, c");
	}

	@Override
	public void exec(Context ctx) {
		short old_a = ctx.a.val;
		int res = ctx.a.val + ctx.c.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		markOverflow(old_a, ctx.c.val, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
