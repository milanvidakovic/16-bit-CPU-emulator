package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_B_SP extends Instruction {
	public LD_B_SP(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ld b, sp");
	}

	@Override
	public void exec(Context ctx) {
		ctx.b.val = ctx.sp.val;
		ctx.pc.val++;
	}
}
