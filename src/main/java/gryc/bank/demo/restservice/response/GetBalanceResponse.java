package gryc.bank.demo.restservice.response;

import java.math.BigInteger;

public class GetBalanceResponse {
    private BigInteger balance;

    public GetBalanceResponse(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getBalance() {
        return balance;
    }
}
