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
        private String[] InstructorArray;
        private double similarity;

        public double getSimilarity() {
            return similarity;
        }

        public void setSimilarity(double similarity) {
            this.similarity = similarity;
        }

        public String[] getInstructorArray() {
            return InstructorArray;
        }

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
                CourseBF_Title = courseBF_Title.replaceAll("\"","");
                Instructors = instructors.replaceAll("\"","");
                InstructorArray=Instructors.split(", ");
                CourseBF_Subject = courseBF_Subject.replaceAll("\"","");
                pair=Institution+"-"+CourseBF_Subject;
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


        Map<String, Integer> answer1=getCourses().collect(Collectors.groupingBy(course::getPair,Collectors.summingInt(course::getParticipants_CourseBF_Content_Accessed)));
        List<Map.Entry<String,Integer>> list = new ArrayList<>(answer1.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o2.getValue().compareTo(o1.getValue())==0){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return  o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String, Integer> answer=list.stream().collect(Collectors.toMap(Map.Entry<String,Integer>::getKey,Map.Entry<String,Integer>::getValue,
                (v1,v2)->v1, LinkedHashMap::new));
        return answer;
    }

    public Map<String, List<List<String>>> getCourseListOfInstructor(){
        Map<String, List<List<String>>> answer = new HashMap<>();
        Map<String, List<String>> answer3= getCourses().collect(
                  Collectors.groupingBy(course::getInstructors,LinkedHashMap::new,Collectors.mapping(course::getCourseBF_Title,Collectors.toList()))
            );

        answer3.forEach((k,v)->
                {   String[] dd=k.split(", ");
                    if(dd.length==1){
                        List<List<String>> List=new LinkedList<>();
                        List<String> List0 = new ArrayList<>();
                        List<String> List1 = new ArrayList<>();
                        List.add(List0);List.add(List1);
                        List0.addAll(v);
                        answer.merge(dd[0],List, (o,n)->{
                            n.get(0).addAll(o.get(0));
                            n.get(1).addAll(o.get(1));
                            Collections.sort(n.get(0));
                            return n;
                        });


                    }else {
                        for (String si :
                                dd) {
                            List<List<String>> List=new LinkedList<>();
                            List<String> List0 = new ArrayList<>();
                            List<String> List1 = new ArrayList<>();
                            List.add(List0);List.add(List1);
                            List1.addAll(v);
                            answer.merge(si,List, (o,n)->{
                                n.get(0).addAll(o.get(0));
                                n.get(1).addAll(o.get(1));
                                Collections.sort(n.get(0));Collections.sort(n.get(1));
                                return n;
                            });

                        }

                    }
                }
        );

        answer.forEach(
                (k,v)->{
                    List new0=v.get(0).stream().distinct().collect(Collectors.toList());
                    v.get(0).clear();v.get(0).addAll(new0);
                    List new1=v.get(1).stream().distinct().collect(Collectors.toList());
                    v.get(1).clear();v.get(1).addAll(new1);
                    Collections.sort(v.get(0));Collections.sort(v.get(1));
                }
        );

        return answer;
    }
    public List<String> getCourses(int topK, String by){
        List<String> answer=null ;
       switch (by){
           case "hours":
               answer=getCourses().sorted(Comparator.comparing(course::getTotal_CourseBF_Hours).reversed().thenComparing(course::getCourseBF_Title)).map(course::getCourseBF_Title).distinct().limit(topK).toList();
               break;
           case"participants":
               answer=getCourses().sorted(Comparator.comparing(course::getParticipants_CourseBF_Content_Accessed).reversed().thenComparing(course::getCourseBF_Title)).map(course::getCourseBF_Title).distinct().limit(topK).toList();
               break;
           default:System.exit(-1);
               break;
       }
    return answer;
    }
    public List<String> searchCourses(String courseSubject, double
            percentAudited, double totalCourseHours){
        List<String> answer=null ;
        answer=getCourses()
                .filter(c->c.getCourseBF_Subject().toLowerCase().contains(courseSubject.toLowerCase()))
                .filter(c->c.get$Audited()>=percentAudited)
                .filter(c->c.getTotal_CourseBF_Hours()<=totalCourseHours)
                .sorted(Comparator.comparing(course::getCourseBF_Title))
                .map(course::getCourseBF_Title)
                .distinct()
                .toList();

        return answer;

    }
    public List<String> recommendCourses(int age, int gender, int
            isBachelorOrHigher){
        List<String> answer=new ArrayList<>() ;
        Map<String, List<course>> temp = getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number));
//        Map temp_Age  =getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.summingInt(course::getMedian_Age)));
//        Map temp_cnt  =getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.counting()));
//        Map temp_Male  =getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.summingDouble(course::get$Male)));
//        Map temp_bachelor =getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.summingDouble(course::get$Bachelor_Degree_or_Higher)));
//        Map average_Age = getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number))
//                .values().stream().mapToInt(c-> {
//                     Map<String, Double> dddd= c.stream().collect(Collectors.groupingBy(course::getCourseBF_Number, Collectors.averagingInt(course::getMedian_Age)));
//                     return dddd.values().stream().mapToInt().sum();
//
//                }).average();
        Map<String,Double> average_Age = getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.averagingInt(course::getMedian_Age)));
        Map<String,Double> average_Male  = getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.averagingDouble(course::get$Male)));
        Map<String,Double> average_bachelor = getCourses().collect(Collectors.groupingBy(course::getCourseBF_Number,Collectors.averagingDouble(course::get$Bachelor_Degree_or_Higher)));
        Map<String,Double> similarity = new HashMap<>();
        for (String key:
                temp.keySet()) {
            double ageT=average_Age.get(key);
            double $maleT=average_Male.get(key);
            double bachelorT=average_bachelor.get(key);
            double sim=(age-ageT)*(age-ageT)+(gender*100-$maleT)*(gender*100-$maleT)+(isBachelorOrHigher*100-bachelorT)*(isBachelorOrHigher*100-bachelorT);
            similarity.put(key,sim);
            temp.get(key).forEach(co->co.setSimilarity(sim));
        }
//        List<String> course10Num=similarity.entrySet().stream().sorted(new Comparator<Map.Entry<String, Double>>() {
//            @Override
//            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                if(o2.getValue().compareTo(o1.getValue())==0){
//                    return 0;
//                }
//                return  o1.getValue().compareTo(o2.getValue());
//            }
//        }).map(Map.Entry::getKey).toList();
//        for (String key :
//                course10Num) {
//            answer.add(temp.get(key).stream().sorted(Comparator.comparing(course::getLaunch_Date).reversed()).toList().get(0).getCourseBF_Title());
//            answer=answer.stream().distinct().collect(Collectors.toList());
//            if(answer.size()==10){
//                break;
//            }
//        }
        answer=getCourses().sorted(Comparator.comparing(course::getSimilarity).thenComparing(course::getCourseBF_Title))

                .map(n->{
                    return temp.get(n.getCourseBF_Number()).stream().sorted(Comparator.comparing(course::getLaunch_Date).reversed()).toList().get(0);
                })
                .map(course::getCourseBF_Title)
                .distinct()
                .limit(10)
                .toList();
        return answer;

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
