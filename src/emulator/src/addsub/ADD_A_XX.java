package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ADD_A_XX extends Instruction {
	public ADD_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("add a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		short old_a = ctx.a.val;
		int res = ctx.a.val + this.argument;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		markOverflow(old_a, this.argument, ctx.a.val, ctx);
		ctx.pc.val +=2;
	}
}
