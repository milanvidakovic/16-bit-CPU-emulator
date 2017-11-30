package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_A_MB extends Instruction {
	public LD_A_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld a, [b]");
	}

	@Override
	public void exec(Context ctx) {
		ctx.a.val = ctx.memory[fix(ctx.b.val)];
		ctx.pc.val++;
	}
}
