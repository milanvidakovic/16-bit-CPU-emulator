package emulator.src.jmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class JNP_XX extends Instruction {
	public JNP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("jnp 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.p.val == 0) {
			ctx.pc.val = this.argument;
		} else {
			ctx.pc.val +=2;
		}
	}
}
