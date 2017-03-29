
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HosManager {
	private Scanner reader;
	private Hospital mHospital;
	private Calendar cal;

	private void Initialize() {
		reader = new Scanner(System.in);
		System.out.println("������ҽԺ��ģ");
		int HosSize = reader.nextInt();
		mHospital = new Hospital(HosSize);
		cal = Calendar.getInstance();
	}

	private int getIntervalDays(Date fDate, Date oDate) {// �����������ڲ�

		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	private void working() throws MyException {
		int option = 0, daynum, week_of_day;
		do {
			daynum = cal.get(Calendar.DATE);
			week_of_day = cal.get(Calendar.DAY_OF_WEEK);
			Date Ddate = new Date();
			Ddate = cal.getTime();
			System.out.println(Ddate);
			if (option == 2) {
				System.out.println("���벡������1-4");
				int kind = reader.nextInt();
				mHospital.mPatients.Arrival(kind, Ddate);

			} else {
				mHospital.mPatients.Arrival(0, Ddate);
			}
			mHospital.updatehospital();
			mHospital.allocate(week_of_day);
			mHospital.printBed();
			if (patient.date != null)
				System.out.println(patient.date + "+++++++++++++++");
			System.out.println("��������ֹͣ 1/0 2���ٲ���");
			option = reader.nextInt();

			cal.set(Calendar.DATE, ++daynum);
		} while (option == 1 || option == 2);
	}

	private void prepare(int day) throws MyException {
		int daynum, week_of_day;
		System.out.println("��ѡ����Ԥ������" + day + "�첡��");
		while (day != 0) {
			daynum = cal.get(Calendar.DATE);
			week_of_day = cal.get(Calendar.DAY_OF_WEEK);
			Date Ddate = new Date();
			Ddate = cal.getTime();
			// System.out.println(Ddate);
			mHospital.mPatients.Arrival(0, Ddate);
			mHospital.updatehospital();
			mHospital.allocate(week_of_day);
			cal.set(Calendar.DATE, ++daynum);
			day--;
		}
		System.out.println("Ԥ���������");
		mHospital.printBed();
	}

	private void predict() throws Exception {
		System.out.println("��ʼԤ�ⲡ�˳�Ժʱ��");
		int daynum, week_of_day;
		daynum = cal.get(Calendar.DATE);
		week_of_day = cal.get(Calendar.DAY_OF_WEEK);
		Date Ddate = new Date();
		Ddate = cal.getTime();
		System.out.println(Ddate);
		System.out.println("���벡������1-4");
		int kind;
		kind = reader.nextInt();
		while (kind > 4 || kind < 1) {
			// int kind = reader.nextInt();
			System.out.println("������������������");
			kind = reader.nextInt();
		}
		mHospital.mPatients.Arrival(kind, Ddate);
		cal.set(Calendar.DATE, ++daynum);
		int i = 0;
		while (true) {

			mHospital.mPatients.Arrival(0, Ddate);
			mHospital.updatehospital();
			mHospital.allocate(week_of_day);
			cal.set(Calendar.DATE, ++daynum);
			i++;
			// System.out.println(i++);
			if (i > 365)
				throw new Exception("����365�컹���ܳ�Ժ");
		}
	}

	public static void main(String args[]) throws MyException {
		HosManager mHosManager = new HosManager();
		mHosManager.Initialize();
		System.out.println("������Ԥ�������ҽԺ�Ĳ�������");
		int day = mHosManager.reader.nextInt();
		mHosManager.prepare(day);
		try {
			mHosManager.predict();
			// mHosManager.working();

		} catch (MyException e) {
			// TODO: handle exception
			System.err.println(patient.Tid + "�Ų���Ԥ���"
					+ mHosManager.getIntervalDays(patient.date, mHosManager.cal.getTime()) + "��");
			// System.out.println(patient.calendar.getTime());
			// System.out.println(mHosManager.cal.getTime());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("����Ԥ��ò��˳�Ժʱ��");
		}
	}
}
