package net.teraware.model.interf;

import java.sql.SQLException;
import java.util.Collection;

public interface IModel<T extends IBean> {
	public void doSave(T bean) throws SQLException;

	public boolean doDelete(int objectId) throws SQLException;

	public T doRetrieveByKey(int objectId) throws SQLException;

	public Collection<T> doRetrieveAll(String order) throws SQLException;
}
