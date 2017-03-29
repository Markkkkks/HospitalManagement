
public class Hospital {

	private int remain_bed;// ʣ�ലλ��
	private static Bed[] beds;// ҽԺ�����б�
	public patients mPatients;// ҽԺ���ŶӶ���
	private static int[][] before = new int[][] { { 1, 1, 1, 1, 1, 1, 1 }, { 2, 3, 2, 2, 2, 2, 3 },
			{ 2, 3, 2, 2, 2, 2, 3 }, { 1, 2, 1, 5, 4, 3, 2 } };

	public Hospital(int bednumber) {
		remain_bed = bednumber;
		beds = new Bed[bednumber];
		InitialBed();
		mPatients = new patients();
	}

	public void InitialBed() {
		for (int i = 0; i < remain_bed; i++) {
			beds[i] = new Bed();
		}
	}

	public void updatehospital() {// ʵ�ֲ��˵ĳ�Ժ���������˵�סԺʱ��-1
		for (Bed b : beds) {
			if (b.getFlag() == true) {// �������� û�˾�ֱ������
				int dayout = b.getDayout();// �������Ժ������

				if (dayout == 1) {// ���˳�Ժ
					// cured.add(hospital.getBed(i).getPatient()); ��Ժ����
					remain_bed++;// ҽԺ���в�����1
					b.setFlag(false);// ����״̬��Ϊ����
				} else
					b.setDayout(--dayout);// ��Ժ����-1��
			}
		}
	}

	public void printBed() {
		System.out.println("      flag PatientID Kind Dayout");
		for (int i = 0; i < beds.length; i++) {
			if (beds[i].flag) {// ������Ϊ��
				System.out.println("����" + i + ": " + beds[i].flag + "    " + beds[i].getPatient().id + "     "
						+ beds[i].getPatient().kind + "     " + beds[i].getDayout());
			} else {// ����Ϊ��
				System.out.println("����" + i + ": " + beds[i].flag + "   *    **    ***");
			}
		}
	}

	private void insert(patient p, int bednum) throws MyException {// ���벡�˵�����Ϊbednum�Ĵ�λ
		if (p.id == patient.Tid)
			throw new MyException();
		beds[bednum].setPatient(p);
		beds[bednum].setFlag(true);
		beds[bednum].setDayout(p.getAfterSurgery() + p.getBeforeSurgery());
	}

	private int find(int kindid) {// ���ݲ����ҵ���һ�������ڳ��Ĵ�λ�����Ҳ���
		if (kindid == 0) {// ���ڿմ�
			for (int i = 0; i < beds.length; i++) {
				if (beds[i].getFlag() == false) {
					return i;
				}
			}
		} else if (0 < kindid && kindid < 5) {// �����ڿմ����ҵ�����ռ�Ĳ�����
			for (int i = 0; i < beds.length; i++) {
				if ((beds[i].getPatient().getKind() == kindid)// idƥ��
						&& beds[i].getDayout() > beds[i].getPatient().getAfterSurgery())// ���һ�û����
					return i;
			}
		}
		return 0;
	}

	private int[] setArgs(int week_of_day) {
		int[] args;
		if (week_of_day == 5) {// ����
			args = new int[] { 1, 4, 2, 3 };
		} else if (week_of_day == 6 || week_of_day == 7 || week_of_day == 1) {
			args = new int[] { 1, 4, 3, 2 };// ���塢������
		} else {
			args = new int[] { 1, 3, 2, 4 };
		}
		return args;
	}

