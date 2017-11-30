package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_A_XX extends Instruction {
	public LD_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("ld a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		ctx.a.val = this.argument;
		ctx.pc.val += 2;
	}
}
