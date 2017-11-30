package emulator.src.jmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class JMP_XX extends Instruction {
	public JMP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("jmp 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		ctx.pc.val = this.argument;
	}
}
