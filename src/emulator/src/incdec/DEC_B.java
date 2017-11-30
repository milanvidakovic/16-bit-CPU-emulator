package emulator.src.incdec;

import emulator.engine.Context;
import emulator.src.Instruction;

public class DEC_B extends Instruction {
	public DEC_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("dec b");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.b.val - 1;
		ctx.b.val = (short)res;
		markFlags(res, ctx.b.val, ctx);
		ctx.pc.val++;
	}
}
