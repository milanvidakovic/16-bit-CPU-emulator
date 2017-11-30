package emulator.src.store;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ST_A_MB extends Instruction {
	public ST_A_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("st a, [b]");
	}

	@Override
	public void exec(Context ctx) {
		ctx.memory[fix(ctx.b.val)] = ctx.a.val;
		ctx.pc.val++;
		updateViewer(ctx, fix(ctx.b.val), ctx.a.val);
	}
}
