package emulator.src.cmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CMP_A_MB extends Instruction {
	public CMP_A_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("cmp a, [b]");
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.a.val == ctx.memory[fix(ctx.b.val)]) {
			ctx.z.val = 1;
		} else {
			ctx.z.val = 0;
		}
		if (ctx.a.val >= ctx.memory[fix(ctx.b.val)]) {
			ctx.p.val = 1;
		} else {
			ctx.p.val = 0;
		}
		ctx.o.val = 0;
		ctx.pc.val++;
	}
}
