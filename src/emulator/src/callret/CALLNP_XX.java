package emulator.src.callret;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CALLNP_XX extends Instruction {
	public CALLNP_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("callnp 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.p.val == 0) {
			ctx.memory[fix(ctx.sp.val)] = (short)(ctx.pc.val + 2);
			updateViewer(ctx, fix(ctx.sp.val), (short)(ctx.pc.val + 2));
			ctx.sp.val++;
			ctx.pc.val = this.argument;
		}
	}
}
