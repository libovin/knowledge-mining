package com.hiekn.knowledge.mining.rbac.repository;

import com.hiekn.knowledge.mining.rbac.model.dao.UserReal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRealRepository extends JpaRepository<UserReal, String> {

    @Query(nativeQuery = true,value = "select t.user_id from t_my_graph as t where t.graph_name = ? ")
    String findUserId(String kgName);

    @Query(nativeQuery = true,value = "select x.apk from t_user_apk as x,t_my_graph as t where x.user_Id=t.user_id and t.graph_name = ? ")
    String findApk(String kgName);

//    @Query(nativeQuery = true, value = "select x.apk, from t_user_apk as x,t_my_graph as t where x.user_Id=t.user_id and t.graph_name = ?0")
//    Object findDbName(String kgName,String collectionName);
}
