package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class OR_A_XX extends Instruction {
	public OR_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("or a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val | this.argument;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val +=2;
	}
}
