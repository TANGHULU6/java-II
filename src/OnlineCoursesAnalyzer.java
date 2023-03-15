import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnlineCoursesAnalyzer {
    final List<course> courses;
    public static class course{
        private String Institution, CourseBF_Number,CourseBF_Title,Instructors, CourseBF_Subject;
        private Date Launch_Date;
        private int Participants_CourseBF_Content_Accessed,Year,Honor_Code_Certificates,Audited_more50_CourseBF_Content_Accessed,Certified;
        private double $Audited,$Certified,$Certified_of_50_CourseBF_Content_Accessed,$Played_Video,$Posted_in_Forum,$Grade_Higher_Than_Zero ,Total_CourseBF_Hours,Median_Hours_for_Certification,$Male,$Female,$Bachelor_Degree_or_Higher;
        private int Median_Age;
        private String pair;


        public String getInstitution() {
            return Institution;
        }

        public String getCourseBF_Number() {
            return CourseBF_Number;
        }

        public String getCourseBF_Title() {
            return CourseBF_Title;
        }

        public String getInstructors() {
            return Instructors;
        }

        public String getCourseBF_Subject() {
            return CourseBF_Subject;
        }

        public Date getLaunch_Date() {
            return Launch_Date;
        }

        public int getParticipants_CourseBF_Content_Accessed() {
            return Participants_CourseBF_Content_Accessed;
        }

        public int getYear() {
            return Year;
        }

        public int getHonor_Code_Certificates() {
            return Honor_Code_Certificates;
        }

        public int getAudited_more50_CourseBF_Content_Accessed() {
            return Audited_more50_CourseBF_Content_Accessed;
        }

        public int getCertified() {
            return Certified;
        }

        public double get$Audited() {
            return $Audited;
        }

        public double get$Certified() {
            return $Certified;
        }

        public double get$Certified_of_50_CourseBF_Content_Accessed() {
            return $Certified_of_50_CourseBF_Content_Accessed;
        }

        public double get$Played_Video() {
            return $Played_Video;
        }

        public double get$Posted_in_Forum() {
            return $Posted_in_Forum;
        }

        public double get$Grade_Higher_Than_Zero() {
            return $Grade_Higher_Than_Zero;
        }

        public double getTotal_CourseBF_Hours() {
            return Total_CourseBF_Hours;
        }

        public double getMedian_Hours_for_Certification() {
            return Median_Hours_for_Certification;
        }

        public double get$Male() {
            return $Male;
        }

        public double get$Female() {
            return $Female;
        }

        public double get$Bachelor_Degree_or_Higher() {
            return $Bachelor_Degree_or_Higher;
        }

        public int getMedian_Age() {
            return Median_Age;
        }

        public course(String institution, String courseBF_Number, String launch_Date, String courseBF_Title, String instructors, String courseBF_Subject, String year,String honor_Code_Certificates,String participants_CourseBF_Content_Accessed,   String audited_more50_CourseBF_Content_Accessed, String certified, String $Audited, String $Certified, String $Certified_of_50_CourseBF_Content_Accessed, String $Played_Video, String $Posted_in_Forum, String $Grade_Higher_Than_Zero, String total_CourseBF_Hours, String median_Hours_for_Certification,  String median_Age,String $Male, String $Female, String $Bachelor_Degree_or_Higher){
            try{
                Institution = institution;
                CourseBF_Number = courseBF_Number;
                CourseBF_Title = courseBF_Title;
                Instructors = instructors;
                CourseBF_Subject = courseBF_Subject;
                pair=institution+"-"+courseBF_Subject;
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Launch_Date = format.parse(launch_Date);
                Participants_CourseBF_Content_Accessed = Integer.parseInt(participants_CourseBF_Content_Accessed);
                Year = Integer.parseInt(year);
                Honor_Code_Certificates = Integer.parseInt(honor_Code_Certificates);
                Audited_more50_CourseBF_Content_Accessed = Integer.parseInt(audited_more50_CourseBF_Content_Accessed);
                Certified = Integer.parseInt(certified);
                this.$Audited = Double.parseDouble($Audited);
                this.$Certified = Double.parseDouble($Certified);
                this.$Certified_of_50_CourseBF_Content_Accessed = Double.parseDouble($Certified_of_50_CourseBF_Content_Accessed);
                this.$Played_Video = Double.parseDouble($Played_Video);
                this.$Posted_in_Forum = Double.parseDouble($Posted_in_Forum);
                this.$Grade_Higher_Than_Zero = Double.parseDouble($Grade_Higher_Than_Zero);
                Total_CourseBF_Hours = Double.parseDouble(total_CourseBF_Hours);
                Median_Hours_for_Certification = Double.parseDouble(median_Hours_for_Certification);
                this.$Male = Double.parseDouble($Male);
                this.$Female = Double.parseDouble($Female);
                this.$Bachelor_Degree_or_Higher = Double.parseDouble($Bachelor_Degree_or_Higher);
                Median_Age = Integer.parseInt(median_Age);
            }catch (Exception e){

            }

        }


        public String getPair() {
            return pair;
        }
    }

   // public static void main(String[] args) {
//        String s="s,s,ss,s, ssadad,dedq,asd";
//        String[] sss=(splitForBug(s));
//        System.out.println(Arrays.toString(sss));
//}


    public Stream<course> getCourses() {
        return courses.stream();
    }

    public OnlineCoursesAnalyzer(String datasetPath) throws IOException {
        courses=Files.lines(Path.of(datasetPath)).filter(sss->!sss.startsWith("Institution")).map(OnlineCoursesAnalyzer::splitForBug).map(b-> new course(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8], b[9], b[10], b[11], b[12], b[13], b[14], b[15], b[16], b[17], b[18], b[19], b[20], b[21], b[22])).toList();
    }
    public Map<String, Integer> getPtcpCountByInst(){
        Map<String, Integer> answer= getCourses().sorted(Comparator.comparing(course::getInstitution))
                .collect(Collectors.groupingBy(course::getInstitution,LinkedHashMap::new,Collectors.summingInt(course::getParticipants_CourseBF_Content_Accessed)));

        return answer;
    }
    public Map<String, Integer> getPtcpCountByInstAndSubject(){


        Map<String, Integer> answer=getCourses().collect(Collectors.groupingBy(course::getPair,()-> {
            return new TreeMap<String,Integer>(Comparator.comparing().reversed().thenComparing());
        },Collectors.summingInt(course::getParticipants_CourseBF_Content_Accessed)));
        List<Map.Entry<String,Integer>> list = new ArrayList<>(answer.entrySet());
        list.sort(Comparator.comparing().reversed().thenComparing());
        return answer;
    }

    public Map<String, List<List<String>>> getCourseListOfInstructor(){
        return null;
    }
    public List<String> getCourses(int topK, String by){
        return null;

    }
    public List<String> searchCourses(String courseSubject, double
            percentAudited, double totalCourseHours){
        return null;

    }
    public List<String> recommendCourses(int age, int gender, int
            isBachelorOrHigher){
        return null;

    }


    protected static String[] splitForBug (String regex){
        String regex1=regex.replaceAll(", ","}");
        String[] temp=regex1.split(",");
        for (int i = 0; i < temp.length; i++) {
            temp[i]=temp[i].replaceAll("}",", ");
        }
        return temp;
    }
}
