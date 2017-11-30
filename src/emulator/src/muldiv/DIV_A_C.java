package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class DIV_A_C extends Instruction {
	public DIV_A_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("div a, c");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val / ctx.c.val;
		int rem = ctx.a.val % ctx.c.val;
		ctx.a.val = (short)res;
		ctx.h.val = (short)rem;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
