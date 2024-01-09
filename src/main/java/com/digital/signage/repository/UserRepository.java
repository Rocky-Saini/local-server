package com.digital.signage.repository;

import com.digital.signage.models.User;
//import com.digital.signage.dto.UserIdAndFullname;
import com.digital.signage.dto.UserIdFullNameRoleIdAndRoleName;
//import com.digital.signage.models.UserDTO;
//import com.digital.signage.models.UserIdFullNameAndEmail;
import com.digital.signage.util.Status;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    String userQuery =
            "select u from User u where lower(u.email) like lower(:email) and lower(u.fullName) like lower(:name)";

    List<User> findUserByEmail(String email);

    @Query(value = "select * from user as u where u.email = :email and (u.status not in (:status) or u.status IS NULL)",
            nativeQuery = true)
    List<User> findUserByEmailAndStatusNot(@Param("email") String email,
                                           @Param("status") Status status);

    User findUserByUserId(Long id);
//
//    List<User> findUserByFullName(String name);
//
//    User findUserByUserIdAndStatus(Long id, Status status);
//
//    @Query(value = "select * from user as u where u.userId = :userId and (u.status in (1, 2) or u.status IS NULL)",
//            nativeQuery = true)
//    User findUserByUserIdAndStatusNot(@Param("userId") Long userId);
//
//    List<User> findUserByStatus(Status status);
//
//    @Query(value = "select * from user as u where u.email = :email and (u.status in (1, 2) or u.status IS NULL)",
//            nativeQuery = true)
//    User findUserByEmailAndStatusNotDeleted(@Param("email") String email);

//    @Query(userQuery)
//    Page<User> userQuery(@Param("email") String email, @Param("name") String name, Pageable pageable);
//
//    @Query("select u from User u inner join u.customers cust where cust.customerId = :customerId and u.userId=:userId")
//    User getUserbyIdAndCustomerId(@Param("userId") Long userId, @Param("customerId") Long customerId);
//
//    @Query("select u.userId from User u inner join u.customers cust inner join u.roles role "
//            + "where cust.customerId = :customerId and role.name not in (:roles) order by u.userId desc")
//    List<Long> getNonPanasonicUserIdListByCustomerId(@Param("customerId") Long customerId,
//                                                     @Param("roles") Set<String> roleNames);

    //@Query(nativeQuery = true, value = "SELECT u.userId, u.email, u.fullName FROM user AS u INNER JOIN userRoleMapping AS urm on urm.userId = u.userId INNER JOIN role AS r ON urm.roleId = r.roleId WHERE r.roleName IN (:roleNames) AND u.status IN (1, 2)")
    //List<UserIdFullNameAndEmail> getAllUsersWithRoleNamesInAndStatusNotDeleted(@Param("roleNames") Set<String> roleNames);

//    @Query("select u from User u inner join u.roles role where role.name in :roleNames")
//    List<User> getAllUsersWithRoleNamesIn(@Param("roleNames") Set<String> roleNames);
//    //@Query(value = "SELECT r.locationId, u.userId, u.fullName FROM userLocationRel as r left join panasonic.user as u on u.userId = r.userId where u.customerId = :customerId and r.locationId in (:locationIds)" ,nativeQuery = true)
//    //List<UserLocationDTO> getUserByLocationId(Long customerId,List<Long> locationIds);
//
//    @Query("select u from User u inner join u.roles role where role.id in :roleIds and u.status<>:status")
//    List<User> getAllUserByRoleId(@Param("roleIds") Set<Long> roleIds,
//                                  @Param("status") Status status);
//
//    @Query("select u from User u inner join u.roles r inner join u.customers cust "
//            + "where r.id in (:roleIds) and cust.customerId =:customerId and u.status =:status and cust.status=:status")
//    List<User> findUsersByRoles(@Param("roleIds") List<Long> roleIds,
//                                @Param("customerId") Long customerId, @Param("status") Status status);

    //@Query(value = "SELECT new digital.signage.models.UserDTO(u.userId, u.fullName) FROM User u WHERE userId = :userId AND Status IN (1, 2)")
    //UserDTO getUserName(@Param("userId") Long userId);

    //@Query(value = "SELECT new digital.signage.models.UserDTO(u.userId, u.fullName) FROM User u WHERE userId in (:userIds) AND Status IN (1, 2)")
    //List<UserDTO> getAllUserName(@Param("userIds") List<Long> userIds);

//    @Query(value =
//            "SELECT u.email FROM userCustomerAssociation as ca inner join user as u on u.userId = ca.userId "
//                    + "inner join userRoleMapping as rm on u.userId = rm.userId  inner join role as r on r.roleId = rm.roleId "
//                    + "where ca.customerId =:customerId and u.status =1 and r.roleName in ('PANASONIC_CUST_REP', 'CUSTOMER_ADMIN')", nativeQuery = true)
//    List<String> getCustRepAndCustomerAdminUserEmail(@Param("customerId") Long customerId);

