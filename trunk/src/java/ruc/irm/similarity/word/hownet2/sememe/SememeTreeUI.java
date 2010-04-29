package ruc.irm.similarity.word.hownet2.sememe;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.zip.GZIPInputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * 用于显示义原层次树的Tree组件，仅仅为了管理和查看方便，与相似度计算算法无关。 所有内容读自sememe.xml.gz压缩文件。
 * 
 * @author xiatian
 * 
 */
public class SememeTreeUI extends JFrame {
  private static final long serialVersionUID = 3270193057395104087L;
	
	public static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
    try {
    	DefaultMutableTreeNode root = load();
    	JTree jtree = new JTree(root);
    	JScrollPane centerScrollPane = new JScrollPane(jtree);
    	panel.add(centerScrollPane, BorderLayout.CENTER);
    } catch (IOException e) {	    
	    e.printStackTrace();
    }
		return panel;		
	}

	private static void createNodes(Multimap<String, Sememe> sememes, DefaultMutableTreeNode top, String parentId) {
		Collection<Sememe> children = sememes.get(parentId);
		for(Sememe child:children){
			String text = child.getEnWord() + "|" + child.getCnWord();
			if(child.getDefine()!=null){
				 text +=  " " + child.getDefine();
			}
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(text);
			top.add(childNode);
			createNodes(sememes, childNode, child.getId());
		}
	}
	
	/**
	 * 加载义原到Multimap中，并创建树的节点
	 * @return
	 * @throws IOException
	 */
	private static DefaultMutableTreeNode load() throws IOException{
		/** 
		 * 存放parentId和该parentId所隶属的义原，即Key为parentId，value为子义原
		 */
		Multimap<String, Sememe> sememes = ArrayListMultimap.create();

		String sememeFile = SememeTreeUI.class.getPackage().getName().replaceAll("\\.", "/") + "/sememe.xml.gz";
		InputStream input = SememeTreeUI.class.getClassLoader().getResourceAsStream(sememeFile);
		input = new GZIPInputStream(input);

		System.out.println("loading sememe dictionary...");
		long time = System.currentTimeMillis();
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(input);

			while (xmlEventReader.hasNext()) {
				XMLEvent event = xmlEventReader.nextEvent();

				if (event.isStartElement()) {
					StartElement startElement = event.asStartElement();
					if (startElement.getName().toString().equals("sememe")) {
						String en = startElement.getAttributeByName(QName.valueOf("en")).getValue();
						String cn = startElement.getAttributeByName(QName.valueOf("cn")).getValue();
						String id = startElement.getAttributeByName(QName.valueOf("id")).getValue();						
						Attribute attr = startElement.getAttributeByName(QName.valueOf("define"));
						String define = (attr==null?null:attr.getValue());
						
						Sememe sememe = new Sememe(id, en, cn, define);
						int pos = id.lastIndexOf("-");
						String parentId = "root";
						if(pos>0){
							parentId = id.substring(0, pos);
						}
						sememes.put(parentId, sememe);
					}
				}
			}
			input.close();
		} catch (Exception e) {
			throw new IOException(e);
		}
		System.out.println("sememe dictionary load completely. time elapsed: " + time);
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("知网义原层次关系树");
		createNodes(sememes, top, "root");
		return top;
	}
	

	public SememeTreeUI() {
		this.setTitle("义原树演示程序");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		 this.getContentPane().add(createPanel());
		 this.pack();
	}
	
	public static void main(String[] args) {
	  new SememeTreeUI().setVisible(true);
  }

}
