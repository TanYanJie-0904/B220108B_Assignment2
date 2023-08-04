public class DiplomaData {

    public  String num;
    public String category;

    public String name;

    public String   year14,
                    year15,
                    year16,
                    year17,
                    year18,
                    year19;

    public static int min=0,max=0, total =0;




    public DiplomaData(String num,String category, String name, String year14, String year15, String year16, String year17, String year18, String year19) {
        this.num = num;
        this.category = category;
        this.name = name;
        this.year14 = year14;
        this.year15 = year15;
        this.year16 = year16;
        this.year17 = year17;
        this.year18 = year18;
        this.year19 = year19;


    }

    @Override
    public String toString() {

        return String.format("%-2s %-25s %-48s %10s %10s %10s %10s %10s %10s\n",num, category, name, year14, year15, year16, year17, year18, year19);
    }

    public  int getTotal(int[] list) {
        int total = 0;
        for (int num : list) {
            total += num;
        }
        return total;
    }



    public  int getMax(int[] list) {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<6;i++){
            if(list[i]>max){
                max=list[i];
            }
        }
        return max;
    }

    public  int getMin(int[] list) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<6;i++){
            if(list[i]<min){
                min=list[i];
            }
        }
        return min;
    }

    public void setMinMaxTotal(){
        int []findMaxMinTotal = new int[]{Integer.parseInt(year14),Integer.parseInt(year15),Integer.parseInt(year16),Integer.parseInt(year17),Integer.parseInt(year18),Integer.parseInt(year19)};

        DiplomaData.max=getMax(findMaxMinTotal);
        DiplomaData.min=getMin(findMaxMinTotal);
        DiplomaData.total=getTotal(findMaxMinTotal);
    }



}