//    @Query(value =
//            "SELECT u.* FROM userCustomerAssociation as ca inner join user as u on u.userId = ca.userId "
//                    + "inner join userRoleMapping as rm on u.userId = rm.userId  inner join role as r on r.roleId = rm.roleId "
//                    + "where ca.customerId =:customerId and u.status =1 and r.roleName in ('PANASONIC_CUST_REP', 'CUSTOMER_ADMIN')", nativeQuery = true)
//    List<User> getCustRepAndCustomerAdminUser(@Param("customerId") Long customerId);

//    @Query(value = "SELECT u.* FROM userRoleMapping as ur"
//            + "    inner join user as u on u.userId = ur.userId "
//            + "     inner join role as r on r.roleId = ur.roleId"
//            + "      where r.roleName ='PANASONIC_ADMIN' and u.status = 1", nativeQuery = true)
//    List<User> findUserByPanasonicAdmin();

//    @Query(value = "SELECT COUNT(u.userId) FROM userRoleMapping AS ur "
//            + " INNER JOIN user AS u ON u.userId = ur.userId "
//            + " INNER JOIN role AS r ON r.roleId = ur.roleId "
//            + " WHERE r.roleName = 'PANASONIC_ADMIN' AND (u.status = 1 OR u.status IS NULL)", nativeQuery = true)
//    Number findActiveOrPasswordNotSetPanasonicAdminUserCount();

//    @Query(value = "SELECT u.* FROM userCustomerAssociation as ca inner join user as u on "
//            + "      u.userId = ca.userId inner join userRoleMapping as rm on u.userId = rm.userId inner "
//            + "    join role as r on r.roleId = rm.roleId where ca.customerId = :customerId and u.status =1 and "
//            + "       r.roleName = 'PANASONIC_CUST_REP'", nativeQuery = true)
//    List<User> findUsersByCustRepAdmin(@Param("customerId") Long customerId);

//    @Query(value = "SELECT u.* FROM userCustomerAssociation as ca inner join user as u on "
//            + "     u.userId = ca.userId inner join userRoleMapping as rm on u.userId = rm.userId inner "
//            + "        join role as r on r.roleId = rm.roleId where ca.customerId = :customerId and u.status=1 and "
//            + "          r.roleName IN ('PANASONIC_CUST_REP','CUSTOMER_ADMIN')", nativeQuery = true)
//    List<User> findUsersByCustomerAndCustRepAdmin(@Param("customerId") Long customerId);

//    @Query(value = "select u.* from userCustomerAssociation as ca inner join user as u on ca.userId=u.userId where u.status!=3 and ca.customerId=:customerId", nativeQuery = true)
//    List<User> findAlluserByCustomerId(@Param("customerId") Long customerId);

//    @Query(value =
//            "select u.* from userCustomerAssociation as ca "
//                    + " inner join user as u on ca.userId=u.userId "
//                    + " inner join userRoleMapping as urm on urm.userId = u.userId "
//                    + " inner join role as r on r.roleId = urm.roleId "
//                    + " where (u.status is null or u.status!=3) "
//                    + " and ca.customerId=:customerId "
//                    + " and r.roleName <> 'PANASONIC_ADMIN' "
//                    + " and r.roleName <> 'PAN_CUST_REP' "
//                    + " and u.email=:email ",
//            nativeQuery = true)
//    List<User> findAllCustomerUserByCustomerIdAndEmail(@Param("customerId") Long customerId,
//                                                       @Param("email") String email);
//
//    @Query(value = "select * from user where userId in (:userIds)", nativeQuery = true)
//    List<User> getUserFullName(@Param("userIds") Set<Long> userIds);
//
//    @Query(value = "SELECT u.* FROM userCustomerAssociation as ca inner join user as u on "
//            + "     u.userId = ca.userId inner join userRoleMapping as rm on u.userId = rm.userId inner "
//            + "        join role as r on r.roleId = rm.roleId where ca.customerId = :customerId and u.status=1 and "
//            + "          r.roleName IN ('CUSTOMER_ADMIN')", nativeQuery = true)
//    List<User> findUsersByCustomerAdmin(@Param("customerId") Long customerId);

//    @Query(value = "SELECT u.* FROM userCustomerAssociation as ca inner join user as u on"
//            + " u.userId = ca.userId  inner join userRoleMapping as rm on u.userId = rm.userId inner "
//            + " join role as r on r.roleId = rm.roleId inner join rolePrivilegeMapping as rp on rp.roleId =r.roleId inner join privilege  as p on rp.privilegeId=p.privilegeId where  ca.customerId = :customerId and u.status=1 and "
//            + " (p.privilegeName='VIEW_DEVICE' and r.customerId = :customerId  or r.roleName IN ('CUSTOMER_ADMIN','PANASONIC_CUST_REP')) group by u.userId", nativeQuery = true)
//    List<User> findUsersByCustomerAndCustRepAdminAndDeviceAccessRole(
//            @Param("customerId") Long customerId);

