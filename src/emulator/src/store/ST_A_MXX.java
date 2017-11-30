package emulator.src.store;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ST_A_MXX extends Instruction {
	public ST_A_MXX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("st a, [0x%04x]");
	}
	
	@Override
	public void exec(Context ctx) {
		ctx.memory[fix(this.argument)] = ctx.a.val;
		ctx.pc.val += 2;
		updateViewer(ctx, fix(this.argument), ctx.a.val);
	}
}
