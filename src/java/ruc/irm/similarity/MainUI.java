package ruc.irm.similarity;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import ruc.irm.similarity.sentence.SegmentProxy;
import ruc.irm.similarity.sentence.SentenceSimilarityUI;
import ruc.irm.similarity.word.WordSimlarityUI;

public class MainUI extends JFrame {

    private static final long serialVersionUID = 85744461208L;

    public MainUI() {
        this.setTitle("相似度计算演示程序");
        this.setSize(400, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ////////////////////////////////////
        //add menu
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        fileMenu.add(new JMenuItem("Exit"));

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpMenu.add(new JMenuItem("Help"));
       
        Container contentPane = this.getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("词语", WordSimlarityUI.createPanel());
        tabbedPane.add("句子", SentenceSimilarityUI.createPanel());
        //tabbedPane.add("文本", WordSimlarityUI.createPanel());
        tabbedPane.add("词法分析", SegmentProxy.createPanel());
        JScrollPane scrollPane = new JScrollPane(tabbedPane);
        contentPane.add(scrollPane);        
    }    
    
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MainUI w = new MainUI();
                w.setVisible(true);
            }
        });
    }


}
