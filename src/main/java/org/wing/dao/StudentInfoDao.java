package org.wing.dao;

import org.wing.entity.StudentInfo;

public interface StudentInfoDao {
    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    /**
     * 通过学号获取studentinfo
     * @param studentNumber
     * @return
     */
    StudentInfo getStudentInfo(String studentNumber);
}