package gryc.bank.demo.restservice.controller;

import gryc.bank.demo.cqrs.service.AccountService;
import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;
import gryc.bank.demo.restservice.dto.CreateAccountDto;
import gryc.bank.demo.restservice.response.AccountCreateResponse;
import gryc.bank.demo.restservice.response.GetBalanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController
@Validated
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    @ResponseBody
    public AccountCreateResponse createAccount(@RequestBody CreateAccountDto createAccountDto) {
        return new AccountCreateResponse(accountService.createUser(createAccountDto));
    }

    @GetMapping("/getBalance/{id}")
    @ResponseBody
    public GetBalanceResponse getBalance(@PathVariable(value = "id") @Min(0) @Max(Long.MAX_VALUE) long id) {
        return new GetBalanceResponse(accountService.getBalance(id));
    }

    @PostMapping("/setBalance")
    @ResponseBody
    public ResponseEntity<AccountBalanceChangeDto> setBalance(@RequestBody AccountBalanceChangeDto accountBalanceChangeDto) {
        accountService.changeBalance(accountBalanceChangeDto);
        return ResponseEntity.ok().build();
    }

}
