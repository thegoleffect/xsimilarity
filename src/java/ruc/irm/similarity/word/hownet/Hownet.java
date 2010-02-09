package ruc.irm.similarity.word.hownet;

import java.io.IOException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ruc.irm.similarity.Similaritable;
import ruc.irm.similarity.word.hownet.concept.ConceptParser;
import ruc.irm.similarity.word.hownet.concept.MyConceptParser;
import ruc.irm.similarity.word.hownet.sememe.MySememeParser;
import ruc.irm.similarity.word.hownet.sememe.SememeParser;

/**
 * Hownet的主控制类, 通过知网的概念和义原及其关系计算汉语词语之间的相似度. 
 * 相似度的计算理论参考论文《汉语词语语义相似度计算研究》
 * 
 * <p>
 * Organization: Knowledge engeering laboratory, IRM, Renmin university of China
 * </p>
 * 
 * @author <a href="xiat@ruc.edu.cn">xiatian</a>
 * @version 1.0
 * 
 * @see ke.commons.similarity.Similariable
 */
public class Hownet implements Similaritable{	
	/** the logger */
	private static final Log LOG = LogFactory.getLog(Hownet.class);
	/** 知网的单例 */
	private static Hownet instance = null;
	
	private ConceptParser conceptParser = null;
	
	private Hownet(){
		try {
			SememeParser sememeParser = new MySememeParser();
			conceptParser = new MyConceptParser(sememeParser);
		} catch (IOException e) {			
			e.printStackTrace();
			LOG.error(e);
		}
	}
	
	/**
	 * 单例获取知网对象
	 * @return
	 */
	public static Hownet instance(){
		if(null == instance){
			instance = new Hownet();
		}
		
		return instance;
	}
	
	/**
	 * 获取概念解析器
	 * @return
	 */
	public ConceptParser getConceptParser(){
		return conceptParser;
	}
		
	public double getSimilarity(String item1, String item2) {		
		return conceptParser.getSimilarity(item1, item2);
	}
		
}
