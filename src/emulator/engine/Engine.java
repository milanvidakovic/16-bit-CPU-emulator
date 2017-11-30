package emulator.engine;

import javax.swing.SwingWorker;

import emulator.Main;
import emulator.memviewer.MemViewer;
import emulator.src.Instruction;
import emulator.src.NotImplementedException;

/**
 * Execution engine. Capable of running in full speed,
 * stepping over and stepping into. supports breakpoints.
 */
public class Engine {
	public Context ctx;
	public Main main;
	public SwingWorker<Void, Instruction> worker;
	public boolean running = false;
	private MemViewer memViewer;
	private MemViewer sfViewer;

	public Engine(Context ctx, Main main) {
		this.ctx = ctx;
		this.main = main;
		this.worker = null;
		this.ctx.reset();
		this.ctx.engine = this;
	}

	public void reset() {
		this.ctx.reset();
		running = false;
		refreshUI(null);
		main.tblSrc.setRowSelectionInterval(0, 0);
		main.tblSrc.scrollRectToVisible(main.tblSrc.getCellRect(0, 0, true));
	}

	public void stop() {
		if (this.worker != null) {
			running = false;
			this.worker.cancel(true);
			this.worker = null;
		}
		if (this.fromStepOver) {
			this.fromStepOver = false;
			Instruction i = ctx.mdl.addr_instr[ctx.pc.val];
			if (i.breakPoint) {
				i.breakPoint = false;
				this.ctx.mdl.fireTableDataChanged();
			}
			
		}
	}

	public void run() {
		running = true;
		worker = new SwingWorker<Void, Instruction>() {
			// executed on a background worker thread
			@Override
			protected Void doInBackground() throws Exception {
				while (running) {
					Instruction i = ctx.mdl.addr_instr[ctx.pc.val];
					if (i.breakPoint) {
						stop();
						break;
					}
					try {
						i.exec(ctx);
					} catch (Exception ex) {
						ex.printStackTrace();
						throw new Exception(ex.getMessage());
					}
					publish(i);
				}
				return null;
			}

			/**
			 * Prima medjurezultate, i odavde ih prikazujem u swing komponentama
			 * Ako metoda publish posalje jednu vrednost, lista chunks ima jedan
			 * element: publish(1, 2, 3) -> process(1, 2, 3), odn. lista chunks
			 * ima tri elementa.
			 */
			@Override
			protected void process(java.util.List<Instruction> chunks) {
				refreshUI(chunks.get(0));
			}

			/**
			 * Poziva se kada se zavrsi izvrsenje (milom ili silom).
			 */
			@Override
			protected void done() {
				main.btnRun.setEnabled(true);
				running = false;
			}
		};

		// startujemo worker
		worker.execute();
	}

	public void stepInto() throws NotImplementedException {
		Instruction i = ctx.mdl.addr_instr[ctx.pc.val];
		i.exec(ctx);
		refreshUI(i);
	}
	boolean fromStepOver = false;
	public void stepOver() throws NotImplementedException {
		Instruction i = ctx.mdl.addr_instr[ctx.pc.val];
		if (i != null && isCall(i)) {
			Instruction next = ctx.mdl.lines.get(i.tableLine +  1);
			if (next != null) {
				next.breakPoint = true;
				this.fromStepOver = true;
				run();
			}
		} else if (i != null) {
			this.fromStepOver = true;
			stepInto();
		}
	}

	private boolean isCall(Instruction i) {
		switch (i.opcode) {
		case 0x0086:
		case 0x00d6:
		case 0x00dc:
		case 0x00e2:
		case 0x00e8:
		case 0x00ee:
		case 0x00f4:
			return true;
		}
		return false;
	}

	public void refreshUI(Instruction i) {
		ctx.a.update();
		ctx.b.update();
		ctx.c.update();
		ctx.pc.update();
		ctx.sp.update();
		ctx.h.update();
		ctx.z.update();
		ctx.p.update();
		ctx.o.update();
		if (i != null) {
			i = ctx.mdl.addr_instr[ctx.pc.val];
			main.tblSrc.setRowSelectionInterval(i.tableLine, i.tableLine);
			main.tblSrc.scrollRectToVisible(main.tblSrc.getCellRect(i.tableLine, 0, true));
		}
	}

	public void setMemViewer(MemViewer memViewer) {
		this.memViewer = memViewer;
	}
	
	public void setSfViewer(MemViewer sfViewer) {
		this.sfViewer = sfViewer;
	}
	public void updateViewer(int addr, short content) {
		memViewer.updateCell(addr, content);
		sfViewer.updateCell(addr, content);
		sfViewer.tblMem.setRowSelectionInterval(addr/4, addr/4);
		sfViewer.tblMem.setColumnSelectionInterval((addr%4) + 1, (addr%4) + 1);
		sfViewer.tblMem.scrollRectToVisible(sfViewer.tblMem.getCellRect(addr/4, 0, true));
		sfViewer.src.revalidate();
		sfViewer.src.repaint();
	}


}