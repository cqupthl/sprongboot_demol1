package com.asion.springboot_demol1.service;

import com.asion.springboot_demol1.VO.QueryVo;
import com.asion.springboot_demol1.VO.Req;
import com.asion.springboot_demol1.VO.RespVO;
import com.asion.springboot_demol1.modal.User;

import java.util.List;
import java.util.Map;

public interface IUserService {

    void delete( int id);

    User selectByPrimaryKey (int id);

    List<User> selectAll(String string);

    void update(User user);

    List<User> findByname (String s1);

    List<User> findbyuser(User user);

    List<User> findbyid(QueryVo vo);

    List<RespVO>findByID(Req req);
}

