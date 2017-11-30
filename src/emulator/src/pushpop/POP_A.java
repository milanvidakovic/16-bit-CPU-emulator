package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class POP_A extends Instruction {
	public POP_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("pop a");
	}

	@Override
	public void exec(Context ctx) {
		ctx.sp.val--;
		int v = fix(ctx.sp.val);
		ctx.a.val = ctx.memory[v];
		ctx.pc.val++;
	}
}
