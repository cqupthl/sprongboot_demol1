package com.asion.springboot_demol1.controller;

import com.asion.springboot_demol1.VO.QueryVo;
import com.asion.springboot_demol1.VO.Req;
import com.asion.springboot_demol1.VO.RespVO;
import com.asion.springboot_demol1.modal.User;
import com.asion.springboot_demol1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private IUserService userService;

    @Value(value="${imageExtend}")
     protected String imageExtend;

    @Value("${wordExtend}")
    protected String wordExtend;

    @Value(value="${base_product_path}")
    private String filePath;

    List<User> list1=new ArrayList<>();


    @RequestMapping(value = {"/selectByPrimaryKey"},method = RequestMethod.GET)
    @ResponseBody
    public User selectByPrimaryKey(){

        System.out.println("查询成功");
        User u1= userService.selectByPrimaryKey(45);
        //  System.out.println(u1.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        u1.setReturnbirthday(formatter.format(u1.getBirthday()));
        return u1;

    }

    @RequestMapping(value = {"/delete"},method = RequestMethod.GET)
    public void deleteUser(){

        System.out.println("删除成功");
        userService.delete(42);

    }

    @RequestMapping(value = {"/selectAll"},method =RequestMethod.GET)
    @ResponseBody
    public List<User> selectAll(@RequestParam("str") String string ){
        System.out.println("查询成功");
       list1 =userService.selectAll(string );
        return list1;
    }


    @RequestMapping(value = {"/update"},method = RequestMethod.GET)
    @ResponseBody
    public User update( ){

        User user2=userService.selectByPrimaryKey(46);
        user2.setAddress("重庆");

        System.out.println("更新成功");
        userService.update(user2);
        return userService.selectByPrimaryKey(46);
    }

    @RequestMapping(value = "/findByname",method = RequestMethod.GET)
    @ResponseBody
    public List<User> findByname( ){
        String s1="王";
        System.out.println("模糊查找成功");
        return userService.findByname(s1);

    }

    @RequestMapping(value = "findbyuser",method =RequestMethod.GET)
    @ResponseBody
    public List<User> findbyuser( ){
        User user=new User();
        user.setUsername("%王%");
        user.setAddress("%龙%");
        System.out.println("查找成功");
        return userService.findbyuser(user);
    }

    @RequestMapping(value = "findbyid",method = RequestMethod.GET)
    @ResponseBody
    public List<User> findbyid(){
        System.out.println("查找成功");
        QueryVo vo=new QueryVo();
        List<Integer> list=new ArrayList<>();
        list.add(41);
        list.add(42);
        vo.setIds(list);
        return userService.findbyid(vo);
    }
    @RequestMapping(value = "/findByID",method = RequestMethod.POST)
    @ResponseBody
    public List<RespVO> findByID(Req  req){
//    System.out.println("id="+req);


   return userService.findByID(req);


    }

    @ResponseBody
    @RequestMapping(value = {"/uploadWord"},method = RequestMethod.POST)
    public String  uploadWord(@RequestParam("file" ) MultipartFile file) {
        String str;
        try {
            //将文件存放路径给前端
            if (file.isEmpty()) {
                str="文件为空";
                System.out.println(str);
                return str ;
            }
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            String extend = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            if (!wordExtend.contains(extend)) {
                str="请上传word文件";
                System.out.println(str);
                return str;
            }

            String url = filePath + fileName; //这里filePath=E：ceshi/   这样生成的文件在E ceshi 文件夹下
            File file1 = new File(url);

            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdir();
            }
            file.transferTo(file1);
            str="上传成功";
            System.out.println(str);

        } catch (Exception e) {
            e.printStackTrace();
            str="上传zz";
        }
        return str;
    }

    @ResponseBody
    @RequestMapping(value ={"/multifile"}, method=RequestMethod.POST)
    public String handFileUpload(HttpServletRequest httpServletRequest) throws IOException {
         List<MultipartFile> list=((MultipartHttpServletRequest) httpServletRequest).getFiles("file");
         MultipartFile multipartFile=null;
        BufferedOutputStream stream=null;

        for(int i=0;i<list.size();i++){
            multipartFile=list.get(i);
            if(!multipartFile.isEmpty()){
                String extend=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
                if(!wordExtend.contains(extend)){
                    return "此文件非word文件，请上传word文件";
                }
                String url=filePath+multipartFile.getOriginalFilename();
                File file=new File(url);
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdir();
                }
              multipartFile.transferTo(file);
                System.out.println("第"+i+"个文件上传成功");
            }else{
                return "第"+i+"个文件为空";
            }
        }

         return  "成功";
    }

//    @ResponseBody
//    @RequestMapping(value = {"/down"} ,method = RequestMethod.POST)
//    public string

}
