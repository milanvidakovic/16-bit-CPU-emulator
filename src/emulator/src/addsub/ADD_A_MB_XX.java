package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ADD_A_MB_XX extends Instruction {
	public ADD_A_MB_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("add a, [b + 0x%04x]");
	}
	
	@Override
	public void exec(Context ctx) {
		short old_a = ctx.a.val;
		int arg = this.argument;
		if (fix(ctx.b.val) + this.argument < 0) {
			arg = fix(this.argument);
		}
		int res = ctx.a.val + ctx.memory[fix(ctx.b.val) + arg];
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		markOverflow(old_a, ctx.memory[fix(ctx.b.val) + arg], ctx.a.val, ctx);
		ctx.pc.val +=2;
	}
}
