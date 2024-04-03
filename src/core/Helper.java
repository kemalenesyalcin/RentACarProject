package core;

import javax.swing.*;
import java.awt.*;

public class Helper {
    public static void setTheme(){
        for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPaneTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz !";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem Başarılı !";
                title = "Sonuç";
                break;
            case "notFound":
                msg = str + "Kayıt bulunamadı !";
                title = "Bulunamadı";
                break;
            case "error":
                msg = "Hatalı bir işlem yaptınız !";
                title = "Hata";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str){
        optionPaneTR();
        String msg;
        if(str.equals("sure")){
            msg = "Bu işlemi yapmak istediğine emin misin?";
        }else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Emin misin ?",JOptionPane.YES_NO_OPTION) == 0 ? true : false;
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList){
        for(JTextField field: fieldList){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size){
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };

    }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText" , "OK");
        UIManager.put("OptionPane.yesButtonText" , "YES");
        UIManager.put("OptionPane.noButtonText" , "NO");
    }
}