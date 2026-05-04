package edu.hitsz.Swing;

import edu.hitsz.DAO.DAO;
import edu.hitsz.DAO.Record;
import edu.hitsz.DAO.RecordDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Marks {
    private JPanel mainPanel;
    private JTable table1;
    private JScrollPane scoreTable;
    private JButton deleteRecordButton;
    private JLabel difficultyLabel;
    private JLabel rankingLAbel;

    private final DAO recordDao;
    private final List<Record> records;

    /**
     * @param difficulty 难度标识：0-简单，1-普通，2-困难
     */
    public Marks(int difficulty) {
        // 从数据访问层获取所有记录
        recordDao = new RecordDaoImpl();
        records = recordDao.getAllRecord();
        // 按得分降序排序
        records.sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));

        // 程序化创建所有UI组件，不依赖IntelliJ表单初始化
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // 难度标签
        String difficultyText;
        switch (difficulty) {
            case 0:
                difficultyText = "简单";
                break;
            case 1:
                difficultyText = "普通";
                break;
            case 2:
                difficultyText = "困难";
                break;
            default:
                difficultyText = "未知";
                break;
        }
        difficultyLabel = new JLabel("难度：" + difficultyText);
        difficultyLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 5, 10);
        mainPanel.add(difficultyLabel, gbc);

        // 排行榜标题
        rankingLAbel = new JLabel("排行榜");
        rankingLAbel.setFont(new Font("SansSerif", Font.BOLD, 20));
        rankingLAbel.setForeground(new Color(0, 100, 200));
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 10, 10);
        mainPanel.add(rankingLAbel, gbc);

        // 创建表格（包含初始数据）
        table1 = createRankingTable();

        // 表格放入滚动面板
        scoreTable = new JScrollPane(table1);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 10, 10, 10);
        mainPanel.add(scoreTable, gbc);

        // 删除按钮
        deleteRecordButton = new JButton("删除选中记录");
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 10, 10, 10);
        mainPanel.add(deleteRecordButton, gbc);

        // 删除按钮事件：删除选中的记录并刷新表格
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(mainPanel, "请先选中要删除的记录！", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // 弹出确认对话框
                String playerName = (String) table1.getValueAt(selectedRow, 1);
                int score = (int) table1.getValueAt(selectedRow, 2);
                int confirm = JOptionPane.showConfirmDialog(mainPanel,
                        "确定要删除玩家 \"" + playerName + "\" 得分 " + score + " 的记录吗？",
                        "确认删除", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

                // 根据选中行获取对应的 Record 对象
                Record selectedRecord = records.get(selectedRow);
                // 从数据源删除
                recordDao.doDelete(selectedRecord);
                // 更新本地 records 列表
                records.remove(selectedRecord);
                // 刷新表格
                refreshTable();
            }
        });
    }

    /**
     * 根据 records 列表创建表格
     */
    private JTable createRankingTable() {
        String[] columnNames = {"名次", "玩家名", "得分", "记录时间"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int rank = 1;
        for (Record record : records) {
            Object[] rowData = {
                    rank++,
                    record.getName(),
                    record.getScore(),
                    record.getTime()
            };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        table.setRowHeight(25);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        // 设置为单选模式
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    /**
     * 删除记录后刷新表格显示
     */
    private void refreshTable() {
        // 用新的表格替换旧的
        JTable newTable = createRankingTable();
        scoreTable.setViewportView(newTable);
        table1 = newTable;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
