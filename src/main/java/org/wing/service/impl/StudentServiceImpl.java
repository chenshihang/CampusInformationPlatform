package org.wing.service.impl;

/**
 * Created by HarvestWu on 2017/12/15.
 */


import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wing.common.Common;
import org.wing.dao.*;
import org.wing.entity.*;
import org.wing.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生服务层接口实现层
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private AchievementDao achievementDao;

    @Autowired
    private StudentInfoDao studentInfoDao;

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExaminationDao examinationDao;
    @Autowired
    private ClassQueryDao classQueryDao;
    @Autowired
    private StudentSelectDao studentSelectDao;
    @Autowired
    private ComputerGradeTwoDao computerGradeTwoDao;
    @Autowired
    private ArticleDao articleDao;
    /**
     * 根据学号和身份证号验证学生是否存在
     * @param studentNumber
     * @param idCard
     * @return
     */
    public int judgeExistStudent(String studentNumber, String idCard) {
        if (idCard.equals(studentDao.getIdCardByStudentNumber(studentNumber))){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 根据学号查询学生认证信息
     * @param studentNumber
     * @return
     */
    public Student getStudentByStudentNumber(String studentNumber) {
        return studentDao.getStudentByStudentNumber(studentNumber);
    }

    /**
     * 注册学生认证信息
     * @param student
     */
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    /**
     * 更新学生认证信息
     * @param student
     */
    public void updateStudent(Student student) {

    }

    /**
     * 学生根据学号查询考试安排
     * @param studentNumber
     * @return
     */
    public List<Examination> getExamByStudentNumber(String studentNumber) {
        return examinationDao.getExamByStudentNumber(studentNumber);
    }
    /**
     * 根据考试周次，星期，节数，教室查找，并按学好排序
     * @param examination
     * @return
     */
    @Override
    public List<Examination> getExamByMap(Examination examination) {
        return examinationDao.getExamByMap(examination);
    }

    /**
     * 根据学生学号查询课程编号
     * @param studentNumber
     * @return
     */
    @Override
    public List<String> getCourseNumber(String studentNumber) {
        return studentSelectDao.getCourseNumber(studentNumber);
    }

    /**
     * 根据课程编号和学期查询课程信息
     * @param courseNumber
     * @return
     */
    @Override
    public ClassQuery getClassQuery(String courseNumber,String term) {
        return classQueryDao.getClassQuery(courseNumber,term);
    }

    @Override
    public List<ComputerGradeTwo> getComputerGradeTwo(String studentNumber) {
        return computerGradeTwoDao.getComputerGradeTwo(studentNumber);
    }

    public  List<Article> getArticles(){

        List<Article> articles = articleDao.getRecentArticles();
        return articles;
    }

    public Article getArticleById(Integer id){
        Article article = articleDao.selectByPrimaryKey(id);
        return article;
    }

    public StudentInfo getCurrentStudent(HttpServletRequest request){
        HttpSession session = request.getSession();
//        String stuNumber = (String) session.getAttribute(Common.SESSION_STUDENT_NUM);
        StudentInfo studentInfo = (StudentInfo)session.getAttribute(Common.CURRENT_STUDENT);

//        System.out.println("getcurrentStudent 获得stuNumber="+stuNumber);
//        Student student = this.getStudentByStudentNumber(stuNumber);
        return studentInfo;
    }

    public List<String> getTermsByStuNum(String studentNumber){
        List<Achievement>  achievements = achievementDao.getTermNumber(studentNumber);
        System.out.println("查询到学生学期信息，共有"+achievements.size()+"ge xue qi");
        List<String> result = new ArrayList<>();
        for(Achievement achievement: achievements){
            System.out.println(achievement.getTerm());
            result.add(achievement.getTerm());
        }
        return result;
    }

    public List<Achievement> getGrades(String studentNumber,String term){

        System.out.println("service 查看学号为"+studentNumber+" term="+term);
        List<Achievement> result = achievementDao.getAchievement(studentNumber,term);
        for(Achievement achievement: result){
            System.out.println(achievement.getCredit());
        }
        return result;
    }

    public boolean studentIsExistInTable1(String studentNumber){

        int count =  studentDao.studentIsExistInTable1(studentNumber);
        if(count==0){
            return false;
        }else {
            return true;
        }
    }

    public StudentInfo getStudentInfo(String studentNumber) {
        return studentInfoDao.getStudentInfo(studentNumber);
    }

}
