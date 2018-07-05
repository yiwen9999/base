package com.hex.base.repository;

import com.hex.base.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor {
    List<Role> findRolesByIdInOrderByCreateTimeAsc(String[] ids);

    List<Role> findRolesByStateOrderByCreateTimeDesc(Integer state);

    Role findFirstByNameEquals(String name);

//    @Modifying
//    @Query("update Role r set r.name=:name,r.remark=:remark,r.channels=:channels where r.id=:id")
//    public int updateRoleNameRemarkChannelById(@Param("name") String name, @Param("remark") String remark, @Param("channels") List<Channel> channels, @Param("id") String id);
}
