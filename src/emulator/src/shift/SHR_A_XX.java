package emulator.src.shift;

import emulator.engine.Context;
import emulator.src.Instruction;

public class SHR_A_XX extends Instruction {
	public SHR_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("shr a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val >> this.argument;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val += 2;
	}
}
