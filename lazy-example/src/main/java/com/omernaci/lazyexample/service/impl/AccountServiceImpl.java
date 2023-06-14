package com.omernaci.lazyexample.service.impl;

import com.omernaci.lazyexample.persistence.entity.Account;
import com.omernaci.lazyexample.persistence.repository.AccountRepository;
import com.omernaci.lazyexample.service.AccountService;
import com.omernaci.lazyexample.service.dto.AccountDto;
import com.omernaci.lazyexample.util.AccountNumberGenerator;
import java.util.List;
import java.util.Optional;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final SolrClient solrClient;

	public AccountServiceImpl(AccountRepository accountRepository,
			SolrClient solrClient) {
		this.accountRepository = accountRepository;
		this.solrClient = solrClient;
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return mapToAccountDtoList(accounts);
	}

	@Override
	public Optional<AccountDto> getAccountById(Long id) {
		Optional<Account> account = accountRepository.findById(id);

		return account.map(this::mapToAccountDto);
	}

	@Override
	public AccountDto saveAccount(AccountDto accountDto) {
		Account account = mapToAccount(accountDto);
		account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
		Account savedAccount = accountRepository.save(account);

		indexAccountInSolr(savedAccount);

		return mapToAccountDto(savedAccount);
	}

	@Override
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}

	private void indexAccountInSolr(Account account) {
		try {
			SolrInputDocument solrDoc = new SolrInputDocument();
			solrDoc.addField("id", account.getId());
			solrDoc.addField("accountNumber", account.getAccountNumber());
			solrDoc.addField("balance", account.getBalance());
			solrDoc.addField("accountHolderName", account.getAccountHolderName());
			solrDoc.addField("accountType", account.getAccountType());

			solrClient.add(solrDoc);
			solrClient.commit();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private AccountDto mapToAccountDto(Account account) {
		return new AccountDto(account.getAccountNumber(), account.getBalance(),
				account.getAccountHolderName(), account.getAccountType());
	}

	private List<AccountDto> mapToAccountDtoList(List<Account> accounts) {
		return accounts.stream().map(this::mapToAccountDto).toList();
	}

	private Account mapToAccount(AccountDto accountDto) {
		Account account = new Account();
		account.setAccountNumber(accountDto.accountNumber());
		account.setBalance(accountDto.balance());
		account.setAccountHolderName(accountDto.accountHolderName());
		account.setAccountType(accountDto.accountType());
		return account;
	}

}
