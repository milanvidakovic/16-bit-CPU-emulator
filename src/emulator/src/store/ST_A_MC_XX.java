package emulator.src.store;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ST_A_MC_XX extends Instruction {
	public ST_A_MC_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("st a, [c + 0x%04x]");
	}
	
	@Override
	public void exec(Context ctx) {
		int arg = this.argument;
		if (fix(ctx.c.val) + arg < 0)
			arg = fix(argument);
		ctx.memory[fix(ctx.c.val) + arg] = ctx.a.val;
		ctx.pc.val += 2;
		updateViewer(ctx, fix(ctx.c.val) + arg, ctx.a.val);
	}
}
