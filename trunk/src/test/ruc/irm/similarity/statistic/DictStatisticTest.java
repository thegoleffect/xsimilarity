package ruc.irm.similarity.statistic;

import junit.framework.TestCase;

public class DictStatisticTest extends TestCase {
    public void testCount(){
        DictStatistic ds = new DictStatistic();
        ds.testFromXml("./db/coredict.xml.gz", true);
    }
}
