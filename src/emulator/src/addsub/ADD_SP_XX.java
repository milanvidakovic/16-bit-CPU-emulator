package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ADD_SP_XX extends Instruction {
	public ADD_SP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("add sp, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		short old_sp = ctx.sp.val;
		int res = ctx.sp.val + this.argument;
		ctx.sp.val = (short)res;
		markFlags(res, ctx.sp.val, ctx);
		markOverflow(old_sp, this.argument, ctx.sp.val, ctx);
		ctx.pc.val +=2;
	}
}
