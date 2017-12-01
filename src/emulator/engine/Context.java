package emulator.engine;

import emulator.registers.Register;
import emulator.src.SrcModel;

/**
 * CPU context. Holds all registers, flags and memory.
 */
public class Context {
	public Register a = new Register("A", this);
	public Register b = new Register("B", this);
	public Register c = new Register("C", this);
	public Register pc = new Register("PC", this);
	public Register sp = new Register("SP", this);
	public Register h = new Register("H", this);
	
	public Register z = new Register("Z", this);
	public Register p = new Register("P", this);
	public Register o = new Register("O", this);
	
	public short[] memory = new short[65536];
	
	
	public SrcModel mdl;
	public Engine engine;
	
	public Context() {
		this.mdl = new SrcModel(this.memory);
	}
	
	public void reset() {
		a.val = 0;
		b.val = 0;
		c.val = 0;
		pc.val = 0;
		sp.val = 0;
		h.val = 0;
		z.val = 0;
		p.val = 0;
		o.val = 0;
		if (this.engine != null && this.engine.main.memViewer != null) {
			engine.main.memViewer.display.setText("                ");
			engine.main.sfViewer.display.setText("                ");
		}
	}

	public void load(String fileName) {
		memory = new short[65536];
		this.mdl = new SrcModel(fileName, memory);
	}
}
