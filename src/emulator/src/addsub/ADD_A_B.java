package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ADD_A_B extends Instruction {
	public ADD_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("add a, b");
	}

	@Override
	public void exec(Context ctx) {
		short old_a = ctx.a.val;
		int res = ctx.a.val + ctx.b.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		markOverflow(old_a, ctx.b.val, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
