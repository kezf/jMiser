package org.miser.extra.tokenizer.engine.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

import org.miser.core.util.StringUtil;
import org.miser.extra.tokenizer.TokenizerEngine;
import org.miser.extra.tokenizer.Result;

/**
 * Jieba分词引擎实现<br>
 * 项目地址：https://github.com/huaban/jieba-analysis
 * 
 * @author Oliver
 *
 */
public class JiebaEngine implements TokenizerEngine {

	private final JiebaSegmenter jiebaSegmenter;
	private final SegMode mode;
	
	/**
	 * 构造
	 */
	public JiebaEngine() {
		this(SegMode.SEARCH);
	}
	
	/**
	 * 构造
	 * 
	 * @param mode 模式{@link SegMode}
	 */
	public JiebaEngine(SegMode mode) {
		this.jiebaSegmenter = new JiebaSegmenter();
		this.mode = mode;
	}

	@Override
	public Result parse(CharSequence text) {
		return new JiebaResult(jiebaSegmenter.process(StringUtil.str(text), mode));
	}

}
