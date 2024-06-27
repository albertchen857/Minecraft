package MCcalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;

public class calculator {
    public Font McTitleFont,McFont;
    {
        try {
            McTitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("./MinecraftAE.ttf")).deriveFont(15f);
            McFont = Font.createFont(Font.TRUETYPE_FONT, new File("./MinecraftAE.ttf")).deriveFont(12f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //箱<盒<组<个 54 27 64
    public JFrame f = new JFrame("物品换算计算器");
    public JPanel card = new JPanel(new CardLayout());
    public JPanel UnitPanel = new JPanel();
    public JPanel inputU = new JPanel();
    public JPanel BlockPanel = new JPanel();
    public JPanel inputB = new JPanel();
    public JPanel GamePanel = new JPanel();
    public JPanel inputG = new JPanel();
    public Box Choice = Box.createHorizontalBox();

    public JLabel title = new JLabel("--MINECRAFT物品换算计算器--");
    //Unitpanel
    public JLabel notice1 = new JLabel("物品单位换算: 64个=1组|27组=1盒|54盒=1箱");
    public JLabel label1 = new JLabel("输入");
    public JButton bt = new JButton("换算");
    public JButton clear = new JButton("清空");
    public JTextField Que = new JTextField(6);
    public JComboBox<String> UnitChoice = new JComboBox<>(new String[]{"个", "组", "盒", "箱"});
    public JTextArea Ans = new JTextArea(6, 12);
    //Blockpanel
    public JLabel noticeB = new JLabel("建材数量计算: 整砖->半砖|整砖->楼梯|1:4分解(木头->木板)|1:9分解(铁块<->铁锭)");
    public JLabel labelB = new JLabel("输入");
    public JButton cb = new JButton("分解");
    public JButton clearB = new JButton("清空");
    public JTextField QueB = new JTextField(6);
    public JComboBox<String> BlockChoice = new JComboBox<>(new String[]{"整转半砖", "整转楼梯", "1:4", "1:9"});
    public JTextArea AnsB = new JTextArea(6, 12);
    //Gamepanel
    public JLabel noticeG = new JLabel("游戏单位计算: 1游戏刻gt=1/2红石刻rt=0.05秒|格/子区块/区块/区域 ");
    public JLabel labelG = new JLabel("输入");
    public JButton tk = new JButton("换算");
    public JButton clearG = new JButton("清空");
    public JTextField QueG = new JTextField(6);
    public JComboBox<String> GameChoice = new JComboBox<>(new String[]{"秒", "游戏刻", "红石刻", "子区块", "区块", "区域"});
    public JTextArea AnsG = new JTextArea(6, 12);
    //Choice
    public JButton UnitMD = new JButton("单位换算模式");
    public JButton BlockMD = new JButton("建材计算模式");
    public JButton GameMD = new JButton("游戏单位模式");

    public static int mode = 0;

    public void main() {
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
        card.setBorder(new EmptyBorder(5,5,5,5));
        title.setFont(McTitleFont);
        f.add(title);
        f.add(card);
        f.add(Choice);
        card.add(UnitPanel, "UnitPanel");
        card.add(BlockPanel, "BlockPanel");
        card.add(GamePanel, "GamePanel");
        f.pack();
        f.setSize(600, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);
        Choice.add(UnitMD);
        Choice.add(BlockMD);
        Choice.add(GameMD);

        CardLayout cardLayout = (CardLayout) card.getLayout();
        cardLayout.show(card, "UnitPanel");

        UnitMD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(card, "UnitPanel");
                //Unit();
            }
        });

