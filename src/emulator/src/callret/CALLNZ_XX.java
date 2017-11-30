package emulator.src.callret;

import emulator.engine.Context;
import emulator.src.Instruction;

public class CALLNZ_XX extends Instruction {
	public CALLNZ_XX(short[] memory, int addr) {
		super(memory, addr);
		super.setArgument();
		super.setAssembler("callnz 0x%04x");
		super.isJump = true;
	}

	@Override
	public void exec(Context ctx) {
		if (ctx.z.val == 0) {
			ctx.memory[fix(ctx.sp.val)] = (short)(ctx.pc.val + 2);
			updateViewer(ctx, fix(ctx.sp.val), (short)(ctx.pc.val + 2));
			ctx.sp.val++;
			ctx.pc.val = this.argument;
		}
	}
}
