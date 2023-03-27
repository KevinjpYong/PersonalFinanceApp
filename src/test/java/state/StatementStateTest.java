package state;

import model.Expense;
import model.Income;
import model.Statement;
import model.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatementStateTest {

   private Statement statement;

   @BeforeEach
   public void init(){
      SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
      String dateString = "03/2023";
      try {
         Date date = formatter.parse(dateString);
         statement = new Statement(date);

         Income income = new Income("Salary", 6000);
         Expense expense = new Expense("Car Loan Repayment", 1000);

         statement.addIncome(income);
         statement.addExpense(expense);
      } catch (ParseException e) {
         throw new RuntimeException(e);
      }
   }

   @Test
    public void displayStatementTest(){
       String displayStatementsOption = "1\n";
       String backToStatementMenuOption = "2\n";
       String input = displayStatementsOption +
               backToStatementMenuOption;
       InputStream in = new ByteArrayInputStream(input.getBytes());
       System.setIn(in);

       User user = new User("Tester", 25);
       StatementState statementState = new StatementState();
       assertTrue(statementState.handle(user) instanceof StatementState);
   }

   @Test
   public void viewStatementTest(){
      String displayStatementsOption = "1\n";
      String viewStatementOption = "1\n";
      String statementDate = "03/2023\n";
      String backToStatementsOption = "3\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              viewStatementOption +
              statementDate +
              backToStatementsOption +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
   }

   @Test
   public void viewStatementNotExistTest(){
      String displayStatementsOption = "1\n";
      String viewStatementOption = "1\n";
      String wrongStatementDate = "01/2023\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              viewStatementOption +
              wrongStatementDate +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
   }

   @Test
   public void viewStatementInvalidInput(){
      String displayStatementsOption = "1\n";
      String viewStatementOption = "1\n";
      String invalidStatementDate = "abc/bcde\n";
      String statementDate = "03/2023\n";
      String backToStatementsOption = "2\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              viewStatementOption +
              invalidStatementDate +
              statementDate +
              backToStatementsOption +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
   }

   @Test
   public void addStatement() throws ParseException {
      String displayStatementsOption = "2\n";
      String statementDate = "02/2023\n";
      String backToStatementsOption = "3\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              statementDate +
              backToStatementsOption +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
      String dateString = "02/2023";
      Date date = formatter.parse(dateString);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
      assertTrue(user.getStatements().containsKey(date));
   }


   @Test
   public void addIncome() throws ParseException {
      String displayStatementsOption = "2\n";
      String statementDate = "02/2023\n";
      String addIncomeOption = "1\n";
      String incomeName = "Salary\n";
      String incomeAmount = "6000\n";
      String backToStatementsOption = "3\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              statementDate +
              addIncomeOption +
              incomeName +
              incomeAmount +
              backToStatementsOption +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
      String dateString = "02/2023";
      Date date = formatter.parse(dateString);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
      assertEquals("Salary",user.getStatements().get(date).getIncomes().get(0).getName());
      assertEquals(6000,user.getStatements().get(date).getIncomes().get(0).getAmount());
   }

   @Test
   public void addExpense() throws ParseException {
      String displayStatementsOption = "2\n";
      String statementDate = "02/2023\n";
      String addExpenseOption = "2\n";
      String expenseName = "Food\n";
      String expenseAmount = "50\n";
      String backToStatementsOption = "3\n";
      String backToStatementMenu = "2\n";

      String input = displayStatementsOption +
              statementDate +
              addExpenseOption +
              expenseName +
              expenseAmount +
              backToStatementsOption +
              backToStatementMenu;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
      String dateString = "02/2023";
      Date date = formatter.parse(dateString);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof StatementState);
      assertEquals("Food",user.getStatements().get(date).getExpenses().get(0).getName());
      assertEquals(50,user.getStatements().get(date).getExpenses().get(0).getAmount());
   }

   @Test
   public void backToMainMenu(){
      String displayStatementsOption = "3\n";
      String input = displayStatementsOption;
      InputStream in = new ByteArrayInputStream(input.getBytes());
      System.setIn(in);

      User user = new User("Tester", 25);
      StatementState statementState = new StatementState();
      assertTrue(statementState.handle(user) instanceof SelectionState);
   }
}
