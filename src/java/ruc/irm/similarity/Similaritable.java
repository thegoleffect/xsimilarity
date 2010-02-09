package ruc.irm.similarity;

/**
 * 可以计算相似度的接口
 * 
 * <p>
 * Organization: Knowledge Engeering Laboratory, IRM, Renmin University of China
 * </p>
 * 
 * @author <a href="xiat@ruc.edu.cn">xiatian</a>
 * @version 1.0
 */
public interface Similaritable {
	/**
	 * 计算两个字符串的相似度，对于句子来说，计算的是句子相似度，对于词语则计算词语的相似度
	 * @param item1 参与相似度计算的第一个字符串
	 * @param item2 参与相似度计算的第二个字符串
	 * @return
	 */
	public double getSimilarity(String item1, String item2); 
}
