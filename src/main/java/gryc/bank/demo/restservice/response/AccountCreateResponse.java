package gryc.bank.demo.restservice.response;

public class AccountCreateResponse {
    private final long id;

    public AccountCreateResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
