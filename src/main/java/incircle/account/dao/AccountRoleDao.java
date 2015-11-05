package incircle.account.dao;

import incircle.account.model.AccountRole;

public interface AccountRoleDao {
	AccountRole updateRole(AccountRole accountRole, boolean update);
}
