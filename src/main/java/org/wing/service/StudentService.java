package org.wing.service;

import com.github.pagehelper.PageInfo;
import org.wing.common.ResultMap;
import org.wing.entity.*;
import org.wing.viewobject.Articlevo;

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
    StudentInfo getCurrentStudent(HttpServletRequest request);


    /**
     * 根据学号获取各个学期
     * @param studentNumber
     * @return
     */
    List<String> getTermsByStuNum(String studentNumber);

    /**
     * 根据学生学号和学期查询各科
     * @param studentNumber
     * @param term
     * @return
     */
    List<Achievement> getGrades(String studentNumber,String term);

    /**
     * 根据学号判断学生是否存在于学生认证表中
     * @param studentNumber
     * @return
     */
    boolean studentIsExistInTable1(String studentNumber);

    /**
     * 根据学号获取学生studentInfo信息
     * @param studentNumber
     * @return
     */
    StudentInfo getStudentInfo(String studentNumber);

    boolean updatePassword(String password,String studentNumber);


    /**
     * 根据categoryId以及分页参数查询文章信息
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultMap<PageInfo<Articlevo>> getArticlesByCategoryId(int categoryId, int pageNum, int pageSize);
}
