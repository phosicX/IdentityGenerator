import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomNamePanel extends JPanel {
    private static final Random random = new Random();
    private final JTextArea resultArea;
    private final MainFrame1_4 mainFrame;
    private JFrame nameWindow;

    public RandomNamePanel(MainFrame1_4 mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 语言选择面板
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup languageGroup = new ButtonGroup();
        JRadioButton chineseButton = new JRadioButton("中文名");
        JRadioButton englishButton = new JRadioButton("英文名");
        languageGroup.add(chineseButton);
        languageGroup.add(englishButton);
        chineseButton.setSelected(true);
        englishButton.addActionListener(e -> {
            if (englishButton.isSelected()) {
                JOptionPane.showMessageDialog(nameWindow, "该功能暂未实现，敬请期待", "提示", JOptionPane.WARNING_MESSAGE);
                chineseButton.setSelected(true);
            }
        });

        languagePanel.add(new JLabel("语言: "));
        languagePanel.add(chineseButton);
        languagePanel.add(englishButton);

        // 姓氏输入面板
        JPanel surnamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField surnameField = new JTextField(7);

        surnamePanel.add(new JLabel("姓氏: "));
        surnamePanel.add(surnameField);

        // 性别选择面板
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup genderGroup = new ButtonGroup();
        JRadioButton maleButton = new JRadioButton("男");
        JRadioButton femaleButton = new JRadioButton("女");
        JRadioButton randomGenderButton = new JRadioButton("随机");
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(randomGenderButton);
        randomGenderButton.setSelected(true);

        genderPanel.add(new JLabel("性别: "));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(randomGenderButton);

        // 生成模式选择面板
        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup optionGroup = new ButtonGroup();
        JRadioButton fullNameButton = new JRadioButton("生成名字");
        JRadioButton givenNameButton = new JRadioButton("生成字名");
        optionGroup.add(fullNameButton);
        optionGroup.add(givenNameButton);
        fullNameButton.setSelected(true);

        optionPanel.add(new JLabel("模式: "));
        optionPanel.add(fullNameButton);
        optionPanel.add(givenNameButton);

        // 批量生成面板
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JSpinner countSpinner = CommonUtils.createNumberSpinner(1, 100, 1);

        countPanel.add(new JLabel("数量: "));
        countPanel.add(countSpinner);

        // 控制面板
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        controlPanel.add(languagePanel);
        controlPanel.add(surnamePanel);
        controlPanel.add(genderPanel);
        controlPanel.add(optionPanel);
        controlPanel.add(countPanel);

        // 结果区域
        resultArea = CommonUtils.createTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(CommonUtils.createButton("清空", () -> resultArea.setText("")));
        buttonPanel.add(CommonUtils.createButton("生成", () -> {
            String inputSurname = surnameField.getText().trim();
            int count = (int) countSpinner.getValue();

            if (fullNameButton.isSelected()) {          //生成姓名
                for (int i = 0; i < count; i++) {
                    boolean isMale = getSelectedGender(maleButton, femaleButton);
                    String name = generateName(isMale);
                    resultArea.append(name + "\n");
                }
            } else {  //生成字名
                if (inputSurname.isEmpty()) {
                    JOptionPane.showMessageDialog(nameWindow, "请先输入姓氏", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                for (int i = 0; i < count; i++) {
                    boolean isMale = getSelectedGender(maleButton, femaleButton);
                    String givenName = generateGivenName(isMale);
                    String name = String.format("[%s] %s%s", isMale ? "男" : "女", inputSurname, givenName);
                    resultArea.append(name + "\n");
                }
            }
        }));
        buttonPanel.add(CommonUtils.createButton("返回", mainFrame::showHomePanel));

        // 布局
        add(controlPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }


    private boolean getSelectedGender(JRadioButton maleButton, JRadioButton femaleButton) {
        if (maleButton.isSelected()) return true;
        if (femaleButton.isSelected()) return false;
        return random.nextBoolean();
    }

    public static String generateName(boolean isMale) {
        String surname = getRandomSurname();
        String givenName = generateGivenName(isMale);
        return String.format("[%s] %s%s", isMale ? "男" : "女", surname, givenName);
    }

    public static String generateGivenName(boolean isMale) {
        String[] singleChars, doubleChars;
        if (isMale) {
            singleChars = CharacterLibrary.getChineseMaleCharacter();
            doubleChars = CharacterLibrary.getChineseMaleDoubleCharacter();
        } else {
            singleChars = CharacterLibrary.getChineseFemaleCharacter();
            doubleChars = CharacterLibrary.getChineseFemaleDoubleCharacter();
        }

        if (random.nextDouble() < 0.15) {
            return getRandomElement(singleChars);
        } else {
            if (random.nextDouble() < 0.3) {
                return getRandomElement(doubleChars);
            } else {
                return getRandomElement(singleChars) + getRandomElement(singleChars);
            }
        }
    }

    private static String getRandomElement(String[] array) {
        return array[random.nextInt(array.length)];
    }

    private static String getRandomSurname() {
        return getRandomElement(CharacterLibrary.getChineseSurnames());
    }

    public MainFrame1_4 getMainFrame() {
        return mainFrame;
    }
}