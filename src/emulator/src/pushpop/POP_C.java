package emulator.src.pushpop;

import emulator.engine.Context;
import emulator.src.Instruction;

public class POP_C extends Instruction {
	public POP_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("pop c");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.sp.val--;
		int v = fix(ctx.sp.val);
		ctx.c.val = ctx.memory[v];
		ctx.pc.val++;
	}
}
