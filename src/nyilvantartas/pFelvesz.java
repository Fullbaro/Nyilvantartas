package nyilvantartas;

import Fullbaro.flbr;
import java.util.Vector;

public class pFelvesz extends javax.swing.JPanel {

    // VÁLTOZÓK
    
    String sql;
    
    Vector<String[]> v = new Vector();
    
    // VÁLTOZÓK VÉGE
    
    public pFelvesz() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 0), new java.awt.Dimension(100, 32767));
        jTextField4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(127, 0), new java.awt.Dimension(134, 0), new java.awt.Dimension(120, 32767));
        jTextField2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(110, 0), new java.awt.Dimension(122, 0), new java.awt.Dimension(110, 32767));
        jTextField3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(15, 32767));
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

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

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 100, 20, 100));
        jPanel1.setLayout(new java.awt.GridLayout(5, 0, 0, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Teljes név");
        jPanel3.add(jLabel1);
        jPanel3.add(filler1);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(jTextField4);

        jPanel1.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Email");
        jPanel4.add(jLabel2);
        jPanel4.add(filler2);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel4.add(jTextField2);

        jPanel1.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Órabér");
        jPanel5.add(jLabel3);
        jPanel5.add(filler3);

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel5.add(jTextField3);

        jPanel1.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Kér értesítést emailben");
        jPanel6.add(jLabel4);
        jPanel6.add(filler4);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.add(jCheckBox2);

        jPanel1.add(jPanel6);

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Mentés");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Vissza gomb
        Foablak.retegek.show(Foablak.pHatter, "Beallitasok");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Mentés gomb
        String hiba="";
        String nev = jTextField4.getText();
        String email = jTextField2.getText();
        String oraber = jTextField3.getText();
        String kerEmailt="";
        
        if(nev.length()>1){
            sql="select nev from Alkalmazott where nev like '"+nev+"'";
            v=flbr.lekerdez(sql);
            if(v.size()>0)
                hiba="Ez a név már létezik!";
            
            if(!email.equals(""))
                if(!flbr.joEmail(email))
                    hiba+="\nHibás email cím!";
            if(!oraber.equals("")){
                if(!flbr.szamE_Fixpontos(oraber))
                    hiba+="\nHibás órabér!";
            }else
                oraber="0";
        }else
            hiba+="\nHibás név";
        
        if(jCheckBox2.isSelected())
            kerEmailt="1";
        else
            kerEmailt="0";
        
        if(hiba.length()==0){
            sql="insert into Alkalmazott(nev, email, keremailt, oraber) values('"+nev+"', '"+email+"', "+kerEmailt+", "+oraber+")";
            try{
                flbr.vegrehajt(sql);
                flbr.uzen(nev+" sikeresen felvéve!", "Hozzáadva");
                pMuszakok.feltolt();
                pKifizetes.feltolt();
                pModosit.feltolt();
                
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jCheckBox2.setSelected(false);
                
                pKifizetes.kiir();
            }catch(Exception e){
                e.printStackTrace();
                flbr.uzen("Hiba történt "+nev+" felvétele közben!", "Hiba");
            }
        }else
            flbr.uzen(hiba, "Hibás adat!");
                
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
