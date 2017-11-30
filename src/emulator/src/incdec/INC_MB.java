package emulator.src.incdec;

import emulator.engine.Context;
import emulator.src.Instruction;

public class INC_MB extends Instruction {
	public INC_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("inc [b]");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.memory[fix(ctx.b.val)] + 1;
		ctx.memory[fix(ctx.b.val)] = (short)res;
		markFlags(res, (short)res, ctx);
		ctx.pc.val++;
		updateViewer(ctx, fix(ctx.b.val), (short)res);
	}
}
