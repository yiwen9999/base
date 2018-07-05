package com.hex.base.repository;

import com.hex.base.domain.Operator;
import com.hex.base.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OperatorRepository extends JpaRepository<Operator, String>, JpaSpecificationExecutor {
    Operator findByName(String name);

    Operator findByEmail(String email);

    Operator findByMobile(String mobile);

    Long countOperatorsByRole(Role role);
}
