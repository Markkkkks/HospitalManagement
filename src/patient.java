import java.util.Date;

public class patient {
	int id = 0;
	int kind = 0;// �������ࣺ1.����2.�����3.��������4.�ж���
	int beforeSurgery = 0;// ��ǰ׼��ʱ��
	int AfterSurgery = 0;// ����۲�ʱ��
	static int Tid = 0;
	static Date date;

	public patient() {
	}

	public patient(int id, int kind, int beforeSurgery, int afterSurgery) {
		this.AfterSurgery = afterSurgery;
		this.kind = kind;
		this.beforeSurgery = beforeSurgery;
		this.id = id;
	}

	public int getAfterSurgery() {
		return AfterSurgery;
	}

	public int getBeforeSurgery() {
		return beforeSurgery;
	}

	public int getId() {
		return id;
	}

	public int getKind() {
		return kind;
	}

	public void setBeforeSurgery(int beforeSurgery) {
		this.beforeSurgery = beforeSurgery;
	}

}
