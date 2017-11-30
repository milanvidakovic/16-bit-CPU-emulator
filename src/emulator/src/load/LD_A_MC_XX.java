package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_A_MC_XX extends Instruction {
	public LD_A_MC_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("ld a, [c + 0x%04x]");
	}

	@Override
	public void exec(Context ctx) {
		ctx.a.val = ctx.memory[fix(ctx.c.val) + this.argument];
		ctx.pc.val += 2;
	}
}
