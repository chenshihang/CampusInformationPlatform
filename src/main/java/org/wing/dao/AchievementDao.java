package org.wing.dao;
/**
 * Created by wzw on 2018/1/2
 */

import org.apache.ibatis.annotations.Param;
import org.wing.entity.Achievement;
import java.util.List;

/**
 * 成绩查询dao层
 */
public interface AchievementDao {
    /**
     * 根据学号查询学期
     * @param studentNumber
     * @return
     */
    List<Achievement> getTermNumber(String studentNumber);

    /**
     * 根据学期号查询各科成绩
     * @param
     * @return
     */
    List<Achievement> getAchievement(@Param("studentNumber") String studentNumber,@Param("term") String term);
}
