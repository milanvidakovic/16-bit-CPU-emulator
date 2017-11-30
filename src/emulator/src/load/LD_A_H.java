package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_A_H extends Instruction {
	public LD_A_H(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld a, h");
	}

	@Override
	public void exec(Context ctx) {
		ctx.a.val = ctx.h.val;
		ctx.pc.val++;
	}
}
