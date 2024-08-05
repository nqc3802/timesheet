package com.example.timesheet.role;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findById(int id);

    Optional<Role> findByName(String name);

    @Query("SELECT u.id AS id, u.name AS name, u.username AS username, u.email AS email " +
            "FROM User u JOIN UserRole ur ON u.id = ur.user.id " +
            "WHERE ur.role.id = :id")
    List<RoleProjection> findRoleWithUsers(int id);
    
    // @Query("SELECT r FROM Role r ORDER BY r.name ASC") // nếu sử dụng query thì tham số sort truyền vào sẽ không có tác dụng
    // Page<Role> findAllAndSort(Pageable pageable);
}
