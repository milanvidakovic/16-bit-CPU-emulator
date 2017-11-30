package emulator.src.cmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CMP_A_XX extends Instruction {
	public CMP_A_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("cmp a, 0x%04x");
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.a.val == this.argument) {
			ctx.z.val = 1;
		} else {
			ctx.z.val = 0;
		}
		if (ctx.a.val >= this.argument) {
			ctx.p.val = 1;
		} else {
			ctx.p.val = 0;
		}
		ctx.o.val = 0;
		ctx.pc.val += 2;
	}
}
