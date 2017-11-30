package emulator.src.store;

import emulator.engine.Context;
import emulator.src.Instruction;

public class ST_B_MXX extends Instruction {
	public ST_B_MXX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("st b, [0x%04x]");
	}

	@Override
	public void exec(Context ctx) {
		ctx.memory[fix(this.argument)] = ctx.b.val;
		ctx.pc.val += 2;
		updateViewer(ctx, fix(this.argument), ctx.b.val);
	}
}
