import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

public class patients {
	// ���map���ڱ���ÿ���²����Ĳ���
	// ������Ҫ4���������ڹ����Ŷ��еĲ���

	private ArrayList<patient> NewP;// ͨ�����ɷֲ������²���
	public LinkedList<patient> Q1, Q2, Q3, Q4;
	private static int patient_id = 0;

	public patients() {
		// TODO ��ʼ����Ա����
		Q1 = new LinkedList<patient>();
		Q2 = new LinkedList<patient>();
		Q3 = new LinkedList<patient>();
		Q4 = new LinkedList<patient>();
	}

	double P_rand(double Lamda) { // ���ɷֲ�
		double x = 0, b = 1, c = Math.exp(-Lamda), u;
		do {
			u = Math.random();
			b *= u;
			if (b >= c)
				x++;
		} while (b >= c);
		return x;
	}

	public void Arrival(int a, Date date) { // ���ɲ��˲�������뵽�ŶӶ�����
		NewP = new ArrayList<patient>();
		int[] args = new int[4];
		// ���ɲ���
		for (int i = 0; i < 4; i++) {
			int temp = (int) P_rand(1.1);
			args[i] = temp + 1;
		}
		// ѭ���������²��˲��뵽NewP��
		for (int i = 1; i <= args[0]; i++) {
			patient_id++;
			if (i == 1 && a == 1) {
				patient.Tid = patient_id;// ���ٸ�id
				patient.date = date;// ��������
			}
			NewP.add(new patient(patient_id, 1, 0, 6));
		}
		for (int i = 1; i <= args[1]; i++) {
			patient_id++;
			if (i == 1 && a == 2) {
				patient.Tid = patient_id;// ���ٸ�id
				patient.date = date;// ��������
			}
			NewP.add(new patient(patient_id, 2, 0, 7));
		}
		for (int i = 1; i <= args[2]; i++) {
			patient_id++;
			if (i == 1 && a == 3) {
				patient.Tid = patient_id;// ���ٸ�id
				patient.date = date;// ��������
			}
			NewP.add(new patient(patient_id, 3, 0, 10));
		}
		for (int i = 1; i <= args[3]; i++) {
			patient_id++;
			if (i == 1 && a == 4) {
				patient.Tid = patient_id;// ���ٸ�id
				patient.date = date;// ��������
			}
			NewP.add(new patient(patient_id, 4, 0, 14));
		}
		// �²��˲��뵽����
		for (patient p : NewP) {
			int kind = p.kind;
			if (kind == 1) {
				Q1.addLast(p);// β���������1
			} else if (kind == 2) {
				Q2.addLast(p);// β���������2
			} else if (kind == 3) {
				Q3.addLast(p);// β���������3
			} else if (kind == 4) {
				Q4.addLast(p);// β���������4
			}
		}
		NewP = null;
	}

	public int getQueueSize(int num) {
		int size = 0;
		switch (num) {
		case 1:
			size = Q1.size();
			break;
		case 2:
			size = Q2.size();
			break;
		case 3:
			size = Q3.size();
			break;
		case 4:
			size = Q4.size();
			break;
		default:
			break;
		}
		return size;
	}

	public void push(int num, patient p) {
		switch (num) {
		case 1:
			Q1.addFirst(p);
			break;
		case 2:
			Q2.addFirst(p);
			break;
		case 3:
			Q3.addFirst(p);
			break;
		case 4:
			Q4.addFirst(p);
			break;
		default:
			break;
		}
	}

	public patient pop(int num) {
		patient tempp = null;
		switch (num) {
		case 1:
			tempp = Q1.removeFirst();
			break;
		case 2:
			tempp = Q2.removeFirst();
			break;
		case 3:
			tempp = Q3.removeFirst();
			break;
		case 4:
			tempp = Q4.removeFirst();
			break;
		default:
			break;
		}
		return tempp;
	}

}
