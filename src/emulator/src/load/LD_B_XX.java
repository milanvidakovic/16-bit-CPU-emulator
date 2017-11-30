package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_B_XX extends Instruction {
	public LD_B_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("ld b, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		ctx.b.val = this.argument;
		ctx.pc.val += 2;
	}
}
