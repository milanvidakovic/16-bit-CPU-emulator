package emulator.memviewer;

import javax.swing.table.AbstractTableModel;

import emulator.engine.Context;
import emulator.src.Instruction;

public class MemModel extends AbstractTableModel {
	private static final long serialVersionUID = 305334635501584898L;
	
	public String[] columnNames = { "Addr", "W1", "W2", "W3", "W4"};
	public short[][] grid = new short[65536 / 4][5];

	private Context ctx;
	
	public MemModel(Context ctx) {
		this.ctx = ctx;
		
		int addr = 0;
		for (int i = 0; i < (65536/4); i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0) {
					grid[i][j] = (short)addr;
				} else {
					grid[i][j] = ctx.memory[addr];
					addr++;
				}
			}
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return grid.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return String.format("%04x", grid[row][col]);
	}

	@Override
	/**
	 * Ako se ova metoda ne redefinise, koristi se default renderer/editor za
	 * celiju. To znaci da, ako je kolona tipa boolean, onda ce se u tabeli
	 * prikazati true/false, a ovako ce se za takav tip kolone pojaviti
	 * checkbox.
	 */
	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		// Prva kolona ne moze da se menja
		if (col < 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		short s;
		if (value instanceof String) {
			col--;
			s = Short.parseShort(value.toString(), 16);
			ctx.memory[row * 4 + col] = s;
			ctx.mdl.reset();
			ctx.mdl.disassm();
			ctx.mdl.fireTableDataChanged();
			Instruction i = ctx.mdl.addr_instr[ctx.pc.val];
			ctx.engine.main.tblSrc.setRowSelectionInterval(i.tableLine, i.tableLine);
			ctx.engine.main.tblSrc.scrollRectToVisible(ctx.engine.main.tblSrc.getCellRect(i.tableLine, 0, true));
		} else {
			s = (short)value;
		}
		grid[row][col + 1] = s;
		System.out.println("CHANGED MEMORY LOCATION CONTENT at addr: " + String.format("0x%04x", ((row * 4) + (col))) + ", to: " + String.format("0x%04x", (s & 0x0000ffff)) + ", row: " + String.format("0x%04x", row*4) + ", col: " + col);
		fireTableCellUpdated(row, row+1);
	}
}
