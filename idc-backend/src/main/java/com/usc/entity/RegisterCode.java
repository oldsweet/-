package com.usc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Baisong Li
 * @since 2023-05-11 06:54:39
 */
@Getter
@Setter
@TableName("register_code")

public class RegisterCode {

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("code")
    private String code;

    @TableField("email")
    private String email;

      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
