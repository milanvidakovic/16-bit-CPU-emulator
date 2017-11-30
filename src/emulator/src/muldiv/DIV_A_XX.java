package emulator.src.muldiv;

import emulator.engine.Context;
import emulator.src.Instruction;

public class DIV_A_XX extends Instruction {
	public DIV_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("div a, 0x%04x");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val / this.argument;
		int rem = ctx.a.val % this.argument;
		ctx.a.val = (short)res;
		ctx.h.val = (short)rem;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val += 2;
	}
}
