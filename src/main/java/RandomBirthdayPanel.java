import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class RandomBirthdayPanel extends JPanel {
    private static final Random random = new Random();
    private final JTextArea resultArea;
    private final MainFrame1_4 mainFrame;

    public RandomBirthdayPanel(MainFrame1_4 mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 生成数量
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JSpinner countSpinner = CommonUtils.createNumberSpinner(1, 100, 1);

        countPanel.add(new JLabel("数量: "));
        countPanel.add(countSpinner);

        // 星座表
        JButton zodiacButton = CommonUtils.createButton("星座表", this::showZodiacChart);

        // 控制面板
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(countPanel);
        controlPanel.add(zodiacButton);

        // 输出面板
        resultArea = CommonUtils.createTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(CommonUtils.createButton("清空", () -> resultArea.setText("")));
        buttonPanel.add(CommonUtils.createButton("生成", () -> generateBirthdays((int)countSpinner.getValue())));
        buttonPanel.add(CommonUtils.createButton("返回", mainFrame::showHomePanel));

        // 布局
        add(controlPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void generateBirthdays(int count) {
        for (int i = 0; i < count; i++) {
            int month = generateRandomMonth();
            int day = generateRandomDay(month);
            resultArea.append(String.format("%02d月%02d日\n", month, day));
        }
    }

    private JDialog zodiacDialog;

    private void showZodiacChart() {
        if (zodiacDialog == null) {
            zodiacDialog = new JDialog(mainFrame, "星座日期表", false);
            zodiacDialog.setSize(220, 300);
            zodiacDialog.setLocationRelativeTo(mainFrame);

            String[] zodiacs = {
                    "白羊座: 03月21日-04月19日",
                    "金牛座: 04月20日-05月20日",
                    "双子座: 05月21日-06月21日",
                    "巨蟹座: 06月22日-07月22日",
                    "狮子座: 07月23日-08月22日",
                    "处女座: 08月23日-09月22日",
                    "天秤座: 09月23日-10月23日",
                    "天蝎座: 10月24日-11月22日",
                    "射手座: 11月23日-12月21日",
                    "摩羯座: 12月22日-01月19日",
                    "水瓶座: 01月20日-02月18日",
                    "双鱼座: 02月19日-03月20日"
            };

            JList<String> zodiacList = new JList<>(zodiacs);
            zodiacList.setFont(CommonUtils.TEXT_FONT);

            zodiacDialog.add(new JScrollPane(zodiacList));
            zodiacDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    zodiacDialog = null;
                }
            });
        }

       zodiacDialog.setVisible(true);
       zodiacDialog.toFront();
    }

    private static int generateRandomMonth() {
        return random.nextInt(12) + 1;
    }

    private static int generateRandomDay(int month) {
        int maxDay;
        switch (month) {
            case 2:
                maxDay = 28;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            default:
                maxDay = 31;
                break;
        };
        return random.nextInt(maxDay) + 1;
    }

    public MainFrame1_4 getMainFrame() {
        return mainFrame;
    }
}