package nyilvantartas;

import Fullbaro.flbr;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;


public class pUtolagosMuszak extends javax.swing.JPanel {

    // VÁLTOZÓK
    
    static String sql;
    
    static Vector<String[]> v = new Vector();
    
    Date date;
    
    // VÁLTOZÓK VÉGE
    
    // KOMPONENSEK
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
    
    // KOMPONENSEK VÉGE
    
    
    public pUtolagosMuszak() {
        initComponents();
        epit();
        feltolt();
    }

 
    
    // METÓDUSOK
    
    void epit(){
        jButton2.setBorder(BorderFactory.createCompoundBorder(
               BorderFactory.createLineBorder(Color.BLACK, 1),
               BorderFactory.createLineBorder(new Color(255,153,0), 20)));
    }
    
    
    public static void feltolt(){
        jComboBox2.removeAllItems();
        
        sql="select nev from alkalmazott order by nev";
        v=flbr.lekerdez(sql);
        
        for(String[] s:v)
            jComboBox2.addItem(s[0]);
        
        

    }

    
    void hozzaad(){
        sql="select id from alkalmazott where nev like '"+jComboBox2.getSelectedItem()+"'";
        v=flbr.lekerdez(sql);
        int id=Integer.parseInt(v.get(0)[0]);
        
        try{
            Date tol = sdf.parse(igazitFel(sdf2.format(jDateChooser3.getDate())+" "+jTextField1.getText())); 
            System.out.println("\n"+sdf.format(tol));
            Date ig;
            long lIg;
            long lTol= tol.getTime();            
            long lMost = new Date().getTime();
            
            System.out.println(jDateChooser2.getDate()+" <-");
            if(jDateChooser2.getDate()!=null && !jTextField2.getText().equals("")){
                ig = sdf.parse(igazitLe(sdf2.format(jDateChooser2.getDate())+" "+jTextField2.getText()));
                System.out.println(sdf.format(ig));
                lIg = ig.getTime();
                
                if(lIg<=lTol){
                    flbr.hiba("A dátum nem lehet régebbi vagy azonos a kezdés dátumával!\nVedd figyelembe a műszakok igazítását", "Hiba");
                }else{
                    sql="insert into orak(ettol, eddig, alkid) values ('"+sdf.format(tol)+"', '"+sdf.format(ig)+"', "+id+")";
                    flbr.vegrehajt(sql);
                    flbr.uzen("Sikeresen hozzáadva", "Kész");
                    jTextField1.setText("10:00");
                    jDateChooser3.setCalendar(null);
                    jDateChooser2.setCalendar(null);
                    jTextField2.setText("");
                    pKifizetes.kiir();
                }
                
            }else{
                sql="select nev from alkalmazott where id like "+id+" and dolgozik = 1";
                v=flbr.lekerdez(sql);
                if(v.size()>0)
                    flbr.hiba("Ő már jelenleg dolgozik!", "Hiba");
                else{
                
                    sql="insert into orak(ettol, eddig, alkid) values('"+sdf.format(tol)+"', '-', "+id+")";
                    flbr.vegrehajt(sql);
                    sql="update alkalmazott set dolgozik=1 where id like "+id;
                    flbr.vegrehajt(sql);

                    flbr.uzen("Sikeresen hozzáadva", "Kész");
                    jTextField1.setText("10:00");
                    jDateChooser3.setCalendar(null);
                    jDateChooser2.setCalendar(null);   
                    pMuszakok.feltolt();
                }
            }
                                                            
        }catch(Exception e){
            flbr.hiba("Hibás dátum szintaxis", "hiba");
            e.printStackTrace();
        }
    }
    