        BlockMD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(card, "BlockPanel");
                //Block();
            }
        });

        GameMD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(card, "GamePanel");
                //Block();
            }
        });

        Unit();
        Block();
        Game();
    }

    public void Unit() {
        UnitPanel.setLayout(new BoxLayout(UnitPanel, BoxLayout.Y_AXIS));
        UnitPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        Ans.setFont(McFont);
        inputU.add(label1);
        inputU.add(Que);
        inputU.add(UnitChoice);
        inputU.add(bt);
        inputU.add(clear);
        UnitPanel.add(notice1);
        UnitPanel.add(inputU);
        UnitPanel.add(new JScrollPane(Ans));
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ans.setText(null);
            }
        });
    }

    public void calculate() {
        try {
            int num = Integer.parseInt(Que.getText());
            String unit = (String) UnitChoice.getSelectedItem();
            int rst;
            double ut1,ut2,ut3;
            String apd = "";
            switch (unit) {
                case "个":
                    rst = num;
                    ut1 = (double) Math.round((num/64.0) * 100) / 100;
                    ut2 = (double) Math.round((num/64.0/27.0) * 100) / 100;
                    ut3 = (double) Math.round((num/64.0/27.0/54.0) * 100) / 100;
                    apd = " &"+ut1+"组|"+ut2+"盒|"+ut3+"箱";
                    break;
                case "组":
                    rst = num * 64;
                    ut2 = (double) Math.round((num/64.0/27.0) * 100) / 100;
                    ut3 = (double) Math.round((num/64.0/27.0/54.0) * 100) / 100;
                    apd = " &"+rst+"个|"+ut2+"盒|"+ut3+"箱";
                    break;
                case "盒":
                    rst = num * 64 * 27;
                    ut1 = (double) Math.round((num/64.0) * 100) / 100;
                    ut3 = (double) Math.round((num/64.0/27.0/54.0) * 100) / 100;
                    apd = " &"+rst+"个|"+ut1+"组|"+ut3+"箱";
                    break;
                case "箱":
                    rst = num * 64 * 27 * 54;
                    ut1 = (double) Math.round((num/64.0) * 100) / 100;
                    ut2 = (double) Math.round((num/64.0/27.0) * 100) / 100;
                    apd = " &"+rst+"个|"+ut1+"组|"+ut2+"盒";
                    break;
                default:
                    Ans.append("Unknown unit\n");
                    return;
            }
            if (rst>2000000000|rst<0){
                throw new RuntimeException();
            }
            int boxes = rst / (54 * 27 * 64);
            rst %= (54 * 27 * 64);
            int cartons = rst / (27 * 64);
            rst %= (27 * 64);
            int groups = rst / 64;
            int individuals = rst % 64;

            Ans.append("输入:" + num + unit + "--结果: " + boxes + " 箱 " + cartons + " 盒 " + groups + " 组 " + individuals + " 个"+apd+"\n");
        } catch (Exception e) {
            Ans.append("你妈逼给我好好输入!(输入必须是数字)\n");
        }
    }

    public void Block() {
        BlockPanel.setLayout(new BoxLayout(BlockPanel, BoxLayout.Y_AXIS));
        BlockPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        AnsB.setFont(McFont);
        inputB.add(labelB);
        inputB.add(QueB);
        inputB.add(BlockChoice);
        inputB.add(cb);
        inputB.add(clearB);
        BlockPanel.add(noticeB);
        BlockPanel.add(inputB);
        BlockPanel.add(new JScrollPane(AnsB));
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Bcalculate();
            }
        });
        clearB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnsB.setText(null);
            }
        });
    }

    public void Bcalculate() {
        try {
            int num = Integer.parseInt(QueB.getText());
            String unit = (String) BlockChoice.getSelectedItem();
            int rst, rst2, rem;
            switch (unit) {
                case "整转半砖":
                    rst = num / 3 * 6;
                    rem = num - (rst/2);
                    AnsB.append("输入:" + num + "(整砖->半砖)--结果:" + rst + "个半砖余" + rem + "个整砖\n");
                    break;
                case "整转楼梯":
                    rst = num / 6 * 4;
                    rem = num - (rst*3/2);
                    AnsB.append("输入:" + num + "(整砖->楼梯)--结果:" + rst + "个楼梯余" + rem + "个整砖\n");
                    break;
                case "1:4":
                    rst = num * 4;
                    rst2 = num / 4;
                    rem = num - (rst2*4);
                    AnsB.append("输入:" + num + "个--结果:" + rst + "个(1:4)或" + rst2 + "个余" + rem + "个(4:1)\n");
                    break;
                case "1:9":
                    rst = num * 9;
                    rst2 = num / 9;
                    rem = num - (rst2*9);
                    AnsB.append("输入:" + num + "个--结果:" + rst + "个(1:9)或" + rst2 + "个余" + rem + "个(9:1)\n");
                    break;
                default:
                    AnsB.append("Unknown unit\n");
                    return;
            }

        } catch (Exception e) {
            AnsB.append("你妈逼给我好好输入!(输入必须是数字)\n");
        }

    }

    public void Game() {
        GamePanel.setLayout(new BoxLayout(GamePanel, BoxLayout.Y_AXIS));
        GamePanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        AnsG.setFont(McFont);
        inputG.add(labelG);
        inputG.add(QueG);
        inputG.add(GameChoice);
        inputG.add(tk);
        inputG.add(clearG);
        GamePanel.add(noticeG);
        GamePanel.add(inputG);
        GamePanel.add(new JScrollPane(AnsG));
        tk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Gcalculate();
            }
        });
        clearG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnsG.setText(null);
            }
        });
    }

    public void Gcalculate() {
        try {
            int num = Integer.parseInt(QueG.getText());
            String unit = (String) GameChoice.getSelectedItem();
            int rst, rst2;
            switch (unit) {
                case "秒", "游戏刻", "红石刻" -> {
                    if(num > 107000000) throw new RuntimeException();
                }
                case "子区块" -> {
                    if(num > 524287) throw new RuntimeException();
                }
                case "区块" -> {
                    if(num > 21845) throw new RuntimeException();
                }
                case "区域" -> {
                    if(num > 21) throw new RuntimeException();
                }
                default -> {
                    // 处理其他情况
                }
            }

            switch (unit) {
                case "秒":
                    rst = num * 20;
                    rst2 = num * 10;
                    AnsG.append("输入:" + num + "s--结果:" + rst + "gt & " + rst2 + "rt\n");
                    break;
                case "游戏刻":
                    rst = num / 20;
                    rst2 = num / 2;
                    AnsG.append("输入:" + num + "gt--结果:" + rst + "s & " + rst2 + "rt\n");
                    break;
                case "红石刻":
                    rst = num / 10;
                    rst2 = num * 2;
                    AnsG.append("输入:" + num + "rt--结果:" + rst + "s & " + rst2 + "gt\n");
                    break;
                case "子区块":
                    rst = num * 16 * 16 * 16;
                    AnsG.append("输入:" + num + "子区块--结果:" + rst + "格\n");
                    break;
                case "区块":
                    rst = num * 16 * 16 * 16;
                    AnsG.append("输入:" + num + "区块--结果:" + rst*24 + "("+ rst*16+ ")格 & " + num*24 + "("+num*16+")子区块\n");
                    break;
                case "区域":
                    rst = num * 512 * 512 * 16;
                    rst2 = num * 32 * 32;
                    AnsG.append("输入:" + num + "区块--结果:" + rst*24 + "("+ rst*16+ ")格 & " + rst2 + "区块 & "+ rst2*24 + "("+rst2*16+")子区块\n");
                    break;
                default:
                    AnsG.append("Unknown unit\n");
                    return;
            }

        } catch (Exception e) {
            AnsG.append("你妈逼给我好好输入!(输入必须是数字)\n");
        }

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new calculator().main();
    }
}
