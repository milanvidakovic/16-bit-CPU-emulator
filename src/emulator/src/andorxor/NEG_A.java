package emulator.src.andorxor;

import emulator.engine.Context;
import emulator.src.Instruction;

public class NEG_A extends Instruction {
	public NEG_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("neg a");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = (short)(~ctx.a.val);
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
