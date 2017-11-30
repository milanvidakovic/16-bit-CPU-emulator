package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class XOR_A_C extends Instruction {
	public XOR_A_C(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("xor a, c");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val ^ ctx.c.val;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
