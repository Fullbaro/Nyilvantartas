package nyilvantartas;

import Fullbaro.flbr;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class pBeallitasok extends javax.swing.JPanel {

    // VÁLTOZÓK
    
    public static int igazitas;
    
    String sql;
    
    Vector<String[]> v = new Vector();
    
    // VÁLTOZÓK VÉGE
    
    public pBeallitasok() {
        initComponents();
        igazitas();
    }

    // METÓDUSOK
    
    void igazitas(){
        sql="select igazitas from beallitasok";
        v=flbr.lekerdez(sql);
        igazitas=Integer.parseInt(v.get(0)[0]);
        
        if(igazitas==0)
            jRadioButton4.setSelected(true);
        else if(igazitas==15)
            jRadioButton1.setSelected(true);
        else if(igazitas==30)
            jRadioButton2.setSelected(true);
        else if(igazitas==60)
            jRadioButton3.setSelected(true);
        
        sql="select kontakt from beallitasok";
        v=flbr.lekerdez(sql);
        jTextField1.setText(v.get(0)[0]);
        
    }
    
    void kikuld(){
        String szoveg="";
        
        Vector<String> idk = new Vector();
        sql="select id from alkalmazott";
        v=flbr.lekerdez(sql);
        for(String[] s:v) idk.add(s[0]);
        
        for(String s:idk){
            szoveg+="<table style=\"border-collapse: collapse;\">";
            double ora=0;
            int penz=0;
            sql="select nev, oraber, ettol, eddig from orak inner join Alkalmazott on Alkalmazott.id = orak.alkid where fizetve like 0 and eddig not like '-' and alkid = "+s;
            v=flbr.lekerdez(sql);
            
            for(String[] a:v){
                ora+=pOraSor.oraSzamol(a[2], a[3]);
                penz+=(Integer.parseInt(a[1])*pOraSor.oraSzamol(a[2], a[3]));   
            }
                        
            v=flbr.lekerdez(sql);
            
            if(v.size()>0){
                szoveg+="<tr><th style=\"border: 1px solid black; padding: 15px;\">"+v.get(0)[0]+"</th><th style=\"border: 1px solid black; padding: 15px;\">"+v.get(0)[1]+" Ft/óra</th><th style=\"border: 1px solid black; padding: 15px;\">Összesen "+ora+" Óra</th><th style=\"border: 1px solid black; padding: 15px;\">Összesen "+penz+" Ft</th></tr>";    
            }
            
            for(String[] a:v){
                szoveg+="<tr><td style=\"border: 1px solid black; padding: 15px;\">"+a[2]+"-tól/től</td><td style=\"border: 1px solid black; padding: 15px;\">"+a[3]+"-ig</td><td style=\"border: 1px solid black; padding: 15px;\">"+pOraSor.oraSzamol(a[2], a[3])+" Óra</td><td style=\"border: 1px solid black; padding: 15px;\">"+(int)(Integer.parseInt(a[1])*pOraSor.oraSzamol(a[2], a[3]))+" Ft</td></tr>";
                ora+=pOraSor.oraSzamol(a[2], a[3]);
                penz+=(Integer.parseInt(a[1])*pOraSor.oraSzamol(a[2], a[3]));
            }
            
            
            
            ora=0;
            penz=0;
            szoveg+="</table><br/><br/>";
        }
        
        boolean siker = flbr.emailKuld("Pénzügy", szoveg, jTextField1.getText(), "pizzakert.gyorok@gmail.com", "Balintcs");
        if(siker)
            flbr.uzen("Az email sikeresen elküldve!", "Kész");
        else
            flbr.hiba("Hiba a küldés során!\nEllenőrizze az internet kapcsolatot.", "Hiba");
        
        sql="update beallitasok set kontakt='"+jTextField1.getText()+"'";
        flbr.vegrehajt(sql);
    }
    
    // METÓDUSOK VÉGE
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(20, 32767));
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 100, 50, 100));
        jPanel3.setLayout(new java.awt.GridLayout(6, 0, 10, 40));

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Alkalmazott felvétele");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Adatmódosítás");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton4.setBackground(new java.awt.Color(255, 153, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Utólagos műszak hozzáadása");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Munkaidő kezdetének és végének igazítása");
        jPanel4.add(jLabel1);

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton1.setText("Negyed");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton1);

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton2.setText("Fél");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton2);

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButton3.setText("Egész");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("órához.");
        jPanel4.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nincs igazítás");
        jPanel4.add(jLabel3);

        jRadioButton4.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton4);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jRadioButton4);

        jPanel3.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Kontakt email: ");
        jPanel5.add(jLabel4);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField1);
        jPanel5.add(filler1);

        jButton5.setBackground(new java.awt.Color(255, 153, 0));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Nyilvántartás kiküldése");
        jButton5.setPreferredSize(new java.awt.Dimension(191, 40));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5);

        jPanel3.add(jPanel5);

        jButton6.setBackground(new java.awt.Color(255, 153, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Program frissítése a legújabb verzióra");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Vissza");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Vissza gomb
        Foablak.retegek.show(Foablak.pHatter, "Menu");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        igazitas=30;
        sql="update beallitasok set igazitas="+igazitas;
        flbr.vegrehajt(sql);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Alkalmazott felvétele gomb
        
        Foablak.retegek.show(Foablak.pHatter, "Felvesz");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Módosítás gomb
        
        Foablak.retegek.show(Foablak.pHatter, "Modosit");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        igazitas=15;
        sql="update beallitasok set igazitas="+igazitas;
        flbr.vegrehajt(sql);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        igazitas=60;
        sql="update beallitasok set igazitas="+igazitas;
        flbr.vegrehajt(sql);
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        igazitas=0;
        sql="update beallitasok set igazitas="+igazitas;
        flbr.vegrehajt(sql);
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // utólagos gomb
        
        Foablak.retegek.show(Foablak.pHatter, "UtolagosMuszak");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        kikuld();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            flbr.uzen("A program újra fog indulni", "Frissítés");
            flbr.cmd("java -jar Updater.jar");           
            System.exit(0);
        }catch(Exception e){
            e.printStackTrace();
            flbr.hiba("Hiba a program frissítése közben!\nVegye fel a kapcsolatot a rendszerazdával", "Hiba");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
