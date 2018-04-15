package org.wing.entity;

/**
 * 学生信息类，对应的数据库表为 xlists
 */
public class StudentInfo {
    private Integer id;

    private String studentNumber;

    private String name;

    private String sex;

    private String institute;

    private String grade;

    private String professional;

    private String team;

    private String status;

    private String campus;

    private String identityId;

    private String educationLevel;

    private String nation;

    public StudentInfo(Integer id, String studentNumber, String name, String sex, String institute, String grade, String professional, String team, String status, String campus, String identityId, String educationLevel, String nation) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.sex = sex;
        this.institute = institute;
        this.grade = grade;
        this.professional = professional;
        this.team = team;
        this.status = status;
        this.campus = campus;
        this.identityId = identityId;
        this.educationLevel = educationLevel;
        this.nation = nation;
    }

    public StudentInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber == null ? null : studentNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute == null ? null : institute.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional == null ? null : professional.trim();
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team == null ? null : team.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus == null ? null : campus.trim();
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId == null ? null : identityId.trim();
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel == null ? null : educationLevel.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }
}