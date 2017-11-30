package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class OR_A_B extends Instruction {
	public OR_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("or a, b");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val | ctx.b.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
