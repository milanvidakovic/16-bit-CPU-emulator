package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class SUB_SP_XX extends Instruction {
	public SUB_SP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("sub sp, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.sp.val - this.argument;
		ctx.sp.val = (short)res;
		markFlags(res, ctx.sp.val, ctx);
		ctx.pc.val +=2;
	}
}
