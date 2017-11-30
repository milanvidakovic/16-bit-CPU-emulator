package emulator.src;

import emulator.engine.Context;

public class Instruction {

	public static final int BREAK_POINT = 0;
	public static final int ADDR = 1;
	public static final int CONTENT = 2;
	public static final int ASSEMBLER = 3;

	public boolean breakPoint;
	public int addr;
	public String content = "";
	public short opcode;
	public boolean hasArgument = false;
	public short argument;
	public String assembler = "";
	public boolean isJump = false;
	
	public short[] memory;
	/**
	 * Table row where this instruction is placed.
	 */
	public int tableLine;

	public Instruction(short[]memory, int addr) {
		this.addr = addr - 1;
		this.memory = memory;
		this.opcode = memory[this.addr];
		setContent();
	}

	public void exec(Context ctx) throws NotImplementedException {
		throw new NotImplementedException(this.assembler + " not implemented yet!");
	}
	
	public Object toCell(int col) {
		switch (col) {
		case BREAK_POINT:
			return breakPoint;
		case ADDR:
			return String.format("%04X", addr);
		case CONTENT:
			return content;
		case ASSEMBLER:
			return assembler;
		}
		return null;
	}

	public void setContent() {
		if (this.hasArgument) {
			this.content = String.format("%04x, %04x", this.opcode, this.argument);
		} else {
			this.content = String.format("%04x", this.opcode);
		}
	}

	public void setArgument() {
		this.argument = memory[this.addr + 1];
		this.hasArgument = true;
	}

	public void setAssembler(String format) {
		if (this.hasArgument) {
			// negativan broj kao argument
			if ((this.argument & 0x8000) != 0) {
				this.assembler = String.format(format + "      ; -%04x", this.argument, neg(this.argument));
			} else {
				this.assembler = String.format(format, this.argument);
			}
		} else {
			this.assembler = format;
		}
	}
	
	protected int fix(short w) {
		return w & 0x000000000000FFFF;
	}
	
	protected void markFlags(int res, short r, Context ctx) {
		if (r == 0) {
			ctx.z.val = 1;
		} else {
			ctx.z.val = 0;
		}
		if ((r & 0x8000) == 1) {
			ctx.p.val = 0;
		} else {
			ctx.p.val = 1;
		}
	}
	
	protected void markOverflow(short a, short b, short res, Context ctx) {
		int sa = sign(a);
		int sb = sign(b);
		int sr = sign(res);
		if (sa == sb) {
			if (sa != sr) {
				ctx.o.val = 1;
				return;
			}
		}		
		ctx.o.val = 0;
	}
	
	private int sign(short a) {
		return a & 0x8000;
	}

	protected void updateViewer(Context ctx, int addr, short content) {
		ctx.engine.updateViewer(addr, content);
	}
	
	private int neg(int arg) {
		return (65536 - arg) & 0xffff;
	}
}
