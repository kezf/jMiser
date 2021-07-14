package org.miser.bloomfilter.filter;

import org.miser.core.util.HashUtil;

public class RSFilter extends AbstractFilter {
	private static final long serialVersionUID = 1L;

	public RSFilter(long maxValue, int machineNum) {
		super(maxValue, machineNum);
	}

	public RSFilter(long maxValue) {
		super(maxValue);
	}

	@Override
	public long hash(String str) {
		return HashUtil.rsHash(str) % size;
	}

}
