import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BankLoanTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void chargeInterestTest() {
      BankLoan loan1 = new BankLoan("Jane", .10);
      loan1.borrowFromBank(1000.00);
      loan1.chargeInterest();
      Assert.assertEquals(" ", 1100, loan1.getBalance(), .001);
   }
   
   @Test public void isInDebtTest() {
      BankLoan b = new BankLoan("Bob", .08);
      Assert.assertFalse(" ",BankLoan.isInDebt(b));
      b.borrowFromBank(1);
      Assert.assertTrue(" ",BankLoan.isInDebt(b));
   }

   @Test public void getLoansCreatedTest() {
      BankLoan.resetLoansCreated();
      BankLoan jane = new BankLoan("Jane", .10);
      BankLoan bob = new BankLoan("Bob", .10);
      
      Assert.assertEquals(" ", 2, BankLoan.getLoansCreated(), .001);
      bob = new BankLoan("Bob Parker", 0.02);
      Assert.assertEquals(" ", 3, BankLoan.getLoansCreated(), .001);
   }

   
}
