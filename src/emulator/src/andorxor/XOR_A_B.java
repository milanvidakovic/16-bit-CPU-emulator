package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class XOR_A_B extends Instruction {
	public XOR_A_B(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("xor a, b");
	}

	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val ^ ctx.b.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
