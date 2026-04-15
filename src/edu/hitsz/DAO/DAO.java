package edu.hitsz.DAO;

import java.util.List;

public interface DAO {
    void doAdd(Record record);

    List<Record> getAllRecord();

}
