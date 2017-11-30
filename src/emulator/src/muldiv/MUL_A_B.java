package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class MUL_A_B extends Instruction {
	public MUL_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("mul a, b");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val * ctx.b.val;
		ctx.a.val = (short)(res & 0xffff);
		ctx.h.val = (short)((res & 0xffff0000) >> 16);
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
