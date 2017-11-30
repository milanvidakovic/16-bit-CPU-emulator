package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class AND_A_XX extends Instruction {
	public AND_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("and a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val & this.argument;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val +=2;
	}
}
