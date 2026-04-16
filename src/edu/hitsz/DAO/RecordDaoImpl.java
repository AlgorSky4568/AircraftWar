package edu.hitsz.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements DAO{
    private List<Record> records;

    public RecordDaoImpl(){
        records = new ArrayList<Record>();
    }

    @Override
    public List<Record> getAllRecord() {
        return records;
    }

    public void fileWrite(Record record){
        try{
            FileWriter writer = new FileWriter("Records.txt",true);
            writer.write(record.getScore() + " " + record.getName() + " " + record.getTime() + "\n");

            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void doAdd(Record record) {
        records.add(record);

    }


}