	public void allocate(int week_of_day) throws MyException {// ���벡�˵�����
		int bednum;// ���ڴ��洲λ��
		int[] args = setArgs(week_of_day);
		int q1size = mPatients.getQueueSize(args[0]);
		while (q1size != 0) {
			if (remain_bed > 0) {
				bednum = find(0);// �������ҵ�������
				patient tempp = mPatients.pop(args[0]); // Q1.getDeque().pollFirst();
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				insert(tempp, bednum);// ���벡��
				remain_bed--;
				// tempRemain=hospital.getRemainBed();//�¼ӵ�
				// hospital.setRemainBed(--tempRemain);//�¼ӵ�
				q1size--;// insert����q1size--���ҵ�����λ�Ĳ��˲�����Լ�����ͷ��
			} else if (find(args[3]) != 0) {
				bednum = find(args[3]);// �������ҵ�������
				patient tempp = mPatients.pop(args[0]);// ���˳���
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[3], beds[bednum].getPatient());// ���߲��˲������
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q4.outToQueue(return_patient);
				insert(tempp, bednum);// ���˲��벡��
				q1size--;
			} else if (find(args[2]) != 0) {
				bednum = find(args[2]);// �������ҵ�������
				patient tempp = mPatients.pop(args[0]);
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[2], beds[bednum].getPatient());
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q3.outToQueue(return_patient);
				insert(tempp, bednum);
				q1size--;
			} else if (find(args[1]) != 0) {
				bednum = find(args[1]);// �������ҵ�������
				patient tempp = mPatients.pop(args[0]);
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[1], beds[bednum].getPatient());
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q2.outToQueue(return_patient);
				insert(tempp, bednum);
				q1size--;
			} // insert����q1size--
			else
				return;// û�пմ�λ���߿��Ե����Ĵ�λ
		}
		int q2size = mPatients.getQueueSize(args[1]);
		while (q2size != 0) {
			if (remain_bed > 0) {
				bednum = find(0);// �������ҵ�������
				patient tempp = mPatients.pop(args[1]); // Q2.getDeque().pollFirst();
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				insert(tempp, bednum);// ���벡��
				remain_bed--;
				q2size--;// insert����q1size--���ҵ�����λ�Ĳ��˲�����Լ�����ͷ��
			} else if (find(args[3]) != 0) {
				bednum = find(args[3]);// �������ҵ�������
				patient tempp = mPatients.pop(args[1]);// ���˳���
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[3], beds[bednum].getPatient());// ���߲��˲������
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q4.outToQueue(return_patient);
				insert(tempp, bednum);// ���˲��벡��
				q2size--;
			} else if (find(args[2]) != 0) {
				bednum = find(args[2]);// �������ҵ�������
				patient tempp = mPatients.pop(args[1]);// ���˳���
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[2], beds[bednum].getPatient());// ���߲��˲������
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q3.outToQueue(return_patient);
				insert(tempp, bednum);// ���˲��벡��
				q2size--;
			} else
				return;// û�пմ�λ���߿��Ե����Ĵ�λ
		}
		int q3size = mPatients.getQueueSize(args[2]);
		while (q3size != 0) {
			if (remain_bed > 0) {
				bednum = find(0);// �������ҵ�������
				patient tempp = mPatients.pop(args[2]); // Q3.getDeque().pollFirst();
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				insert(tempp, bednum);// ���벡��
				remain_bed--;
				q3size--;// insert����q1size--���ҵ�����λ�Ĳ��˲�����Լ�����ͷ��
			} else if (find(args[3]) != 0) {
				bednum = find(args[3]);// �������ҵ�������
				patient tempp = mPatients.pop(args[2]);// ���˳���
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				mPatients.push(args[3], beds[bednum].getPatient());// ���߲��˲������
				// patient return_patient=hospital.getBed(bednum).getPatient();
				// Q4.outToQueue(return_patient);
				insert(tempp, bednum);// ���˲��벡��
				q3size--;
			} else
				return;// û�пմ�λ���߿��Ե����Ĵ�λ
		}
		int q4size = mPatients.getQueueSize(args[3]);
		while (q4size != 0) {
			if (remain_bed > 0) {
				bednum = find(0);// �������ҵ�������
				patient tempp = mPatients.pop(args[3]); // Q3.getDeque().pollFirst();
				tempp.setBeforeSurgery(before[tempp.getKind() - 1][week_of_day - 1]);// ������ǰʱ��
				insert(tempp, bednum);// ���벡��
				remain_bed--;
				q4size--;// insert����q1size--���ҵ�����λ�Ĳ��˲�����Լ�����ͷ��
			} else
				return;// û�пմ�λ���߿��Ե����Ĵ�λ
		}
	}
}
