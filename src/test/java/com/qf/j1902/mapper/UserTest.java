package com.qf.j1902.mapper;

import com.qf.j1902.pojo.*;
import com.qf.j1902.service.UserInfoService;
import org.apache.catalina.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@Configuration("com.qf.j1902.mapper")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
@Autowired
private  CertificateTypeMapper certificateTypeMapper;
@Autowired
private RealNameMapper realNameMapper;
    @Autowired
    private RoleInfoMapper roleInfoMapper;
    @Test
    public void test1(){
        UserInfo qq = userInfoMapper.findUserByName("qq");
        System.out.println(qq);
    }
    @Test
    public void test2(){
        Md5Hash admin = new Md5Hash("admin", null, 1024);
        System.out.println(admin.toString());
        Md5Hash admin2 = new Md5Hash("uu", null, 1024);
        System.out.println(admin2.toString());
    }
    @Test
    public  void ss(){
        CertificateType ss = certificateTypeMapper.findByTpe("个体工商户");
        System.out.println(ss);
    }
    @Test
    public  void ss2(){
//        RealName realName = realNameMapper.selectRealNameByUname("ll");
        boolean b = realNameMapper.updateCodeByUserName("已认证", "未通过", "ll");
        System.out.println(b);
    }

    @Test
    public  void sss(){
        List<Permission> admin = permissionMapper.findPermsById("admin");
        System.out.println(admin);
    }
    @Test
    public void ses(){
        realNameMapper.addUserRole(101,100);
    }
    @Test
    public void SSSS(){
        UserInfo userInfo = new UserInfo();
        /*private Integer userid;  //用户id
    private  String userName;//用户名
    private  String password;//用户密码
    private  String status;//用户身份
    private Date createTime;//创建时间
    private String email;//创建时间
*/
        userInfo.setCreateTime(new Date());
        userInfo.setEmail("111");
        userInfo.setStatus("2323");
        userInfo.setPassword("89");
        userInfo.setUserName("werw");

        userInfoMapper.updateUserById(userInfo,8);
    }
    @Test
    public void role11(){
        List<RoleInfo> userRoleById = roleInfoMapper.findUserRoleById(5);
        for (RoleInfo u:userRoleById){
            System.out.println(u);
        }
        System.out.println("---------------------");
        List<RoleInfo> notRoleById = roleInfoMapper.findNotRoleById(5);
        if (notRoleById ==null){
            System.out.println("vihrejfioer");
        }
        System.out.println(notRoleById);
       /* for (RoleInfo u:notRoleById){
            System.out.println(u);
        }*/
    }

    @Test
    public void cate(){
        List<Label> allByCateName = labelMapper.findAllByCateName("科技");
        System.out.println(allByCateName);
        for (Label a:allByCateName){
            System.out.println(a);
        }
    }
}