//    @Query(value = "SELECT u.*  FROM userCustomerAssociation as ca "
//            + " inner join user as u on u.userId = ca.userId "
//            + " inner join userRoleMapping as rm on u.userId = rm.userId "
//            + " inner join role as r on r.roleId = rm.roleId "
//            + " where ca.customerId in (:customerIds) "
//            + " and (u.status != 3 or u.status is null) "
//            + " and r.roleName not in (:roleNames)",
//            nativeQuery = true)
//    List<User> findUsersBelongingToCustomerIdsAndNotInPanasonicRolesAndNotDeleted(
//            @Param("customerIds") List<Long> customerIds,
//            @Param("roleNames") Set<String> roleNames);

//    @Query(value = "SELECT `userId` FROM `user` WHERE `status` IN (1, 2)", nativeQuery = true)
//    List<Number> findAllValidUserIds();
//
//    @Query(value = "SELECT " +
//            "   u.`userId`, " +
//            "   u.`fullName`, " +
//            "   r.`roleId`, " +
//            "   r.`roleName` " +
//            " FROM " +
//            "   `userRoleMapping` urm " +
//            " INNER JOIN `user` u ON " +
//            "   urm.`userId` = u.`userId` " +
//            " INNER JOIN `role` r ON " +
//            "   urm.`roleId` = r.`roleId` " +
//            " WHERE urm.`userId` = :userId", nativeQuery = true)
//    List<UserIdFullNameRoleIdAndRoleName> getUserIdFullNameRoleIdAndRoleNameByUserId(
//            @NonNull @Param("userId") Long userId
//    );

    @Query(
            value = "SELECT " +
                    "   u.`user_id`, " +
                    "   u.`full_name`, " +
                    "   r.`role_id`, " +
                    "   r.`role_name` " +
                    " FROM " +
                    "   `user_role_mapping` urm " +
                    " INNER JOIN `user` u ON " +
                    "   urm.`user_id` = u.`userId` " +
                    " INNER JOIN `role` r ON " +
                    "   urm.`role_id` = r.`roleId` " +
                    " WHERE urm.`user_id` = :userId" +
                    "   AND u.`status` IN (1,2)",
            nativeQuery = true
    )
    List<UserIdFullNameRoleIdAndRoleName> getUserIdFullNameRoleIdAndRoleNameByUserIdAndNotDeleted(
            @NonNull @Param("userId") Long userId
    );

//    @Query(value = "SELECT uca.`customerId` FROM `userCustomerAssociation` uca WHERE uca.`userId` = :userId", nativeQuery = true)
//    List<Number> getCustomerIdsAssociatedWithGivenUserId(@NonNull @Param("userId") Long userId);

    /*@Query(value = "SELECT "
            + "   u.`userId`, "
            + "   u.`fullName` "
            + " FROM "
            + "   `userCustomerAssociation` uca "
            + " INNER JOIN `user` u ON "
            + "   u.`userId` = uca.`userId` "
            + " WHERE "
            + "   uca.`customerId` = :customerId", nativeQuery = true)
    List<UserIdAndFullname> getAllUserNameByCustomerId(@Param("customerId") Long customerId);


    @Query(value = "SELECT u.userId, u.email, u.fullName FROM userCustomerAssociation as ca inner join user as u on "
            + "     u.userId = ca.userId inner join userRoleMapping as rm on u.userId = rm.userId inner "
            + "        join role as r on r.roleId = rm.roleId where ca.customerId = :customerId and u.status = 1 and "
            + "          r.roleName = 'CUSTOMER_ADMIN'", nativeQuery = true)
    List<UserIdFullNameAndEmail> findUsersNameAndEmailByCustomerAdmin(@Param("customerId") Long customerId);

    @Query(value = "SELECT u.`userId`, u.`fullName` FROM `user` u", nativeQuery = true)
    List<UserIdAndFullname> getAllUserIdFullname();

    @Query(value = "SELECT u.`userId`, u.`fullName` FROM `user` u WHERE u.`userId` IN (:userIds)", nativeQuery = true)
    List<UserIdAndFullname> getAllUserIdFullnameByUserIds(
            @Param("userIds") List<Long> userIds
    );*/

//    @Query(value = "select * from role where role_id = (select role_id from user_role_mapping ur where ur.user_id = :userId ) " , nativeQuery = true)
//    Role getRole(@Param("userId") Long userId);
}