
public class Bed {
	private patient p;// �ò����Ĳ���
	boolean flag;// ��λ�Ƿ��в���
	private int dayout;// �������Ժ������

	public Bed() {
		p = null;
		flag = false;
		dayout = 0;
	}

	public Bed(patient p, boolean flag, int dayout) {
		this.p = p;
		this.flag = flag;
		this.dayout = dayout;
	}

	public patient getPatient() {
		return p;
	}

	public boolean getFlag() {
		return flag;
	}

	public int getDayout() {
		return dayout;
	}

	public void setPatient(patient p) {
		this.p = p;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setDayout(int dayout) {
		this.dayout = dayout;
	}
}
