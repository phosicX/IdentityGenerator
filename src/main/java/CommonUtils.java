import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class CommonUtils {
    public static final Random random = new Random();

    public static final String TITLE = "身份生成器 - Identity Generator";
    public static final String VERSION = "Ver 1.4";
    public static final String AUTHOR = "created by ‘phosicX'";

    public static final Font TITLE_FONT = new Font("Microsoft YaHei", Font.BOLD, 24);
    public static final Font MENU_FONT = new Font("等线", Font.PLAIN, 16);
    public static final Font BUTTON_FONT = new Font("Microsoft YaHei", Font.PLAIN, 12);
    public static final Font TEXT_FONT = new Font("等线", Font.PLAIN, 14);
    public static final Font BASIC_INFO = new Font("宋体", Font.ITALIC, 10);


    //窗口
    public static JFrame createWindow(JFrame parent, String title, int width, int height) {
        JFrame window = new JFrame(title);
        window.setSize(width, height);
        window.setLocationRelativeTo(parent);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return window;
    }

    //主菜单
    public static JButton menuButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(MENU_FONT);
        button.addActionListener(e -> action.run());
        return button;
    }

    //按钮
    public static JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFont(BUTTON_FONT);
        button.addActionListener(e -> action.run());
        return button;
    }

    //面板
    public static JPanel createPanel(LayoutManager layout, int padding) {
        JPanel panel = new JPanel(layout);
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        return panel;
    }

    //输出框
    public static JTextArea createTextArea(int rows, int columns) {
        JTextArea textArea = new JTextArea(rows, columns);
        textArea.setEditable(false);
        textArea.setFont(TEXT_FONT);
        return textArea;
    }

    //随机数
    public static int generateRandomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    //微调器
    public static JSpinner createNumberSpinner(int min, int max, int initial) {
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(initial, min, max, 1));
        spinner.setPreferredSize(new Dimension(60, 20));
        return spinner;
    }
}
