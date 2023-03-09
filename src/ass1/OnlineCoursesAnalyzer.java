package ass1;


import java.util.*;

public class OnlineCoursesAnalyzer {
    public static class course{
        String Institution, CourseBF_Number,Launch_Date,CourseBF_Title,Instructors, CourseBF_Subject,Year,Honor_Code_Certificates,Participants_CourseBF_Content_Accessed,Audited (> 50% CourseBF Content Accessed),Certified,% Audited,% Certified,% Certified of > 50% CourseBF Content Accessed,% Played Video,% Posted in Forum,% Grade Higher Than Zero,Total CourseBF Hours (Thousands),Median Hours for Certification,Median Age,% Male,% Female,% Bachelor's Degree or Higher

    }


    public OnlineCoursesAnalyzer(String datasetPath){

    }
    public Map<String, Integer> getPtcpCountByInst(){

    }
    public Map<String, Integer> getPtcpCountByInstAndSubject(){

    }
    public Map<String, List<List<String>>> getCourseListOfInstructor(){

    }
    public List<String> getCourses(int topK, String by){

    }
    public List<String> searchCourses(String courseSubject, double
            percentAudited, double totalCourseHours){

    }
    public List<String> recommendCourses(int age, int gender, int
            isBachelorOrHigher){

    }

}
