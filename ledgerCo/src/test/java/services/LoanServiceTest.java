package services;

import com.ledgerCo.dao.BankDao;
import com.ledgerCo.dao.BorrowerDao;
import com.ledgerCo.dao.LoanDao;
import com.ledgerCo.models.Bank;
import com.ledgerCo.models.Borrower;
import com.ledgerCo.models.Loan;
import com.ledgerCo.services.BankService;
import com.ledgerCo.services.BorrowerService;
import com.ledgerCo.services.LoanService;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class LoanServiceTest {

    @Test
    public void testGetBalance() throws Exception {
        LoanDao loanDao = new LoanDao();
        LoanService loanService = new LoanService(loanDao);
        BankDao bankDao = new BankDao();
        BankService bankService = new BankService(bankDao);
        BorrowerDao borrowerDao = new BorrowerDao();
        BorrowerService borrowerService = new BorrowerService(borrowerDao);
        Bank bank = bankService.getBank("DummyBank");
        Borrower borrower = borrowerService.getBorrower("Tom");
        loanService.addNewLoan(bank, borrower, 10000, 5, 2);
        Loan loan = loanService.getLoanDetails(bank.getName() + "-" + borrower.getName());
        assertEquals("First test case in testGetBalance", "DummyBank Tom 0 24", loanService.getBalance(loan, 0));
        assertEquals("Second test case in testGetBalance", "DummyBank Tom 1836 20", loanService.getBalance(loan, 4));
    }
}
