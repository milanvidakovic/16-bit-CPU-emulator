package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class MUL_A_XX extends Instruction {
	public MUL_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("mul a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val * this.argument; 
		ctx.a.val = (short)(res & 0xffff);
		ctx.h.val = (short)((res & 0xffff0000) >> 16);
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val += 2;
	}
}
