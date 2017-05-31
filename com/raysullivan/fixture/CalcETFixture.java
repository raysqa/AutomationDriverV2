package raysullivan.fixture;

import raysullivan.operation.AutomationDriverUtil;


//import fit.ColumnFixture;

public class CalcETFixture {
	private long end, start;
	private int millisec = 1000;
	private AutomationDriverUtil util = new AutomationDriverUtil();

	public void setStart(long start) {
		this.start = start;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	public String elapsedTime() {
		// util = new AutomationDriverUtil();
		return Float.toString(util.calcEt(end, start, millisec));
	}
}