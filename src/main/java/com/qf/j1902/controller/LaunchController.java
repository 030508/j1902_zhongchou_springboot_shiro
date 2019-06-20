package com.qf.j1902.controller;

import com.qf.j1902.pojo.Category;
import com.qf.j1902.pojo.Label;
import com.qf.j1902.service.CategoryService;
import com.qf.j1902.service.LabelService;
import com.qf.j1902.vo.ProjectInfoVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiresPermissions( value = {"认证用户"})
public class LaunchController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private  LabelService labelService;
    @GetMapping("/start")
    public String start(){

        return "start";
    }
    @GetMapping("/start-step-1")
    public String start_step_1(Model model){
        List<Category> categories = categoryService.categorys();
        model.addAttribute("categories",categories);
        return "start-step-1";
    }
    @GetMapping("/labelTags")
    @ResponseBody
    public  List<Label> labelTags(String cname){
        List<Label> labels = labelService.labels(cname);
        return labels;
    }
    @PostMapping("/start-step-2")
    public String start_step_2(@RequestParam("headimg") MultipartFile mf,@RequestParam("detailsimg") MultipartFile mf2,
                               @RequestParam(value = "tags" ,defaultValue = "[]") List<String> tags , ProjectInfoVo projectInfoVo ,Model model){
        String headimg = imageUpload(mf ,model);
        String detailsimg = imageUpload(mf2,model);
        if (headimg.equals("url") || detailsimg.equals("url")){
            return "start-step-1";
        }
        if (tags.size() == 0){
            return "start-step-1";
        }
        System.out.println(tags);
        System.out.println(projectInfoVo);

        return "start-step-2";
    }
    @GetMapping("/start-step-3")
    public String start_step_3(){
        return "start-step-3";
    }
    @GetMapping("/start-step-4")
    public String start_step_4(){
        return "start-step-4";
    }

    private String imageUpload(MultipartFile mf,  Model model) {
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
}
