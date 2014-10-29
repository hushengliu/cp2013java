package dao;

import java.util.List;

import model.Record;

public interface IRecordDao {
	public int addRecord(Record re);
	public List<Record> getRecords();
	public List<Record> getRecordsByDate(String time);
	public int deleteRecord(String time);
}
