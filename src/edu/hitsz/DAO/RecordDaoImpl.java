package edu.hitsz.DAO;

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

    @Override
    public void doAdd(Record record) {
        records.add(record);
    }
    
}
