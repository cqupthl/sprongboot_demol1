package com.asion.springboot_demol1.service.impl;

import com.asion.springboot_demol1.VO.QueryVo;
import com.asion.springboot_demol1.VO.Req;
import com.asion.springboot_demol1.VO.RespVO;
import com.asion.springboot_demol1.mappering.UserMapper;
import com.asion.springboot_demol1.modal.User;
import com.asion.springboot_demol1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value = "userService")
public class UserService implements IUserService{

    @Autowired
    private UserMapper userMapper;

    List<User> l1=new ArrayList<>();

   //日期转换
   String dStr ="2001.12.12-08:23:21";
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
   Date date;

    {
        try {
            date = sdf.parse(dStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    User user=new User();
    User user1=new User(3,"小李",date,"男","重庆");

    @Override
    public void delete(int id ) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User selectByPrimaryKey (int id){
        return userMapper.selectByPrimaryKey( id);
    }

    @Override
    public List<User> selectAll(String string) {
       l1=userMapper.selectAll(string );
        return l1;
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> findByname(String s1) {
        return userMapper.findbyname(s1);
    }

    @Override
    public List<User> findbyuser(User user) {
        return userMapper.findbyuser(user);
    }

    @Override
    public List<User> findbyid(QueryVo vo) {
        return userMapper.findbyid(vo);
    }

    @Override
    public List<RespVO> findByID(Req req) {

        int ID=Integer.valueOf(req.getId()).intValue();

     Map<String,Object> map=new HashMap<>();
     map.put("ID",ID);
     map.put("name",req.getName());
     return userMapper.findByID(map);
    }


}
