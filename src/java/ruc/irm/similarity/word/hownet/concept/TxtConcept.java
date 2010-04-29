package ruc.irm.similarity.word.hownet.concept;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
 * @author xiatian
 * @deprecated
 */
public class TxtConcept extends Concept {

	public TxtConcept(String word, String pos, String def) {		
		this.word = word;
		this.pos = pos;
		this.define = (def == null) ? "" : def.trim();
		
		// 虚词用{***}表示
		if (define.length() > 0 
				&& define.charAt(0) == '{'
				&& define.charAt(define.length() - 1) == '}'){
			this.bSubstantive = false;
		} else {
			this.bSubstantive = true;
		}

		parseDefine();
	}

	/**
	 * 处理定义，把定义分为第一基本义元、其他基本义元、关系义元和符号义元四类
	 */
	private void parseDefine() {
		List<String> secondList = new ArrayList<String>(); 		//其他基本义原
		List<String> relationList = new ArrayList<String>(); 	//关系义原
		List<String> symbolList = new ArrayList<String>(); 		//符号义原
		
		String tokenString = this.define;

		//如果不是实词，则处理“{}”中的内容
		if (!this.bSubstantive) {			
			tokenString = define.substring(1, define.length() - 1);
		}
		
		StringTokenizer token = new StringTokenizer(tokenString, ",", false);

		// 第一个为第一基本义元
		if (token.hasMoreTokens()) {
			this.mainSememe = token.nextToken();
		}
		
		main_loop: while (token.hasMoreTokens()) {
			String item = token.nextToken();
			if (item.equals("")) continue;
			
			// 先判断是否为符号义元
			String symbol = item.substring(0, 1);		
			for(int i=0;i< Symbol_Descriptions.length;i++){
		    	if(symbol.equals( Symbol_Descriptions[i][0])){
		            symbolList.add(item);		            
		            continue main_loop;
		    	}
			}
			
			//如果不是符号义元，则进一步判断是关系义元还是第二基本义元, 带有“=”表示关系义原
			if (item.indexOf('=') > 0){
				relationList.add(item);
			} else {
				secondList.add(item);
			}			
		}
		
		this.secondSememes = secondList.toArray(new String[secondList.size()]);
		this.relationSememes = relationList.toArray(new String[relationList.size()]);
		this.symbolSememes = symbolList.toArray(new String[symbolList.size()]);

	}

}
