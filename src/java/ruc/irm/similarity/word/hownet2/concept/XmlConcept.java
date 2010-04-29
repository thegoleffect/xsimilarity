package ruc.irm.similarity.word.hownet2.concept;

/**
 * XML格式存储的概念，其形式如下：
 * <pre>
 * &lt;concept>
 *	&lt;word type="1" pos="V">拔</word>
 *	&lt;meaning en="associate" zh="交往" />
 *	&lt;meaning en="turn" zh="扭转" relation="means"/>
 *	&lt;meaning en="tool" zh="用具" symbol="#"/>
 * &lt;/concept>
 * </pre>
 * 其中： type为0，表示为虚词，type为1表示为实词，pos表示词性<br/>
 * meaning表示词语的一条意义，en表示英文意义，zh表示中文意义<br/>
 * relation表示为关系义原<br/>
 * symbol为符号义原<br/>
 * 关系义原和符号义原不应该同时存在
 * 
 * @author <a href="mailto:iamxiatian@gmail.com">夏天</a>
 * @organization 中国人民大学信息资源管理学院 知识工程实验室
 */
public class XmlConcept extends Concept {
	
}
