package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class PUSH_C extends Instruction {
	public PUSH_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("push c");
	}
	
	@Override
	public void exec(Context ctx) {
		int v = fix(ctx.sp.val);
		ctx.memory[v] = ctx.c.val;
		ctx.sp.val++;
		ctx.pc.val++;
		updateViewer(ctx, v, ctx.c.val);
	}
}
