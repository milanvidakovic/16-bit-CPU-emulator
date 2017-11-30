package emulator.src.cmp;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CMP_MB_XX_A extends Instruction {
	public CMP_MB_XX_A(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("cmp [b + 0x%04x], a");
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.memory[fix(ctx.b.val) + this.argument] == ctx.a.val) {
			ctx.z.val = 1;
		} else {
			ctx.z.val = 0;
		}
		if (ctx.memory[fix(ctx.b.val) + this.argument] >= ctx.a.val) {
			ctx.p.val = 1;
		} else {
			ctx.p.val = 0;
		}
		ctx.o.val = 0;
		ctx.pc.val += 2;
	}
}
