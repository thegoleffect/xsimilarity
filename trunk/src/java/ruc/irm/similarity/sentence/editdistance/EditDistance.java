package ruc.irm.similarity.sentence.editdistance;

import ruc.irm.similarity.Similaritable;


/**
 * 编辑距离的父类，定义了其中的主要行为
 * @author 夏 天 
 */
public abstract class EditDistance implements Similaritable {
        
    public abstract double getEditDistance(SuperString<? extends EditUnit> S, SuperString<? extends EditUnit> T);    
 
    public double getSimilarity(String s1, String s2){
    	SuperString<WordEditUnit> S = SuperString.createWordSuperString(s1);
    	SuperString<WordEditUnit> T = SuperString.createWordSuperString(s2);
    	
    	return 1-(getEditDistance(S, T))/(Math.max(S.length(), T.length()));
    }
}
