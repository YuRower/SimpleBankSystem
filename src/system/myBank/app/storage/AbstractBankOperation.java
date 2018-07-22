package system.myBank.app.storage;

public abstract class AbstractBankOperation <T>{
	public abstract void addInfo(T obj);
	public abstract void removeInfo(int index);
	public abstract int searchInfoID(T obj);


}
