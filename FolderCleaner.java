import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class FolderCleaner {
    public static void main(String[] args){
        Scanner scin = new Scanner(System.in);
        System.out.println("Where To Clean(Enter Full Path): ");
        String path = scin.nextLine();
        System.out.println("Delete Old Files After Putting Them In Folder[Y/n]: ");
        String delete = scin.next();
        File file = new File(path);
        File[] list = file.listFiles();
        for (File x : list){
            String extension = "";
            int i = x.getName().lastIndexOf('.');
            if (i > 0){
                extension = x.getName().substring(i+1);
            }
            File file1 = new File(path+"/"+extension);
            file1.mkdir();
            String pathOfNewFile = path+"/"+extension+"/"+x.getName();
            File file2 = new File(pathOfNewFile);
            int byteContent;
            String fileContent = "";
            try {
                file2.createNewFile();
                FileInputStream fileInputStream = new FileInputStream(x.getPath());
                FileOutputStream fileOutputStream = new FileOutputStream(pathOfNewFile);
                while ((byteContent = fileInputStream.read())!=-1){
                    fileContent+=(char)byteContent;
                }
                fileOutputStream.write(fileContent.getBytes());
                if (
                        delete.matches("Y") && !x.isDirectory()
                ) {
                    System.out.println("Deleting... "+x.getName());
                    x.delete();
                }

            } catch (Exception ignored){}
        }
        System.out.println("Task Completed :-)");
    }
}
