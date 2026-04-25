package edu.hitsz.DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecordDaoImpl implements DAO{
    private List<Record> records;
    private String filePath = "Records.txt";

    public RecordDaoImpl(){
        records = new ArrayList<Record>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { //读取文件中的所有记录，存到现在的records中
            String line;
            while ((line = br.readLine()) != null) {
                // 跳过空行
                if (line.trim().isEmpty()) {
                    continue;
                }
                // 按空格分割，并去除开头/结尾多余空格
                String[] fields = line.trim().split("\\s+");
                Record record = new Record(Integer.parseInt(fields[0]),fields[1],fields[2]);
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Record> getAllRecord() {
        return records;
    }

    @Override
    public void fileWrite() { //写入Records.txt文件中，采用覆写才对，因为可能会有删除操作，而追加无法改变被删除的数据，
        sortRecords();
        //先清空文件，再写入
        try {
            FileWriter writer1 = new FileWriter(filePath);
            writer1.write("\n");
            writer1.close();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (Record record : records) {
            try {
                FileWriter writer = new FileWriter(filePath,true);
                writer.write(record.getScore() + " " + record.getName() + " " + record.getTime() + "\n");

                writer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void sortRecords() {
        Collections.sort(records, (r1, r2) -> Integer.compare(r2.getScore(), r1.getScore()));
        records.sort(Comparator.comparingInt(p -> p.getScore()));  // 更简洁
    }

    @Override
    public void printRecords() {
        for(Record record : records){
            System.out.println(record.getScore() + " " + record.getName() + " " + record.getTime());
        }
    }

    @Override
    public void doAdd(Record record) {
        records.add(record);
        fileWrite();
    }



    //删除，待实现，但现在的问题是如何删除，根据什么删除

}