    String igazitLe(String teljesDatum){
                
        int ora = Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(" ")+1, teljesDatum.indexOf(":")));
        int perc=Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(":")+1));
        
        
        if(pBeallitasok.igazitas==15){
            if(perc<15)
                perc=0;
            else if(perc>15 && perc<30)
                perc=15;
            else if(perc>30 && perc<45)
                perc=30;                       
            else if(perc>45 && perc<=59)
                perc=45;
                
        }else if(pBeallitasok.igazitas==30){
            if(perc<30)
                perc=0;
            else if(perc>30 && perc<=59)
                perc=30;
            
        }else if(pBeallitasok.igazitas==60)
            if(perc>0)
                perc=0;

        
        teljesDatum=teljesDatum.substring(0,teljesDatum.indexOf(":"))+":"+perc;
        if(perc==0) teljesDatum+="0";
        
        return teljesDatum;
    }
    
    String igazitFel(String teljesDatum){
                
        int ora = Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(" ")+1, teljesDatum.indexOf(":")));
        int perc=Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(":")+1));
        
        try{
            date = sdf.parse(teljesDatum);
        }catch(Exception e){e.printStackTrace();}
        
        long miliben = date.getTime();
        
                
        if(pBeallitasok.igazitas==15){
            if(perc<15 && perc>0)
                perc=15;
            else if(perc>15 && perc<30)
                perc=30;
            else if(perc>30 && perc<45)
                perc=45;
            else if(perc>45 && perc<=59){
                perc=0;
                miliben+=(1000*60*60);             
            }
                
        }else if(pBeallitasok.igazitas==30){
            if(perc<30 && perc>0)
                perc=30;
            else if(perc>30 && perc<=59){
                perc=0;
                miliben+=(1000*60*60);             
            }
            
        }else if(pBeallitasok.igazitas==60){
            if(perc>0){
                perc=0;
                miliben+=(1000*60*60);             
            }
        }
        
        teljesDatum=sdf.format(new Date(miliben));
        
        teljesDatum=teljesDatum.substring(0,teljesDatum.indexOf(":"))+":"+perc;
        if(perc==0) teljesDatum+="0";
        
        return teljesDatum;
    }
    
    // METÓDUSOK VÉGE
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(50, 32767));
        jComboBox2 = new javax.swing.JComboBox();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jButton3 = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(1, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(1, 32767));
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 32767));
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

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
        jPanel2.add(filler1);

        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox2);
        jPanel2.add(filler6);

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Frissít");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jPanel2.add(filler3);
        jPanel2.add(filler2);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jDateChooser3.setDateFormatString("yyyy.MM.dd");
        jDateChooser3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDateChooser3.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel3.add(jDateChooser3, new java.awt.GridBagConstraints());
        jPanel3.add(filler4, new java.awt.GridBagConstraints());

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("10:00");
        jTextField1.setPreferredSize(new java.awt.Dimension(60, 40));
        jPanel3.add(jTextField1, new java.awt.GridBagConstraints());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("-tól/től");
        jPanel3.add(jLabel1, new java.awt.GridBagConstraints());
        jPanel3.add(filler8, new java.awt.GridBagConstraints());

        jDateChooser2.setDateFormatString("yyyy.MM.dd");
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDateChooser2.setPreferredSize(new java.awt.Dimension(150, 40));
        jPanel3.add(jDateChooser2, new java.awt.GridBagConstraints());
        jPanel3.add(filler5, new java.awt.GridBagConstraints());

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setPreferredSize(new java.awt.Dimension(60, 40));
        jPanel3.add(jTextField2, new java.awt.GridBagConstraints());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("-ig");
        jPanel3.add(jLabel2, new java.awt.GridBagConstraints());
        jPanel3.add(filler7, new java.awt.GridBagConstraints());

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Hozzáadás");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel3);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Amennyiben nem tudod a műszak végét, hagyd üresen és jelenleg műszakban lévőnek fog megjelenni az illető");
        jPanel1.add(jLabel3);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Vissza gomb
        Foablak.retegek.show(Foablak.pHatter, "Beallitasok");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked

    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
        sql="select nev, email, oraber, keremailt from alkalmazott where nev like '"+jComboBox2.getSelectedItem()+"'";
        v=flbr.lekerdez(sql);

        

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        feltolt();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hozzaad();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
