package ruc.irm.similarity.word.hownet.concept;

import ruc.irm.similarity.word.hownet.HownetMeta;


/**
 * 知网的概念表示类 <br/>example和英文部分对于相似度的计算不起作用，考虑到内存开销， 在概念的表示中去掉了这部分数据的对应定义
 * 
 * @author <a href="mailto:iamxiatian@gmail.com">夏天</a>
 * @organization 中国人民大学信息资源管理学院 知识工程实验室
 */
public class Concept implements HownetMeta, Comparable<Concept> {
	/** 中文概念名称 */
	protected String word;
	/** 词性: Part of Speech */
	protected String pos;
	/** 定义 */
	protected String define;

	/** 是否是实词，false表示为虚词, 一般为实词 */
	protected boolean bSubstantive;
	/** 第一基本义原 */
	protected String mainSememe;
	/** 其他基本义原 */
	protected String[] secondSememes;
	/** 关系义元原 */
	protected String[] relationSememes;
	/** 关系符号描述 */
	protected String[] symbolSememes;

	static String[][] Concept_Type = { { "=", "事件" },
			{ "aValue|属性值", "属性值" }, { "qValue|数量值", "数量值" },
			{ "attribute|属性", "属性" }, { "quantity|数量", "数量" },
			{ "unit|", "单位" }, { "%", "部件" } };	

	/**
	 * 获取第一义元
	 * 
	 * @return
	 */
	public String getMainSememe() {
		return mainSememe;
	}
	
	/**
	 * 获取其他基本义元描述
	 * 
	 * @return
	 */
	public String[] getSecondSememes() {
		return secondSememes;
	}

	/**
	 * 获取关系义元描述
	 * 
	 * @return
	 */
	public String[] getRelationSememes() {
		return relationSememes;
	}

	/**
	 * 获取符号义元描述
	 * 
	 * @return
	 */
	public String[] getSymbolSememes() {
		return symbolSememes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name=");
		sb.append(this.word);
		sb.append("; pos=");
		sb.append(this.pos);
		sb.append("; define=");
		sb.append(this.define);
		sb.append("; 第一基本义元:[" + mainSememe);
		
		sb.append("]; 其他基本义元描述:[");
		for(String sem: secondSememes){
			sb.append(sem);
			sb.append(";");
		}

		sb.append("]; [关系义元描述:");
		for(String sem: relationSememes){
			sb.append(sem);
			sb.append(";");
		}

		sb.append("]; [关系符号描述:");
		for(String sem: symbolSememes){
			sb.append(sem);
			sb.append(";");
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 是实词还是虚词
	 * 
	 * @return true:实词；false:虚词
	 */
	public boolean isSubstantive() {
		return this.bSubstantive;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getDefine() {
		return define;
	}

	public void setDefine(String define) {
		this.define = define;
	}

	/**
	 * 获取该概念的类型
	 * 
	 * @return
	 */
	public String getType() {
		for (int i = 0; i < Concept_Type.length; i++) {
			if (define.toUpperCase().indexOf(Concept_Type[i][0].toUpperCase()) >= 0) {
				return Concept_Type[i][1];
			}
		}
		return "普通概念";
	}	
	
	/**
	 * 按照概念的名称进行比较
	 */
	public int compareTo(Concept o) {
		return word.compareTo(o.word);
	}

	//////////////////////////////////////////////
	/**
	 * 方便在parse中比较概念词语加入的方法
	 * @param another
	 * @return
	 */
	public int compareTo(String another){
		return word.compareTo(another);
	}
	
	public boolean equals(String another){
		return word.equals(another);
	}
}