package edu.hitsz.Swing;

import edu.hitsz.DAO.DAO;
import edu.hitsz.DAO.Record;
import edu.hitsz.DAO.RecordDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Marks {
    private JTable table1;
    private JScrollPane scoreTable;
    private JButton deleteRecordButton;
    private JLabel difficultyLabel;
    private JLabel rankingLAbel;

    /**
     * @param difficulty 难度标识：0-简单，1-普通，2-困难
     */
    public Marks(int difficulty) {
        // 根据难度标识设置难度文本
        String difficultyText;
        switch (difficulty) {
            case 0:
                difficultyText = "Easy";
                break;
            case 1:
                difficultyText = "Common";
                break;
            case 2:
                difficultyText = "Difficult";
                break;
            default:
                difficultyText = "Unknown";
                break;
        }
        difficultyLabel.setText("难度：" + difficultyText);

        // 初始化表格模型，设置四列：名次、玩家名、得分、记录时间
        String[] columnNames = {"名次", "玩家名", "得分", "记录时间"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 表格不可编辑
            }
        };

        // 从数据访问层获取所有记录
        DAO recordDao = new RecordDaoImpl();
        List<Record> records = recordDao.getAllRecord();

        // 按得分降序排序
        records.sort((r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));

        // 填充表格数据
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

        // 设置表格模型
        table1.setModel(model);

        // 设置表格列宽
        table1.getColumnModel().getColumn(0).setPreferredWidth(50);  // 名次
        table1.getColumnModel().getColumn(1).setPreferredWidth(100); // 玩家名
        table1.getColumnModel().getColumn(2).setPreferredWidth(80);  // 得分
        table1.getColumnModel().getColumn(3).setPreferredWidth(120); // 记录时间
    }
}
