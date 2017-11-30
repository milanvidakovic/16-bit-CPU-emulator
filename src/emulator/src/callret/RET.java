package emulator.src.callret;

import emulator.engine.Context;
import emulator.src.Instruction;

public class RET extends Instruction {
	public RET(short[] memory, int addr) {
		super(memory, addr);
		super.setAssembler("ret");
	}

	@Override
	public void exec(Context ctx) {
		ctx.sp.val--;
		ctx.pc.val = ctx.memory[fix(ctx.sp.val)];
	}
}
