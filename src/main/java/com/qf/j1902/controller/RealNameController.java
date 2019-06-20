package com.qf.j1902.controller;

import com.qf.j1902.pojo.CertificateType;
import com.qf.j1902.pojo.RealName;
import com.qf.j1902.service.CertificateTypeService;
import com.qf.j1902.service.RealNameService;
import com.qf.j1902.utils.Sessionkey;
import com.qf.j1902.vo.RealNameVo;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.shiro.session.mgt.SessionKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class RealNameController {
    @Autowired
    private CertificateTypeService certificateTypeService;
    @Autowired
    private RealNameService realNameService;
    @GetMapping("/accttype")
    public  String maber(){
        return "accttype";
    }
    @GetMapping("/cert")
    public  String cert(){
        return "cert";
    }

    @GetMapping("/type")
    public  String typemanager(){
        return "type";
    }
    @RequestMapping(value = "/typelist" ,method = RequestMethod.POST)
    @ResponseBody
    public  String typelist(String shangye,String geti,String geren,String zhengfu){
        certificateTypeService.delType();
        CertificateType type1 = new CertificateType("商业公司",shangye);
        CertificateType type2 = new CertificateType("个体工商户",geti);
        CertificateType type3 = new CertificateType("个人经营",geren);
        CertificateType type4 = new CertificateType("政府及非营利组织",zhengfu);
        certificateTypeService.addType(type1);
        certificateTypeService.addType(type2);
        certificateTypeService.addType(type3);
        certificateTypeService.addType(type4);
        return "1";
    }

    @GetMapping("/apply")
    public  String apply(String type, Model model){
        model.addAttribute("type",type);
        return "apply";
    }
    @GetMapping("/apply-1")
    public  String apply1(Model model,String type,String realname, String namber,String phone){
        String regex =  "^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        if (type ==null ||type =="")return "apply";
        if (!realname.matches("[\u4e00-\u9fa5]{2,4}")){
            model.addAttribute("name","2");
            return "apply";
        }
        if (namber ==null || namber =="" ){
            model.addAttribute("namber","1");
            return "apply";
        }
        if (namber.length()!=18  ){
            model.addAttribute("namber","2");
            return "apply";
        }
        if (phone ==null || phone =="" ){
            model.addAttribute("phone","1");
            return "apply";
        }
        if (phone.length()!=11 ||  !phone.matches(regex) ){
            model.addAttribute("phone","2");
            return "apply";
        }
        CertificateType certificateType = certificateTypeService.findByTpe(type);
        String mingcheng = certificateType.getMingcheng();
        String[] splits = mingcheng.split(",");
        model.addAttribute("splits",splits);
        model.addAttribute("type",type);
        model.addAttribute("realname",realname);
        model.addAttribute("namber",namber);
        model.addAttribute("phone",phone);

        return "apply-1";
    }
    @RequestMapping(value = "/apply-2",method = RequestMethod.POST)
    public String apply2(List<MultipartFile>  imgs, HttpServletRequest request, Model model,String type
            ,String  realname,String namber,String phone){
        String fileName ="";
            for (MultipartFile img: imgs){
               String s= imageUpload(img, request, model);
                fileName = fileName+ ","+s;
            }
        model.addAttribute("type",type);
        model.addAttribute("realname",realname);
        model.addAttribute("namber",namber);
        model.addAttribute("phone",phone);
        model.addAttribute("img",fileName);

        return "apply-2";
    }
    private String imageUpload( MultipartFile mf, HttpServletRequest request, Model model) {
        String path ="E:\\javaee\\j1902_zhongchou_springboot_shiro\\src\\main\\resources\\static\\upload";

        String fileName = mf.getOriginalFilename();
        String location = path +"/"+ fileName;
        String regex_img = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";
        if (fileName == null || fileName=="" || !fileName.matches(regex_img)){
            model.addAttribute("img","1");
            return "url";
        }
        File f = new File(location);
        try {
            f.createNewFile();
            mf.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    @PostMapping("/apply-3")
    public  String apply3(RealNameVo realNameVo,Model model,HttpSession session){
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if (!realNameVo.getEmail().matches(regex)){
            model.addAttribute("email","1");
            return "apply-2";
        }
        String aa = sendEmails(realNameVo.getEmail());
        session.setAttribute("codell",aa);
        session.setAttribute("realNameVo",realNameVo);

        return "apply-3";
    }
    @RequestMapping(value = "fsyz" ,method = RequestMethod.GET)
    @ResponseBody
    public  String fsyz(HttpSession session){
        String email = (String)session.getAttribute("email");
        String aa = sendEmails(email);
        session.setAttribute("codell",aa);
        return aa;
    }
    public  String sendEmails(String email){
        int a = (int) ((Math.random() * 9 + 1) * 100000);
        String aa = String.valueOf(a);
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName("smtp.139.com");
        htmlEmail.setCharset("utf-8");
        try {
            htmlEmail.addTo(email);
            htmlEmail.setFrom("13772079049@139.com","人人众筹");
            htmlEmail.setAuthentication("13772079049@139.com","030508qq");
            htmlEmail.setSubject("实名认证验证码");
            htmlEmail.setMsg("尊贵的会员：您的验证码为"+"<h3>"+aa+"</h3>");
            htmlEmail.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return aa;
    }
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public  String code(HttpSession session,Model model,String codeon){
        String codell = (String) session.getAttribute("codell");
        if (!codell.equals(codeon)){
            model.addAttribute("apply-3","1");
        }
        RealNameVo realNameVo = (RealNameVo) session.getAttribute("realNameVo");
        String code ="审核中";
        if (realNameVo != null){
            RealName realName = new RealName();
            realName.setUserName(realNameVo.getUserName());
            realName.setEmail(realNameVo.getEmail());
            realName.setRealName(realNameVo.getRealName());
            realName.setIDCard(realNameVo.getIDCard());
            realName.setPhone(realNameVo.getPhone());
            realName.setImg(realNameVo.getImg());
            realName.setType(realNameVo.getType());
            realName.setRealTime(new Date());
            realName.setCode(code);
            realNameService.addRealName(realName);
        }
        if( codeon !=null){
            session.setAttribute(Sessionkey.REAL_CODE,code);
            session.setAttribute(Sessionkey.REAL_TYPE,realNameVo.getType());
        }
        return  "redirect:/manager/member";
    }
    @PostMapping("/zaicishnehe")
    public  String zaicishnehe( RealNameVo realNameVo,HttpSession session){
        String code ="审核中";
        if (realNameVo != null){
            RealName realName = new RealName();
            realName.setUserName(realNameVo.getUserName());
            realName.setEmail(realNameVo.getEmail());
            realName.setRealName(realNameVo.getRealName());
            realName.setIDCard(realNameVo.getIDCard());
            realName.setPhone(realNameVo.getPhone());
            realName.setImg(realNameVo.getImg());
            realName.setType(realNameVo.getType());
            realName.setRealTime(new Date());
            realName.setCode(code);
            realNameService.addRealName(realName);
        }
            session.setAttribute(Sessionkey.REAL_CODE,code);
            session.setAttribute(Sessionkey.REAL_TYPE,realNameVo.getType());
        return  "redirect:/manager/member";
    }
}
