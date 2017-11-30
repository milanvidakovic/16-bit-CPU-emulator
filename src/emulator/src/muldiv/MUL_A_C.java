package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class MUL_A_C extends Instruction {
	public MUL_A_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("mul a, c");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val * ctx.c.val;
		ctx.a.val = (short)(res & 0xffff);
		ctx.h.val = (short)((res & 0xffff0000) >> 16);
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
