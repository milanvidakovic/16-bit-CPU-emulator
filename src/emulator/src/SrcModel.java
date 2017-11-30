package emulator.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import emulator.src.addsub.ADD_A_B;
import emulator.src.addsub.ADD_A_C;
import emulator.src.addsub.ADD_A_MB;
import emulator.src.addsub.ADD_A_MB_XX;
import emulator.src.addsub.ADD_A_XX;
import emulator.src.addsub.ADD_SP_XX;
import emulator.src.addsub.SUB_A_B;
import emulator.src.addsub.SUB_A_C;
import emulator.src.addsub.SUB_A_MB;
import emulator.src.addsub.SUB_A_MB_XX;
import emulator.src.addsub.SUB_A_XX;
import emulator.src.addsub.SUB_SP_XX;
import emulator.src.andorxor.AND_A_B;
import emulator.src.andorxor.AND_A_MB;
import emulator.src.andorxor.AND_A_MB_XX;
import emulator.src.andorxor.AND_A_XX;
import emulator.src.andorxor.NEG_A;
import emulator.src.andorxor.OR_A_B;
import emulator.src.andorxor.OR_A_MB;
import emulator.src.andorxor.OR_A_MB_XX;
import emulator.src.andorxor.OR_A_XX;
import emulator.src.andorxor.XOR_A_B;
import emulator.src.andorxor.XOR_A_C;
import emulator.src.andorxor.XOR_A_MB;
import emulator.src.callret.CALLNO_XX;
import emulator.src.callret.CALLNP_XX;
import emulator.src.callret.CALLNZ_XX;
import emulator.src.callret.CALLO_XX;
import emulator.src.callret.CALLP_XX;
import emulator.src.callret.CALLZ_XX;
import emulator.src.callret.CALL_XX;
import emulator.src.callret.RET;
import emulator.src.cmp.CMP_A_B;
import emulator.src.cmp.CMP_A_MB;
import emulator.src.cmp.CMP_A_MB_XX;
import emulator.src.cmp.CMP_A_XX;
import emulator.src.cmp.CMP_B_XX;
import emulator.src.cmp.CMP_MB_XX_A;
import emulator.src.general.HALT;
import emulator.src.general.NOP;
import emulator.src.incdec.DEC_A;
import emulator.src.incdec.DEC_B;
import emulator.src.incdec.DEC_C;
import emulator.src.incdec.DEC_MB;
import emulator.src.incdec.INC_A;
import emulator.src.incdec.INC_B;
import emulator.src.incdec.INC_C;
import emulator.src.incdec.INC_MB;
import emulator.src.jmp.JMP_XX;
import emulator.src.jmp.JNO_XX;
import emulator.src.jmp.JNP_XX;
import emulator.src.jmp.JNZ_XX;
import emulator.src.jmp.JO_XX;
import emulator.src.jmp.JP_XX;
import emulator.src.jmp.JZ_XX;
import emulator.src.load.LD_A_B;
import emulator.src.load.LD_A_C;
import emulator.src.load.LD_A_H;
import emulator.src.load.LD_A_MB;
import emulator.src.load.LD_A_MB_XX;
import emulator.src.load.LD_A_MC;
import emulator.src.load.LD_A_MC_XX;
import emulator.src.load.LD_A_XX;
import emulator.src.load.LD_B_A;
import emulator.src.load.LD_B_C;
import emulator.src.load.LD_B_SP;
import emulator.src.load.LD_B_XX;
import emulator.src.load.LD_C_A;
import emulator.src.load.LD_C_B;
import emulator.src.load.LD_C_XX;
import emulator.src.load.LD_SP_B;
import emulator.src.load.LD_SP_XX;
import emulator.src.muldiv.DIV_A_B;
import emulator.src.muldiv.DIV_A_C;
import emulator.src.muldiv.DIV_A_MB_XX;
import emulator.src.muldiv.DIV_A_XX;
import emulator.src.muldiv.MUL_A_B;
import emulator.src.muldiv.MUL_A_C;
import emulator.src.muldiv.MUL_A_MB_XX;
import emulator.src.muldiv.MUL_A_XX;
import emulator.src.pushpop.POP_A;
import emulator.src.pushpop.POP_B;
import emulator.src.pushpop.POP_C;
import emulator.src.pushpop.PUSH_A;
import emulator.src.pushpop.PUSH_B;
import emulator.src.pushpop.PUSH_C;
import emulator.src.pushpop.PUSH_XX;
import emulator.src.shift.SHL_A_B;
import emulator.src.shift.SHL_A_MB;
import emulator.src.shift.SHL_A_MB_XX;
import emulator.src.shift.SHL_A_XX;
import emulator.src.shift.SHR_A_B;
import emulator.src.shift.SHR_A_MB;
import emulator.src.shift.SHR_A_MB_XX;
import emulator.src.shift.SHR_A_XX;
import emulator.src.store.ST_A_MB;
import emulator.src.store.ST_A_MB_C_XX;
import emulator.src.store.ST_A_MB_XX;
import emulator.src.store.ST_A_MC;
import emulator.src.store.ST_A_MC_XX;
import emulator.src.store.ST_A_MXX;
import emulator.src.store.ST_B_MXX;

