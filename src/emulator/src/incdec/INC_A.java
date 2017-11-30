package emulator.src.incdec;

import emulator.engine.Context;
import emulator.src.Instruction;

public class INC_A extends Instruction {
	public INC_A(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("inc a");
	}
	
	@Override
	public void exec(Context ctx) {
		int res = ctx.a.val + 1;
		ctx.a.val = (short)res;
		markFlags(res, ctx.a.val, ctx);
		ctx.pc.val++;
	}
}
