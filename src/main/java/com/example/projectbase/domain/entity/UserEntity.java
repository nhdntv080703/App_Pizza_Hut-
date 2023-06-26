package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.DateAuditing;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends DateAuditing {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(insertable = false, updatable = false, nullable = false, columnDefinition = "CHAR(36)")
  private String id;

  @Column(nullable = false)
  @JsonIgnore
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String phone;

  @Column(nullable = false)
  private String address;

  @Column
  private Integer point;

  @Column(nullable = false, unique = true)
  private String username;

  @Nationalized
  @Column(nullable = false)
  private String firstName;

  @Nationalized
  @Column(nullable = false)
  private String lastName;

  //Link to table Role
  @ManyToOne
  @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_USER_ROLE"))
  private RoleEntity roleEntity;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<OrderEntity> orderEntitySet = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "cart",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<ProductEntity> productEntityList = new ArrayList<>();
}