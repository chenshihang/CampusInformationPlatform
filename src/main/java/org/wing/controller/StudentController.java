package org.wing.controller;

/**
 * Created by HarvestWu on 2017/12/15.
 */

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import org.json.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wing.common.Common;
import org.wing.common.ResponseCode;
import org.wing.common.ResultMap;
import org.wing.entity.*;
import org.wing.service.StudentService;
import org.wing.utils.CommonUtil;
import org.wing.utils.CryptographyUtil;
import org.wing.utils.StringUtil;
import org.wing.viewobject.Articlevo;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.*;
/**
 * 学生控制层
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    /**
     * 学生登录认证
     * @param request
     * @param studentNumber
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultMap<StudentInfo> login(HttpServletRequest request,@RequestParam("studentNumber")String studentNumber,@RequestParam("password")String password ){

        if(studentService.studentIsExistInTable1(studentNumber)){
            String passwordMd5 = CryptographyUtil.md5(password);
            Student student1 = studentService.getStudentByStudentNumber(studentNumber);
            if(student1.getPassword().equals(passwordMd5)){

            }else {
                return ResultMap.createByErrorMessage("密码错误");
            }
        }else {
            StudentInfo studentInfo = studentService.getStudentInfo(studentNumber);
            if(studentInfo == null){
                return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"学号错误，未能获取学生信息");
            }else {
                String identityId = studentInfo.getIdentityId();
                String idTopassword = CommonUtil.identityIdToPassword(identityId);
                if(password.equals(idTopassword)){

                }else {
                    return ResultMap.createByErrorMessage("密码错误");
                }
            }
        }

        StudentInfo studentInfo = studentService.getStudentInfo(studentNumber);
        studentInfo.setIdentityId(null);
        request.getSession().setAttribute(Common.SESSION_STUDENT_NUM,studentNumber);
        request.getSession().setAttribute(Common.CURRENT_STUDENT,studentInfo);
        return ResultMap.createBySuccess("登录成功",studentInfo);
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResultMap logout(HttpServletRequest request){
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createBySuccessMessage("当前没有用户登录");
        }else {
            HttpSession session = request.getSession();
            session.removeAttribute(Common.CURRENT_STUDENT);
            return ResultMap.createBySuccessMessage("退出登录成功");
        }
    }

    /**
     * 修改密码的功能
     * @param request
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public ResultMap resetPassword(HttpServletRequest request,@RequestParam(value = "oldPassword") String oldPassword,@RequestParam(value = "newPassword") String newPassword){
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，请登陆");
        }
        if(StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)){
            return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"参数不能为空");
        }
        if(oldPassword.equals(newPassword)){
            return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"新密码不能与原密码相同");
        }
        if(studentService.studentIsExistInTable1(currentStu.getStudentNumber())){
            Student student = studentService.getStudentByStudentNumber(currentStu.getStudentNumber());
            if(! student.getPassword().equals(CryptographyUtil.md5(oldPassword))){
                return ResultMap.createByErrorMessage("原密码错误");
            }else {
                student.setPassword(CryptographyUtil.md5(newPassword));
                boolean result = studentService.updatePassword(CryptographyUtil.md5(newPassword),currentStu.getStudentNumber());
                if(!result){
                    return ResultMap.createByErrorMessage("修改密码错误");
                }
            }
        }else {
            if(! CommonUtil.identityIdToPassword(currentStu.getIdentityId()).equals(oldPassword)){
                return ResultMap.createByErrorMessage("原密码错误");
            }else {

                Student student = new Student();
                student.setPassword(CryptographyUtil.md5(newPassword));
                student.setStudentNumber(currentStu.getStudentNumber());
                student.setIdCard(currentStu.getIdentityId());
                studentService.insertStudent(student);
            }
        }
        return ResultMap.createBySuccessMessage("修改密码成功");
    }

    /**
     * 学生查询考试安排
     * @param
     * @return
     */
    @RequestMapping(value = "/examSchedule")
    @ResponseBody
    public ResultMap<List<Examination>> examSchedule(HttpServletRequest request){


        System.out.println("进入examSchedule方法--------------------");
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，请登陆");
        }

        List<Examination> examination=studentService.getExamByStudentNumber(currentStu.getStudentNumber());
        if (examination!=null) {
            for (int i = 0; i < examination.size(); i++) {
                if (examination.get(i).getExamMethod().equals("上机考核")) {
                    List<Examination> examinations = studentService.getExamByMap(examination.get(i));
                    int temp = 0;
                    for (int j = 0; j < examinations.size(); j++) {
                        temp++;
                        if (currentStu.getStudentNumber().equals(examinations.get(j).getStudentNumber())) {
                            examination.get(i).setSeatNumber(String.valueOf(temp));
                            System.out.println(temp);
                            break;
                        }
                    }
                }
            }
        }
        return ResultMap.createBySuccess(examination);
    }

    /**
     * 学生查询课表,根据学号和学期查询
     * @param request
     * @param term
     * @return
     */
    @RequestMapping(value = "/queryClass")
    @ResponseBody
    public ResultMap<List<ClassQuery>> queryClass(HttpServletRequest request,@RequestParam(value = "term",defaultValue = "false")String term){
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，请登陆");
        }

        List<ClassQuery>classQueries=new ArrayList<>();
        List<String> courseNumbers=studentService.getCourseNumber(currentStu.getStudentNumber());
        for (int i=0;i<courseNumbers.size();i++){
            ClassQuery classQuery=studentService.getClassQuery(courseNumbers.get(i),term);
            if (classQuery!=null && classQuery.getClassroom()!=null && classQuery.getClassTime()!=null) {
                String a[][] = StringUtil.sub(classQuery.getClassTime(), classQuery.getClassroom());
                for (int j = 0; j < a.length; j++) {
                    ClassQuery query = null;
                    try {
                        query = classQuery.clone();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    assert query != null;
                    query.setClassTime(a[j][0]);
                    query.setClassroom(a[j][1]);
                    System.out.println(query);
                    classQueries.add(query);
                }
            }
        }

        return ResultMap.createBySuccess(classQueries);
    }




    /**
     * 根据学号查询计算机等级考试成绩
     * @return
     */
    @RequestMapping("/queryComputerGradeTwo")
    @ResponseBody
    public ResultMap<List<ComputerGradeTwo>> queryComputerGradeTwo(HttpServletRequest request){
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，请登陆");
        }



        List<ComputerGradeTwo> computerGradeTwos=null;
        if (StringUtils.isNotBlank(currentStu.getStudentNumber())){
            System.out.println("studentNumber："+currentStu.getStudentNumber());
           computerGradeTwos= studentService.getComputerGradeTwo(currentStu.getStudentNumber());
            if (computerGradeTwos!=null&&computerGradeTwos.size()>0){
                return ResultMap.createBySuccess("查询成功",computerGradeTwos);
            }
            else{
                return ResultMap.createBySuccessMessage("您暂时没有成绩信息");
            }
        }else{
            return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"学号不能为空");
        }
    }

    /**
     * 返回管理员发布的公告信息
     * @return
     */
    @RequestMapping("/getArticles")
    @ResponseBody
    public ResultMap<List<Articlevo>> getArticles(HttpServletRequest request){
        //认证是否登录
        StudentInfo currentStu = studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录，请登陆");
        }

        List<Article> articles = studentService.getArticles();
        List<Articlevo> articlevos = new ArrayList<>();
        for(Article article: articles){
            articlevos.add(CommonUtil.articleToVo(article));
        }
        if(articles.size()==0){
            return ResultMap.createByErrorMessage("未获取到公告信息");
        }else {
            return ResultMap.createBySuccess(articlevos);
        }
    }

    /**
     * 根据文章id获取文章信息
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getArticle/{id}")
    @ResponseBody
    public ResultMap<Articlevo> getArticleById(HttpServletRequest request, @PathVariable("id") Integer id){
        StudentInfo currentStu = this.studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登陆");
        }
        Article article = studentService.getArticleById(id);
        if(article == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),"为通过id获取到公告信息");
        }else {
            Articlevo articlevo = CommonUtil.articleToVo(article);
            return ResultMap.createBySuccess(articlevo);
        }
    }

    /**
     * 根据分类，分页信息获取公告
     * @param request
     * @param pageNum
     * @param pagesize
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/getArticlePages")
    @ResponseBody
    public ResultMap<PageInfo<Articlevo>> getArticlePages(HttpServletRequest request,
                                               @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "10")int pagesize,
                                               @RequestParam(value = "categoryId",defaultValue = "0") int categoryId){
        StudentInfo currentStu = this.studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登陆");
        }
        if(categoryId==0){
            return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"categoryId不能为空");
        }
        return studentService.getArticlesByCategoryId(categoryId,pageNum,pagesize);
    }

    /**
     * 查询学生有哪些学期
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTerms")
    @ResponseBody
    public ResultMap<List<String>> getTerms(HttpServletRequest request){
        StudentInfo currentStu = this.studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登陆");
        }

        List<String> terms = studentService.getTermsByStuNum(currentStu.getStudentNumber());
        return ResultMap.createBySuccess(terms);
    }

    /**
     * 查询学生的成绩
     * @param request
     * @param term
     * @return
     */
    @RequestMapping("/getGrades")
    @ResponseBody
    public ResultMap<List<Achievement>> getGrades(HttpServletRequest request,@RequestParam(value = "term") String term){
        StudentInfo currentStu = this.studentService.getCurrentStudent(request);
        if(currentStu == null){
            return ResultMap.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登陆");
        }
        if(term == null || term.length()==0){
            return ResultMap.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),"学期参数错误");
        }
        List<Achievement> achievements = studentService.getGrades(currentStu.getStudentNumber(),term);
        if(achievements.size() == 0){
            return ResultMap.createByErrorMessage("未查询到成绩信息");
        }else {
            return ResultMap.createBySuccess(achievements);
        }
    }

}
