package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class MUL_A_MB_XX extends Instruction {
	public MUL_A_MB_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("mul a, [b + 0x%04x]");
	}

	@Override
	public void exec(Context ctx) {
		int arg = this.argument;
		if (fix(ctx.b.val) + this.argument < 0) {
			arg = fix(this.argument);
		}
		int res = ctx.a.val * ctx.memory[fix(ctx.b.val) + arg]; 
		ctx.a.val = (short)(res & 0xffff);
		ctx.h.val = (short)((res & 0xffff0000) >> 16);
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val += 2;
	}
}
