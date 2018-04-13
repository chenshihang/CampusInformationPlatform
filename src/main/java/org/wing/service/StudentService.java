package org.wing.service;

/**
 * Created by HarvestWu on 2017/12/15.
 */

import org.wing.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 学生服务层接口
 */
public interface StudentService {

    /**+9
     * 根据学号和身份证号验证学生是否存在
     * @param studentNumber
     * @param idCard
     * @return
     */
    int judgeExistStudent(String studentNumber,String idCard);

    /**
     * 根据学号查询学生认证信息
     * @param studentNumber
     * @return
     */
    Student getStudentByStudentNumber(String studentNumber);

    /**
     * 注册学生认证信息
     * @param student
     */
    void insertStudent(Student student);

    /**
     * 更新学生认证信息
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 学生根据学号查询考试信息
     * @param studentNumber
     * @return
     */
    List<Examination> getExamByStudentNumber(String studentNumber);

    /**
     * 根据考试周次，星期，节数，教室查找，并按学好排序
     * @param examination
     * @return
     */
    List<Examination> getExamByMap(Examination examination);

    /**
     * 根据学号查询课程编号
     * @param studentNumber
     * @return
     */
    List<String> getCourseNumber(String studentNumber);

    /**
     * 根据课程编号和学期查询课程信息
     * @param courseNumber
     * @return
     */
    ClassQuery getClassQuery(String courseNumber,String term);


    /**
     * 获取公告信息，不包括公告内容
     * @return
     */
    List<Article> getArticles();

    /**
     * 根据id查询相应公告，并返回
     * @param id
     * @return
     */
    Article getArticleById(Integer id);

    List<ComputerGradeTwo> getComputerGradeTwo(String studentNumber);

    /**
     * 通过request 获取当前登录的学生用户
     * @param request
     * @return
     */
    Student getCurrentStudent(HttpServletRequest request);
}
