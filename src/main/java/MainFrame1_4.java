import javax.swing.*;
import java.awt.*;

public class MainFrame1_4 extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContentPanel;

    public MainFrame1_4() {
        setTitle(CommonUtils.TITLE);
        setSize(560, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主内容面板（使用CardLayout）
        mainContentPanel = new JPanel(cardLayout);

        // 创建各个面板
        HomePanel homePanel = new HomePanel(this);
        RandomNamePanel namePanel = new RandomNamePanel(this);
        RandomBirthdayPanel birthdayPanel = new RandomBirthdayPanel(this);

        // 添加面板到主内容面板
        mainContentPanel.add(homePanel, "home");
        mainContentPanel.add(namePanel, "name");
        mainContentPanel.add(birthdayPanel, "birthday");

        // 版本号
        JPanel basicInfoPanel = CommonUtils.createPanel(new GridLayout(1, 2),0);
        JLabel versionLabel = new JLabel(CommonUtils.VERSION, JLabel.LEFT);
        JLabel authorLabel = new JLabel(CommonUtils.AUTHOR, JLabel.RIGHT);
        versionLabel.setFont(CommonUtils.BASIC_INFO);
        authorLabel.setFont(CommonUtils.BASIC_INFO);

        basicInfoPanel.add(versionLabel);
        basicInfoPanel.add(authorLabel);

        // 主布局
        JPanel mainPanel = CommonUtils.createPanel(new BorderLayout(10, 20), 20);
        mainPanel.add(mainContentPanel, BorderLayout.CENTER);
        mainPanel.add(basicInfoPanel, BorderLayout.SOUTH);

        add(mainPanel);
        showHomePanel();
    }

    public void showHomePanel() {
        cardLayout.show(mainContentPanel, "home");
    }

    public void showNamePanel() {
        cardLayout.show(mainContentPanel, "name");
    }

    public void showBirthdayPanel() {
        cardLayout.show(mainContentPanel, "birthday");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame1_4 frame = new MainFrame1_4();
            frame.setVisible(true);
        });
    }
}

class HomePanel extends JPanel {
    public HomePanel(MainFrame1_4 mainFrame) {
        setLayout(new BorderLayout());

        // 标题
        JLabel titleLabel = new JLabel("身份生成器", JLabel.CENTER);
        titleLabel.setFont(CommonUtils.TITLE_FONT);
        add(titleLabel, BorderLayout.NORTH);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JButton nameButton = CommonUtils.menuButton("随机姓名", mainFrame::showNamePanel);
        JButton birthdayButton = CommonUtils.menuButton("随机生日", mainFrame::showBirthdayPanel);

        buttonPanel.add(nameButton);
        buttonPanel.add(birthdayButton);
        add(buttonPanel, BorderLayout.CENTER);
    }
}