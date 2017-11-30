package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_A_MC extends Instruction {
	public LD_A_MC(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld a, [c]");
	}

	@Override
	public void exec(Context ctx) {
		ctx.a.val = ctx.memory[fix(ctx.c.val)];
		ctx.pc.val++;
	}
}
