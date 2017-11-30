package emulator.src.jmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class JZ_XX extends Instruction {
	public JZ_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("jz 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.z.val == 1) {
			ctx.pc.val = this.argument;
		} else {
			ctx.pc.val +=2;
		}
	}
}
