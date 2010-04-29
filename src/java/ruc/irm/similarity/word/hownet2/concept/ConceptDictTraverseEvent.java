package ruc.irm.similarity.word.hownet2.concept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ruc.irm.similarity.util.TraverseEvent;


/**
 * 实现遍历加载概念信息到概念表中, 概念词典的组织以知网导出的格式为标准，格式如下：<br/>
 * 阿斗                	N    	human|人,ProperName|专,past|昔<br/>
 * 阿爸                	N    	human|人,family|家,male|男<br/>
 * 即： &lt;概念&gt; &lt;空格或者跳格&gt; &lt;词性&gt; &lt;空格或者跳格&gt; &lt;定义&gt; &lt;空格或者跳格&gt; &lt;例子&gt;"
 * <br/>
 * 概念保存到数组中，没有保存到Map中，可以降低对内存空间的使用
 * 
 * @author <a href="mailto:iamxiatian@gmail.com">夏天</a>
 * @organization 中国人民大学信息资源管理学院 知识工程实验室
 */
public class ConceptDictTraverseEvent implements TraverseEvent<String> {
	private List<Concept> conceptList = null;
	
	public ConceptDictTraverseEvent(){
		conceptList = new ArrayList<Concept>();
	}
	
	public Concept[] getConcepts(){
		Concept[] concepts = conceptList.toArray(new Concept[conceptList.size()]);
		Arrays.sort(concepts);
		return concepts;
	}
	
	/**
	 * 读取概念词典中的一行，并进行解析处理
	 */
	public boolean visit(String line) {
		String word = null;
		String pos = null;
		String define = null;
		char ch;
		
		//第一行是//开始的，认为是注释行
		if(line.startsWith("//")){
			return true;
		}
		
		int lastPosition = 0;	//最近一次处理内容的有意义的开始位置
		int processFlag = 0;	//当前处理部分的标志 0：处理word； 1：词性；2：定义；3：例子
		//解析出一行中的概念各项数据		
		for (int position = 0; position < line.length(); position++) {
			ch = line.charAt(position);
			
			if ((ch == ' ') || (ch == '\t') || (position==(line.length()-1))) {
				String item = line.substring(lastPosition, (position==(line.length()-1))?(position+1):position);
				switch(processFlag){				
				case 0:
					word = item;
					processFlag++;
					break;
				case 1:
					pos = item;
					processFlag++;
					break;
				case 2:
					define = item;
					processFlag++;
					break;
				case 3:
					//skip example
					break;
				}				
				
				for( ;(position < line.length()); position++){
					ch = line.charAt(position);
					if ((ch != ' ') && (ch != '\t')) {
						lastPosition = position;
						break;
					}
				}
					
			}
		}
		
		conceptList.add(new TxtConcept(word, pos, define));
		return true;
	}
		
}
