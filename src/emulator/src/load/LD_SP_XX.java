package emulator.src.load;

import emulator.engine.Context;
import emulator.src.Instruction;

public class LD_SP_XX extends Instruction {
	public LD_SP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("ld sp, 0x%04x");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.sp.val = this.argument;
		ctx.pc.val += 2;
	}
}
