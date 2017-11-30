package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class SUB_A_B extends Instruction {
	public SUB_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("sub a, b");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val - ctx.b.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
