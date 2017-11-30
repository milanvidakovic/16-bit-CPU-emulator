package emulator.src.addsub;

import emulator.engine.Context;
import emulator.src.Instruction;

public class SUB_A_MB extends Instruction {
	public SUB_A_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("sub a, [b]");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val - ctx.memory[fix(ctx.b.val)];
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
