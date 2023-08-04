import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File diploma= new File("diploma.csv");

        int index = 0;

        char symbol = '-';
        int repeatCount = 148;

        String repeatedString = String.valueOf(symbol).repeat(repeatCount);


        ArrayList<DiplomaData> diplomaData = new ArrayList<>();
        
        ArrayList<String>   DiplomaLanjutan= new ArrayList<>(),
                            KursusPengkhususan= new ArrayList<>(),
                            Categories= new ArrayList<>();



        if (diploma.exists()){
            System.out.println("File exists");

            if(diploma.canRead()){
                System.out.println("File is readable");
            }else{
                System.out.println("File is unreadable");
            }

            try(Scanner reader = new Scanner(diploma)){
                System.out.format("%-2s %-24s %-48s %10s %10s %10s %10s %10s %10s\n","No.","Category", "Name", "2014", "2015", "2016", "2017", "2018", "2019");
                while (reader.hasNext()) {
                    String line = reader.nextLine();
                    String[] items = line.split(",");

                    if (index > 0) {
                        DiplomaData data = new DiplomaData(items[0],items[1], items[2], items[3], items[4], items[5],items[6],items[7],items[8]);

                        diplomaData.add(data);


                        System.out.println(data);

                    }

                    index++;


                }


                for (DiplomaData row: diplomaData) {
                    String name = row.category;

                    row.setMinMaxTotal();

                    if (!(Categories.contains(row.category))) {
                        Categories.add(row.category);
                    }

                    if (name.startsWith(" Diploma Lanjutan")) {
                        DiplomaLanjutan.add(String.format("%-4s %-48s %4s %10s %10s %10s %10s %10s %10s %10s %10s\n",row.num,row.name,row.year14,row.year15, row.year16,row.year17,row.year18,row.year19,DiplomaData.max,DiplomaData.min,DiplomaData.total));
                    }

                    if (name.startsWith("Kursus Pengkhususan")) {
                        KursusPengkhususan.add(String.format("%-4s %-48s %4s %10s %10s %10s %10s %10s %10s %10s %10s\n",row.num,row.name,row.year14,row.year15, row.year16,row.year17,row.year18,row.year19,DiplomaData.max,DiplomaData.min,DiplomaData.total));
                    }



                }



                System.out.println();

                System.out.println("Number of Diploma Lanjutan: " + DiplomaLanjutan.size());
                System.out.println(repeatedString);
                System.out.format("%-4s %-48s %4s %10s %10s %10s %10s %10s  %10s %9s %10s\n","No.","Name","2014","2015","2016","2017","2018","2019","Maximum","Minimum","Total");
                System.out.println(repeatedString);
                for (String diplomaLanjutan: DiplomaLanjutan){
                    System.out.println(diplomaLanjutan);
                }
                System.out.println(repeatedString);

                System.out.println();

                System.out.println("Number of Kursus Pengkhususan: " + KursusPengkhususan.size());
                System.out.println(repeatedString);
                System.out.format("%-4s %-48s %4s %10s %10s %10s %10s %10s %10s %10s %10s\n","No.","Name","2014","2015","2016","2017","2018","2019","Maximum","Minimum","Total");
                System.out.println(repeatedString);
                for (String kursusPengkhususan: KursusPengkhususan){
                    System.out.println(kursusPengkhususan);
                }
                System.out.println(repeatedString);



                for (String category: Categories){
                    try(PrintWriter writer = new PrintWriter(new File(category + ".txt"))){
                        writer.format("%-6s %-30s %-48s %10s %10s %10s\n","No.","Category","Name", "Maximum", "Minimum","Total");
                        for (DiplomaData row: diplomaData){
                            row.setMinMaxTotal();
                            if (category.equals(row.category)){
                                writer.format("%-5s %-31s %-48s %10s %10s %10s \n",row.num, row.category, row.name,row.max,row.min,row.total);
                            }
                        }
                    }catch (FileNotFoundException exception){
                        System.out.println(exception.getMessage());
                    }
                }



            }catch(FileNotFoundException exception){
                System.out.println(exception.getMessage());
            }
        }else{
            System.out.println("File does not exist");
        }
    }

}