
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
		System.out.println("请输入医院规模");
		int HosSize = reader.nextInt();
		mHospital = new Hospital(HosSize);
		cal = Calendar.getInstance();
	}

	private int getIntervalDays(Date fDate, Date oDate) {// 计算两个日期差

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
				System.out.println("输入病人种类1-4");
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
			System.out.println("继续或者停止 1/0 2跟踪病人");
			option = reader.nextInt();

			cal.set(Calendar.DATE, ++daynum);
		} while (option == 1 || option == 2);
	}

	private void prepare(int day) throws MyException {
		int daynum, week_of_day;
		System.out.println("您选择了预先输入" + day + "天病人");
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
		System.out.println("预先输入结束");
		mHospital.printBed();
	}

	private void predict() throws Exception {
		System.out.println("开始预测病人出院时间");
		int daynum, week_of_day;
		daynum = cal.get(Calendar.DATE);
		week_of_day = cal.get(Calendar.DAY_OF_WEEK);
		Date Ddate = new Date();
		Ddate = cal.getTime();
		System.out.println(Ddate);
		System.out.println("输入病人种类1-4");
		int kind;
		kind = reader.nextInt();
		while (kind > 4 || kind < 1) {
			// int kind = reader.nextInt();
			System.out.println("不允许输入其他病种");
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
				throw new Exception("超过365天还不能出院");
		}
	}

	public static void main(String args[]) throws MyException {
		HosManager mHosManager = new HosManager();
		mHosManager.Initialize();
		System.out.println("请输入预先输入进医院的病人天数");
		int day = mHosManager.reader.nextInt();
		mHosManager.prepare(day);
		try {
			mHosManager.predict();
			// mHosManager.working();

		} catch (MyException e) {
			// TODO: handle exception
			System.err.println(patient.Tid + "号病人预测等"
					+ mHosManager.getIntervalDays(patient.date, mHosManager.cal.getTime()) + "天");
			// System.out.println(patient.calendar.getTime());
			// System.out.println(mHosManager.cal.getTime());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("不能预测该病人出院时间");
		}
	}
}
