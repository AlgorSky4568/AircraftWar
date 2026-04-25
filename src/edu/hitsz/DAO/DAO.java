package edu.hitsz.DAO;

import java.util.List;

public interface DAO {
    void doAdd(Record record); //添加新纪录

    List<Record> getAllRecord();

}
