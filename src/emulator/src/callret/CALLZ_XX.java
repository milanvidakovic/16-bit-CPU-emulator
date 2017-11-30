package emulator.src.callret;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CALLZ_XX extends Instruction {
	public CALLZ_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("callz 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.z.val == 1) {
			ctx.memory[fix(ctx.sp.val)] = (short)(ctx.pc.val + 2);
			updateViewer(ctx, fix(ctx.sp.val), (short)(ctx.pc.val + 2));
			ctx.sp.val++;
			ctx.pc.val = this.argument;
		}
	}
}
