package kr.or.hundbheroku.dto;

public class classTimeDto {
	private int id;
	private String name;
	
	private String M1;
	private String M2;
	private String M3;
	private String M4;
	private String M5;
	
	private String T1;
	private String T2;
	private String T3;
	private String T4;
	private String T5;
	
	private String W1;
	private String W2;
	private String W3;
	private String W4;
	private String W5;
	
	private String TH1;
	private String TH2;
	private String TH3;
	private String TH4;
	private String TH5;
	
	private String F1;
	private String F2;
	private String F3;
	private String F4;
	private String F5;
	
	public classTimeDto(int id, String name, String M1, String M2, String M3, String M4, String M5, 
											  String T1, String T2, String T3, String T4, String T5,
			                                  String W1, String W2, String W3, String W4, String W5,
			                                  String TH1, String TH2, String TH3, String TH4, String TH5, 
			                                  String F1, String F2, String F3, String F4, String F5) {
		super();
		this.id = id;
		this.name = name;
		
		this.M1 = M1; 
		this.M2 = M2; 
		this.M3 = M3; 
		this.M4 = M4; 
		this.M5 = M5; 

		this.T1 = T1; 
		this.T2 = T2; 
		this.T3 = T3; 
		this.T4 = T4; 
		this.T5 = T5; 
		
		this.W1 = W1; 
		this.W2 = W2; 
		this.W3 = W3; 
		this.W4 = W4; 
		this.W5 = W5; 
		
		this.TH1 = TH1;
		this.TH2 = TH2;
		this.TH3 = TH3;
		this.TH4 = TH4;
		this.TH5 = TH5;
		
		this.F1 = F1; 
		this.F2 = F2; 
		this.F3 = F3; 
		this.F4 = F4; 
		this.F5 = F5;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getM1() {
		return M1;
	}

	public void setM1(String m1) {
		M1 = m1;
	}

	public String getM2() {
		return M2;
	}

	public void setM2(String m2) {
		M2 = m2;
	}

	public String getM3() {
		return M3;
	}

	public void setM3(String m3) {
		M3 = m3;
	}

	public String getM4() {
		return M4;
	}

	public void setM4(String m4) {
		M4 = m4;
	}

	public String getM5() {
		return M5;
	}

	public void setM5(String m5) {
		M5 = m5;
	}

	public String getT1() {
		return T1;
	}

	public void setT1(String t1) {
		T1 = t1;
	}

	public String getT2() {
		return T2;
	}

	public void setT2(String t2) {
		T2 = t2;
	}

	public String getT3() {
		return T3;
	}

	public void setT3(String t3) {
		T3 = t3;
	}

	public String getT4() {
		return T4;
	}

	public void setT4(String t4) {
		T4 = t4;
	}

	public String getT5() {
		return T5;
	}

	public void setT5(String t5) {
		T5 = t5;
	}

	public String getW1() {
		return W1;
	}

	public void setW1(String w1) {
		W1 = w1;
	}

	public String getW2() {
		return W2;
	}

	public void setW2(String w2) {
		W2 = w2;
	}

	public String getW3() {
		return W3;
	}

	public void setW3(String w3) {
		W3 = w3;
	}

	public String getW4() {
		return W4;
	}

	public void setW4(String w4) {
		W4 = w4;
	}

	public String getW5() {
		return W5;
	}

	public void setW5(String w5) {
		W5 = w5;
	}

	public String getTH1() {
		return TH1;
	}

	public void setTH1(String tH1) {
		TH1 = tH1;
	}

	public String getTH2() {
		return TH2;
	}

	public void setTH2(String tH2) {
		TH2 = tH2;
	}

	public String getTH3() {
		return TH3;
	}

	public void setTH3(String tH3) {
		TH3 = tH3;
	}

	public String getTH4() {
		return TH4;
	}

	public void setTH4(String tH4) {
		TH4 = tH4;
	}

	public String getTH5() {
		return TH5;
	}

	public void setTH5(String tH5) {
		TH5 = tH5;
	}

	public String getF1() {
		return F1;
	}

	public void setF1(String f1) {
		F1 = f1;
	}

	public String getF2() {
		return F2;
	}

	public void setF2(String f2) {
		F2 = f2;
	}

	public String getF3() {
		return F3;
	}

	public void setF3(String f3) {
		F3 = f3;
	}

	public String getF4() {
		return F4;
	}

	public void setF4(String f4) {
		F4 = f4;
	}

	public String getF5() {
		return F5;
	}

	public void setF5(String f5) {
		F5 = f5;
	}

	@Override
	public String toString() {
		return "classTimeDto [id=" + id + ", name=" + name + ", M1=" + M1 + ", M2=" + M2 + ", M3=" + M3 + ", M4=" + M4
				+ ", M5=" + M5 + ", T1=" + T1 + ", T2=" + T2 + ", T3=" + T3 + ", T4=" + T4 + ", T5=" + T5 + ", W1=" + W1
				+ ", W2=" + W2 + ", W3=" + W3 + ", W4=" + W4 + ", W5=" + W5 + ", TH1=" + TH1 + ", TH2=" + TH2 + ", TH3="
				+ TH3 + ", TH4=" + TH4 + ", TH5=" + TH5 + ", F1=" + F1 + ", F2=" + F2 + ", F3=" + F3 + ", F4=" + F4
				+ ", F5=" + F5 + "]";
	}
}
