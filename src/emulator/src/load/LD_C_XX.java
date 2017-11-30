package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_C_XX extends Instruction {
	public LD_C_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("ld c, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		ctx.c.val = this.argument;
		ctx.pc.val += 2;
	}
}
