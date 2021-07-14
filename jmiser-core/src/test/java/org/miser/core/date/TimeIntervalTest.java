package org.miser.core.date;

import org.miser.core.lang.Console;
import org.miser.core.thread.ThreadUtil;
import org.junit.Test;

public class TimeIntervalTest {
	@Test
	public void intervalGroupTest(){
		final TimeInterval timer = new TimeInterval();
		timer.start("1");
		ThreadUtil.sleep(800);
		timer.start("2");
		ThreadUtil.sleep(900);


		Console.log("Timer 1 took {} ms", timer.intervalMs("1"));
		Console.log("Timer 2 took {} ms", timer.intervalMs("2"));
	}
}
