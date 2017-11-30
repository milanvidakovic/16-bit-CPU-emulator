package emulator.src.store;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ST_A_MC extends Instruction {
	public ST_A_MC(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("st a, [c]");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.memory[fix(ctx.c.val)] = ctx.a.val;
		ctx.pc.val++;
		updateViewer(ctx, fix(ctx.c.val), ctx.a.val);
	}
}
