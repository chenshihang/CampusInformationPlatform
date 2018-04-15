package org.wing.entity;

/**
 * Created by wzw on 2018/1/2
 */
/**
 * 成绩查询类
 */
public class Achievement {
    private String studentNumber;//学号
    private String term;//学期号
    private String courseName;//课程名
    private float achievement;//成绩
    private float credit;//学分
    private String valid;//是否有效
    private String courseNature;//课程性质
    private String source;//成绩来源

    public Achievement() {
    }

    public Achievement(String studentNumber, String term, String courseName, float achievement, float credit,
                       String valid, String courseNature, String source) {
        this.studentNumber = studentNumber;
        this.term = term;
        this.courseName = courseName;
        this.achievement = achievement;
        this.credit = credit;
        this.valid = valid;
        this.courseNature = courseNature;
        this.source = source;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getAchievement() {
        return achievement;
    }

    public void setAchievement(float achievement) {
        this.achievement = achievement;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(String courseature) {
        this.courseNature = courseature;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
