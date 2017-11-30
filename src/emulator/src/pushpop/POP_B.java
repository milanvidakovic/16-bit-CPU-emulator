package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class POP_B extends Instruction {
	public POP_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("pop b");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.sp.val--;
		int v = fix(ctx.sp.val);
		ctx.b.val = ctx.memory[v];
		ctx.pc.val++;
	}
}
