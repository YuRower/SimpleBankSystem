package system.myBank.app.utill;

public class Regexp {
	public static final String NUMBER_PATTERN = "^[0-9]";
	public static final String ACCOUNT_PATTERN = "^\\d+$";
	public static final String NAME_PATTERN = "^[a-zA-Z\\s]+";
	public static final String PHONE_PATTERN = "\\d{10}";
	public static final String ADDRESS_PATTERN ="^[a-zA-Z0-9]";
	public static final String EMAIL_PATTERN ="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
	public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	public static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})";





}
