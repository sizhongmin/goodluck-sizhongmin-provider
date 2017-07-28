package com.bw.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.Serializable;

/**
 * Created by a on 2017/7/28.
 */
@Data
@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String pwd;
    private Integer age;
    private String sex;
    private String hobby;


}
