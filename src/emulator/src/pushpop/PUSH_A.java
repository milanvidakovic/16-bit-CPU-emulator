package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class PUSH_A extends Instruction {
	public PUSH_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("push a");
	}
	
	@Override
	public void exec(Context ctx) {
		int v = fix(ctx.sp.val);
		ctx.memory[v] = ctx.a.val;
		ctx.sp.val++;
		ctx.pc.val++;
		updateViewer(ctx, v, ctx.a.val);
	}
}
