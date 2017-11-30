package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class XOR_A_MB extends Instruction {
	public XOR_A_MB(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("xor a, [b]");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val ^ ctx.memory[fix(ctx.b.val)];
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
