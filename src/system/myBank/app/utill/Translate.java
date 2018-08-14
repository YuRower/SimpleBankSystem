package system.myBank.app.utill;

import java.util.Locale;
import java.util.ResourceBundle;

import system.myBank.app.SignIn;

public class Translate {

	public static void translateMenu_RUS() {
		SignIn s = new SignIn("Bank");

		String language_country = "RU";
		String country = "RU";
		Locale current = new Locale(language_country, country);
		ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
		String s1 = rb.getString("menuCreate");
		String s2 = rb.getString("create");
		String s3 = rb.getString("menuInfo");
		String s4 = rb.getString("info");
		String s5 = rb.getString("menuEdit");
		String s6 = rb.getString("deposit");
		String s7 = rb.getString("withdraw");
		String s8 = rb.getString("history");
		String s9 = rb.getString("transfer");
		String s10 = rb.getString("menuDelete");
		String s11 = rb.getString("delete");
		String s12 = rb.getString("language");
		String s13 = rb.getString("menuTest");
		String s14 = rb.getString("menuChat");
		String s15 = rb.getString("eng_language");
		String s16 = rb.getString("rus_language");
		String s17 = rb.getString("help");

		s.getMenuCreate().setText(s1);
		s.getCreate().setText(s2);
		s.getMenuInfo().setText(s3);
		s.getInfo().setText(s4);
		s.getMenuEdit().setText(s5);
		s.getDeposit().setText(s6);
		s.getWithdraw().setText(s7);
		s.getHistory().setText(s8);
		s.getTransfer().setText(s9);
		s.getMenuDelete().setText(s10);
		s.getDelete().setText(s11);
		s.getLanguage().setText(s12);
		s.getMenuTest().setText(s13);
		s.getMenuChat().setText(s14);
		s.getEng_language().setText(s15);
		s.getRus_language().setText(s16);
		s.getChat().setText(s17);

	}
	
	public static void translateMenu_ENG() {
		SignIn s = new SignIn("Bank");

		String language_country = "EN";
		String country = "GB";
		Locale current = new Locale(language_country, country);
		ResourceBundle rb = ResourceBundle.getBundle("property.text", current);
		String s1 = rb.getString("menuCreate");
		String s2 = rb.getString("create");
		String s3 = rb.getString("menuInfo");
		String s4 = rb.getString("info");
		String s5 = rb.getString("menuEdit");
		String s6 = rb.getString("deposit");
		String s7 = rb.getString("withdraw");
		String s8 = rb.getString("history");
		String s9 = rb.getString("transfer");
		String s10 = rb.getString("menuDelete");
		String s11 = rb.getString("delete");
		String s12 = rb.getString("language");
		String s13 = rb.getString("menuTest");
		String s14 = rb.getString("menuChat");
		String s15 = rb.getString("eng_language");
		String s16 = rb.getString("rus_language");
		String s17 = rb.getString("help");

		s.getMenuCreate().setText(s1);
		s.getCreate().setText(s2);
		s.getMenuInfo().setText(s3);
		s.getInfo().setText(s4);
		s.getMenuEdit().setText(s5);
		s.getDeposit().setText(s6);
		s.getWithdraw().setText(s7);
		s.getHistory().setText(s8);
		s.getTransfer().setText(s9);
		s.getMenuDelete().setText(s10);
		s.getDelete().setText(s11);
		s.getLanguage().setText(s12);
		s.getMenuTest().setText(s13);
		s.getMenuChat().setText(s14);
		s.getEng_language().setText(s15);
		s.getRus_language().setText(s16);
		s.getChat().setText(s17);

	}

}
