package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class DIV_A_B extends Instruction {
	public DIV_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("div a, b");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val / ctx.b.val;
		int rem = ctx.a.val % ctx.b.val;
		ctx.a.val = (short)res;
		ctx.h.val = (short)rem;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
