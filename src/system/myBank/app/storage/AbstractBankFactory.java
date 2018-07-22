package system.myBank.app.storage;

public abstract class AbstractBankFactory<T extends AbstractBankOperation> {
	public abstract T createInstance();
}
