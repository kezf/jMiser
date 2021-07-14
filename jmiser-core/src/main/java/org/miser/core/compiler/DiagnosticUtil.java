package org.miser.core.compiler;

import java.util.List;
import java.util.stream.Collectors;

import javax.tools.DiagnosticCollector;

/**
 * 诊断工具类
 *
 * @author Oliver
 * 
 */
public class DiagnosticUtil {

	/**
	 * 获取{@link DiagnosticCollector}收集到的诊断信息，以文本返回
	 *
	 * @param collector {@link DiagnosticCollector}
	 * @return 诊断消息
	 */
	public static String getMessages(DiagnosticCollector<?> collector) {
		final List<?> diagnostics = collector.getDiagnostics();
		return diagnostics.stream().map(String::valueOf).collect(Collectors.joining(System.lineSeparator()));
	}
}