public class SrcModel extends AbstractTableModel {
	private static final long serialVersionUID = 8062097745546805165L;
	
	public String[] columnNames = { "Breakpoint", "Addr", "Content", "Assembler" };
	public List<Instruction> lines = new ArrayList<Instruction>();
	public Instruction[] addr_instr= new Instruction[65536];
	public short[] memory;

	public SrcModel(short[] memory) {
		this.memory = memory;
	}
	
	public SrcModel(String fileName, short[] memory) {
		this.memory = memory;
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fileName));
			in.readLine(); // preskočimo prvi red
			String s = in.readLine();
			in.close();
			parse(s);
			disassm();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disassm() {
		int addr = 0;
		boolean finished = false;
		while (!finished) {
			Instruction instr = getInstruction(memory, addr++);
			instr.setContent();
			lines.add(instr);
			instr.tableLine = lines.size() - 1;
			if (instr.hasArgument) 
				addr++;
			if (addr == 65536)
				finished = true;
			addr_instr[instr.addr] = instr;
		}
	}
	
	private Instruction getInstruction(short[] memory, int addr) {
		switch((int)memory[addr++]) {
		case 0x0000:
			return new NOP(memory, addr);
		case 0x0004:
			return new LD_A_B(memory, addr);
		case 0x0006:
			return new LD_B_A(memory, addr);
		case 0x0008:
			return new LD_A_XX(memory, addr);
		case 0x000a:
			return new LD_B_XX(memory, addr);
		case 0x000c:
			return new LD_A_MB(memory, addr);
		case 0x000e:
			return new LD_A_MB_XX(memory, addr);
		case 0x0012:
			return new LD_SP_XX(memory, addr);
		case 0x0014:
			return new LD_B_SP(memory, addr);
		case 0x0016:
			return new LD_SP_B(memory, addr);
		case 0x0018:
			return new ADD_A_B(memory, addr);
		case 0x001a:
			return new SUB_A_B(memory, addr);
		case 0x001c:
			return new ADD_SP_XX(memory, addr);
		case 0x0020:
			return new SUB_SP_XX(memory, addr);
		case 0x0024:
			return new ADD_A_MB(memory, addr);
		case 0x0028:
			return new SUB_A_MB(memory, addr);
		case 0x002c:
			return new ADD_A_MB_XX(memory, addr);
		case 0x0032:
			return new SUB_A_MB_XX(memory, addr);
		case 0x0038:
			return new ADD_A_XX(memory, addr);
		case 0x003c:
			return new SUB_A_XX(memory, addr);
		case 0x0040:
			return new CMP_A_B(memory, addr);
		case 0x0042:
			return new CMP_A_MB(memory, addr);
		case 0x0046:
			return new CMP_A_XX(memory, addr);
		case 0x004a:
			return new DEC_A(memory, addr);
		case 0x004c:
			return new INC_A(memory, addr);
		case 0x004e:
			return new DEC_B(memory, addr);
		case 0x0050:
			return new INC_B(memory, addr);
		case 0x0052:
			return new DEC_MB(memory, addr);
		case 0x0056:
			return new INC_MB(memory, addr);
		case 0x005a:
			return new AND_A_B(memory, addr);
		case 0x005c:
			return new OR_A_B(memory, addr);
		case 0x005e:
			return new AND_A_MB(memory, addr);
		case 0x0062:
			return new OR_A_MB(memory, addr);
		case 0x0066:
			return new AND_A_XX(memory, addr);
		case 0x006a:
			return new OR_A_XX(memory, addr);
		case 0x006e:
			return new NEG_A(memory, addr);
		case 0x0070:
			return new PUSH_XX(memory, addr);
		case 0x0074:
			return new PUSH_A(memory, addr);
		case 0x0076:
			return new POP_A(memory, addr);
		case 0x0078:
			return new PUSH_B(memory, addr);
		case 0x007a:
			return new POP_B(memory, addr);
		case 0x007c:
			return new JMP_XX(memory, addr);
		case 0x007e:
			return new JZ_XX(memory, addr);
		case 0x0082:
			return new JNZ_XX(memory, addr);
		case 0x0086:
			return new CALL_XX(memory, addr);
		case 0x008a:
			return new RET(memory, addr);
		case 0x008c:
			return new ST_A_MXX(memory, addr);
		case 0x0090:
			return new ST_B_MXX(memory, addr);
		case 0x0094:
			return new ST_A_MB(memory, addr);
		case 0x0096:
			return new ST_A_MB_XX(memory, addr);
		case 0x009a:
			return new JP_XX(memory, addr);
		case 0x009e:
			return new JNP_XX(memory, addr);
		case 0x00a2:
			return new JO_XX(memory, addr);
		case 0x00a6:
			return new JNO_XX(memory, addr);
		case 0x00aa:
			return new AND_A_MB_XX(memory, addr);
		case 0x00b0:
			return new OR_A_MB_XX(memory, addr);
		case 0x00b6:
			return new SHL_A_B(memory, addr);
		case 0x00b8:
			return new SHR_A_B(memory, addr);
		case 0x00ba:
			return new SHL_A_MB(memory, addr);
		case 0x00be:
			return new SHR_A_MB(memory, addr);
		case 0x00c2:
			return new SHL_A_MB_XX(memory, addr);
		case 0x00c8:
			return new SHR_A_MB_XX(memory, addr);
		case 0x00ce:
			return new SHL_A_XX(memory, addr);
		case 0x00d2:
			return new SHR_A_XX(memory, addr);
		case 0x00d6:
			return new CALLZ_XX(memory, addr);
		case 0x00dc:
			return new CALLNZ_XX(memory, addr);
		case 0x00e2:
			return new CALLP_XX(memory, addr);
		case 0x00e8:
			return new CALLNP_XX(memory, addr);
		case 0x00ee:
			return new CALLO_XX(memory, addr);
		case 0x00f4:
			return new CALLNO_XX(memory, addr);
		case 0x00fa:
			return new LD_A_H(memory, addr);
		case 0x0001:
			return new MUL_A_B(memory, addr);
		case 0x0015:
			return new DIV_A_B(memory, addr);
		case 0x0029:
			return new CMP_B_XX(memory, addr);
		case 0x002d:
			return new LD_C_XX(memory, addr);
		case 0x002f:
			return new ST_A_MC(memory, addr);
		case 0x0031:
			return new ST_A_MC_XX(memory, addr);
		case 0x0035:
			return new DEC_C(memory, addr);
		case 0x0037:
			return new INC_C(memory, addr);
		case 0x0039:
			return new LD_C_A(memory, addr);
		case 0x003b:
			return new LD_C_B(memory, addr);
		case 0x003d:
			return new PUSH_C(memory, addr);
		case 0x003f:
			return new POP_C(memory, addr);
		case 0x0041:
			return new MUL_A_MB_XX(memory, addr);
		case 0x0047:
			return new DIV_A_MB_XX(memory, addr);
		case 0x004d:
			return new MUL_A_XX(memory, addr);
		case 0x0051:
			return new DIV_A_XX(memory, addr);
		case 0x0055:
			return new LD_A_C(memory, addr);
		case 0x0057:
			return new LD_B_C(memory, addr);
		case 0x0059:
			return new LD_A_MC(memory, addr);
		case 0x005b:
			return new LD_A_MC_XX(memory, addr);
		case 0x005f:
			return new MUL_A_C(memory, addr);
		case 0x0061:
			return new DIV_A_C(memory, addr);
		case 0x0063:
			return new XOR_A_B(memory, addr);
		case 0x0065:
			return new XOR_A_C(memory, addr);
		case 0x0067:
			return new CMP_A_MB_XX(memory, addr);
		case 0x006d:
			return new XOR_A_MB(memory, addr);
		case 0x0071:
			return new CMP_A_MB_XX(memory, addr);
		case 0x0077:
			return new ADD_A_C(memory, addr);
		case 0x0079:
			return new SUB_A_C(memory, addr);
		case 0x007b:
			return new ST_A_MB_C_XX(memory, addr);
		case 0x0081:
			return new CMP_MB_XX_A(memory, addr);
		case 0x00FF:
			return new HALT(memory, addr);
		}
		return new Instruction(memory, addr);
	}
	
	private void parse(String s) {
		String[] words = s.split(" ");
		short addr = 0;
		for (int i = 0; i < words.length; i++) {
			String w = words[i];
			memory[addr++] = (short)Integer.parseInt(w, 16);
		}
	}



	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return lines.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Instruction i = lines.get(row);
		return i.toCell(col);
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
		// Prva kolona moze da se menja
		if (col < 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		System.out.println("SET BREAKPOINT AT: " + row);
		lines.get(row).breakPoint = (boolean)value;
	}

	public void reset() {
		lines.clear();
		addr_instr = new Instruction[65536];
	}

}
