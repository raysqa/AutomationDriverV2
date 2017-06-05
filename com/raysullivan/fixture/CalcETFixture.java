package raysullivan.fixture;

import raysullivan.operation.AdUtil;


//import fit.ColumnFixture;

public class CalcETFixture {
	private long end, start;
	private int millisec = 1000;
	private AdUtil util = new AdUtil();

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