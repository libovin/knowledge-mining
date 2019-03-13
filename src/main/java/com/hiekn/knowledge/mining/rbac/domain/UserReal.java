package com.hiekn.knowledge.mining.rbac.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="user")
@DynamicUpdate
@DynamicInsert
public class UserReal {

  @Id
  @Column(name = "user_id")
  private String userId;
  private String username;
  private String pwd;
  @Column(name = "wx_openid")
  private String wxOpenid;
  @Column(name = "head_img")
  private String headImg;
  private Long status;
  private String name;
  private String phone;
  private String position;
  private String company;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;
  private String email;
  private String info;
  @Column(name = "data_count")
  private Long dataCount;
  @Column(name = "graph_count")
  private Long graphCount;


}
