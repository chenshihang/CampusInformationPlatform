package org.wing.utils;


public class StringUtil {

    /**
     * 对上课的时间和教师进行切割
     * @param classTime
     * @param classRoom
     * @return
     */
    public static String[][] sub(String classTime,String classRoom){
        int count = 0;
        for(int i=0;i<classTime.length();i++){
            if(classTime.charAt(i)=='<') count++;
        }
        if(count==0){
            return null;
        }
        String[][] result = new String[count][2];
        String[] times = classTime.split("<br>");
        String[] rooms = classRoom.split("<br>");
        String[] resultTimes = new String[count];
        for(int i=0;i<count;i++){
            resultTimes[i] = times[i].split(",")[0];
        }
        for(int i=0;i<count;i++){
            result[i][0]=resultTimes[i];
            result[i][1]=rooms[i];
        }
        return result;
    }

    public static void main(String[] args) {
        String text="1-1,1-2<br>3-3,3-4<br>5-5,5-6<br>";//上课时间
        String str="10-A301<br>10-B401<br>10-B403<br>";//上课教室
        StringUtil.sub(text,str);
    }
}
