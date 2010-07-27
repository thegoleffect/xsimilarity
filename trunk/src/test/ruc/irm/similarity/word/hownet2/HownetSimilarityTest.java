package ruc.irm.similarity.word.hownet2;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ruc.irm.similarity.word.hownet2.concept.LiuConceptParser;
import ruc.irm.similarity.word.hownet2.concept.XiaConceptParser;

public class HownetSimilarityTest  extends TestCase {
    XiaConceptParser xParser = null;
    LiuConceptParser lParser = null;
    
    @Before
    public void setUp(){
        xParser = XiaConceptParser.getInstance();
        lParser = LiuConceptParser.getInstance();
    }
    
    @Test
    public void testWordSimiarltiy(){
        String word1 = "电动车";
        String word2 = "自行车";
        double x_sim = xParser.getSimilarity(word1, word2);
        double l_sim = lParser.getSimilarity(word1, word2);
        assertTrue(x_sim>l_sim);
        assertTrue(x_sim>0.2);
    }
}
