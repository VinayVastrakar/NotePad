import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;

class note extends JFrame implements ActionListener{


        JFrame f;
        //textArea Declearation
        JTextArea t;

        //constructor
        note(){
            //Initializing the frame
            f=new JFrame("NotePad");
            //Initializing the textArea
            t=new JTextArea();

            //creating a menubar
            JMenuBar menubar=new JMenuBar();

            //creating file menu
            JMenu file=new JMenu("File");
            //creating optinon for file
            JMenuItem f1=new JMenuItem("New");
            JMenuItem f2=new JMenuItem("Save");
            JMenuItem f3=new JMenuItem("Open");
            JMenuItem f4=new JMenuItem("Print");

            //adding action to each of the options
            f1.addActionListener(this);
            f2.addActionListener(this);
            f3.addActionListener(this);
            f4.addActionListener(this);

            //adding the option to the file menu
            file.add(f1);
            file.add(f2);
            file.add(f3);
            file.add(f4);

            //creating the edit menu
            JMenu edit=new JMenu("Edit");
            //creating optinon for Edit
            JMenuItem e1=new JMenuItem("Cut");
            JMenuItem e2=new JMenuItem("Copy");
            JMenuItem e3=new JMenuItem("Paste");

            //adding action to each of the options
            e1.addActionListener(this);
            e2.addActionListener(this);
            e3.addActionListener(this);

            //adding the option to the Edit menu
            edit.add(e1);
            edit.add(e2);
            edit.add(e3);

            //creating the close button
            JMenuItem close=new JMenuItem("Close");
            close.addActionListener(this);

            menubar.add(file);
            menubar.add(edit);
            menubar.add(close);

            f.setJMenuBar(menubar);
            f.add(t);
            f.setSize(1000,700);
            f.setVisible(true);


        }

    //to add the functionalities
    public void actionPerformed(ActionEvent e) {
        //extracting the command into a string
        String s = e.getActionCommand();

        switch (s) {
            case "New":
                t.setText("");
                break;
            case "Save":
                // creating the object of jfilechooser class with starting path of d
                JFileChooser j=new JFileChooser("D: ");

                //invoke save dialogBox
                int r=j.showSaveDialog(null);
                //r contains status of dialog box 0 if clicked on save
                if(r== 0){
                    //declear a file object
                    File fi=new File(j.getSelectedFile().getAbsolutePath());


                    try{
                        FileWriter fw=new FileWriter(fi);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(t.getText());

                        bw.flush();
                        bw.close();


                    }catch (IOException e1){
                        e1.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(f, "the user has cancelled the operation");
                }

                break;
            case "Open":
                JFileChooser ji=new JFileChooser("D :");

                //invoke saved dialog box
                int ri=ji.showOpenDialog(null);
                //r contains status of dialog box 0 if clicked on save
                if(ri==0){
                    //declear the file object & store file location
                    File fi=new File(ji.getSelectedFile().getAbsolutePath());
                    try{
                        FileReader fr=new FileReader(fi);
                        BufferedReader br=new BufferedReader(fr);
                        String s1="";
                        String s2="";

                        //first line stored in s1
                        s1= br.readLine();
                        while((s2= br.readLine())!=null){
                            s1= s1 + "\n" +s2;
                        }
                        t.setText(s1);

                    }catch (IOException e1){
                        e1.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(f,"The user has cancelled the operation ");
                }


                break;
            case "Print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Cut":
                t.cut();
                break;
            case "Copy":
                t.copy();
                break;
            case "Paste":
                t.paste();
                break;
            case "Close":
                f.setVisible(false);
                break;

        }
    }

    public static void main(String args[]) {
        note n = new note();

    }
}