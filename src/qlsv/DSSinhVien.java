package qlsv;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import util.IO_Interface;

public class DSSinhVien implements IO_Interface {
	protected ArrayList<SinhVien> ds;

	public DSSinhVien() {
		ds = new ArrayList<SinhVien>();
	}

	public DSSinhVien(ArrayList<SinhVien> ds) {
		this.ds = (ArrayList<SinhVien>) ds.clone();
	}

	public DSSinhVien(DSSinhVien dssv) {
		this.ds = (ArrayList<SinhVien>) dssv.ds.clone();
	}

	public ArrayList<SinhVien> getDssv() {
		return ds;
	}

	public SinhVien get(int index) {
		return ds.get(index);
	}

	public void add(SinhVien sv) {
		ds.add(sv);
	}

	public boolean isHocKyExist(int tenHocKy) {
		boolean flag = false;
		for (SinhVien s : ds) {
			for (Map.Entry d : s.getKqHt().entrySet()) {
				if ((int) d.getKey() == tenHocKy)
					flag = true;
			}
		}
		return flag;
	}

	public int input() {
		int isTiepTuc;
		SinhVien sv;
		do {
			Object[] options = { "Sinh Vien Chinh Quy",
					"Sinh Vien Tai Chuc" };
			int n = JOptionPane.showOptionDialog(null, null, "Lua chon", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (n == JOptionPane.DEFAULT_OPTION)
				return -1;
			else if (n == JOptionPane.YES_OPTION)
				sv = new SinhVienChinhQuy();
			else
				sv = new SinhVienTaiChuc();
			if (sv.input() == 0)
				add(sv);
			else
				return -1;
			isTiepTuc = JOptionPane.showConfirmDialog(null, String.format("Ban co muon them sinh vien ?"), "Xac nhan",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		} while (isTiepTuc == JOptionPane.YES_OPTION);
		return 0;
	}

	public String output() {
		String s = "";
		for (SinhVien e : ds) {
			s += e.output();
		}

		return s;
	}

	public String list() {
		String res = String.format("%10s | %-30s | %15s | %7s | %5s | %20s", "MASV", "HO TEN", "NGAY SINH",
				"NAM HOC", "DDV", "NOI LKDT");
		res += "\n";
		res += "------------------------------------------------------------------------------------------------------";
		res += "\n";
		for (SinhVien s : ds) {
			res += String.format("%10d | %-30s | %15s | %7d | %05.2f | %20s", s.getMaSV(), s.getHoTen(),
					s.getNgaySinhString(),
					s.getNamHoc(), s.getDiemDV(),
					(s.isSVTaiChuc()) ? ((SinhVienTaiChuc) s).getNoiLKDaoTao() : "Khong");
			res += "\n";
		}
		res += "------------------------------------------------------------------------------------------------------";
		res += "\n";
		return res;
	}
}