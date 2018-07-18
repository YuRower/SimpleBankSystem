package test;


import junit.framework.Assert;

import org.junit.Test;

public class TestPasswordValidator
{
   @SuppressWarnings("deprecation")
@Test
   public void testNormalPassword()
   {
       PasswordValidator validator = PasswordValidator.buildValidator(false, false, false, 6, 14);

       Assert.assertTrue(validator.validatePassword("howtodoinjava"));
       Assert.assertTrue(validator.validatePassword("howtodoin"));
       //Sort on length
       Assert.assertFalse(validator.validatePassword("howto"));
   }

   @Test
   public void testForceNumeric()
   {
       PasswordValidator validator = PasswordValidator.buildValidator(false,false, true, 6, 16);
       //Contains numeric
       Assert.assertTrue(validator.validatePassword("howtodoinjava12"));
       Assert.assertTrue(validator.validatePassword("34howtodoinjava"));
       Assert.assertTrue(validator.validatePassword("howtodo56injava"));
       //No numeric
       Assert.assertFalse(validator.validatePassword("howtodoinjava"));
   }

   @Test
   public void testForceCapitalLetter()
   {
       PasswordValidator validator = PasswordValidator.buildValidator(false,true, false, 6, 16);
       //Contains capitals
       Assert.assertTrue(validator.validatePassword("howtodoinjavA"));
       Assert.assertTrue(validator.validatePassword("Howtodoinjava"));
       Assert.assertTrue(validator.validatePassword("howtodOInjava"));
       //No capital letter
       Assert.assertFalse(validator.validatePassword("howtodoinjava"));
   }

   @Test
   public void testForceSpecialCharacter()
   {
       PasswordValidator validator = PasswordValidator.buildValidator(true,false, false, 6, 16);
       //Contains special char
       Assert.assertTrue(validator.validatePassword("howtod@injava"));
       Assert.assertTrue(validator.validatePassword("@Howtodoinjava"));
       Assert.assertTrue(validator.validatePassword("howtodOInjava@"));
       //No special char
       Assert.assertFalse(validator.validatePassword("howtodoinjava"));
   }
}