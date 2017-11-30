package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class PUSH_B extends Instruction {
	public PUSH_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("push b");
	}

	@Override
	public void exec(Context ctx) {
		int v = fix(ctx.sp.val);
		ctx.memory[v] = ctx.b.val;
		ctx.sp.val++;
		ctx.pc.val++;
		updateViewer(ctx, v, ctx.b.val);
	}
}
