package nyilvantartas;

import Fullbaro.flbr;
import java.awt.BorderLayout;
import java.awt.Component;
import java.lang.reflect.Array;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class pKifizetes extends javax.swing.JPanel {

    
    // VÁLTOZÓK
    
    public static Vector<String> idLista = new Vector();
    
    public static int penzOsszeg=0;
    
    public static double oraOsszeg=0.0;
    
    public static String sql;
    
    public static Vector<String[]> v = new Vector();
    
    public static Vector<JCheckBox> sorok = new Vector();
    
    // VÁLTOZÓK VÉGE
    
    public pKifizetes() {
        initComponents();
        jPanel5.add(jPanel3, BorderLayout.NORTH);
        feltolt();
    }

    // METÓDUSOK
    
    public static void feltolt(){
        jComboBox1.removeAllItems();
        
        sql="select nev from alkalmazott order by nev";
        v=flbr.lekerdez(sql);
        
        for(String[] s:v)
            jComboBox1.addItem(s[0]);
    }
    
    public static void kiir(){ 
        penzOsszeg=0;
        oraOsszeg=0;
        idLista.clear();       
        
        jPanel3.removeAll();        
        jPanel3.repaint();
        jPanel3.revalidate();
        
        sql="select orak.id, ettol, eddig, fizetve, oraber from orak inner join alkalmazott on alkalmazott.id=orak.alkid where nev like '"+jComboBox1.getSelectedItem()+"' and eddig not like '-'";
        if(!jCheckBox2.isSelected())
            sql+="and fizetve like 0";
        v=flbr.lekerdez(sql);
        
        for(String[] s:v){
            JPanel p = new pOraSor(s);
            jPanel3.add(p);
        }
        
        osszegek();
    }
    
    public static void osszegek(){
        oraOsszeg=flbr.kerekit(oraOsszeg, 2);
        jLabel2.setText(penzOsszeg+" Ft");
        jLabel3.setText(oraOsszeg+" Óra");
    }
    
    public static void email(String neve){
        
        String jelszo="";
        sql="select jelszo from beallitasok";
        v=flbr.lekerdez(sql);        
        
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Ez a funkció jelszóvédett!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            if(password.equals(v.get(0)[0])){
                try{
                    String szoveg="";
                    double ora=0;
                    int penz=0;
                    sql="select ettol, eddig, fizetve, oraber, email from orak inner join alkalmazott on alkalmazott.id=orak.alkid where nev like '"+neve+"' and eddig not like '-' and fizetve like 0";
                    v=flbr.lekerdez(sql);

                    for(String[] s:v){
                        szoveg+="<tr><td style=\"border: 1px solid black; padding: 15px;\">"+s[0]+"-tól/től</td><td style=\"border: 1px solid black; padding: 15px;\">"+s[1]+"-ig</td><td style=\"border: 1px solid black; padding: 15px;\">"+pOraSor.oraSzamol(s[0], s[1])+" óra</td><td style=\"border: 1px solid black; padding: 15px;\">"+(int)(pOraSor.oraSzamol(s[0], s[1])*Integer.parseInt(s[3]))+" Ft</td></tr>";                        
                        ora+=pOraSor.oraSzamol(s[0], s[1]);
                        penz+=Integer.parseInt(s[3])*pOraSor.oraSzamol(s[0], s[1]);
                    }

                    szoveg="<table style=\"border-collapse: collapse;\"><tr><th style=\"border: 1px solid black; padding: 15px;\" colspan=\"2\">Összesen</th><th style=\"border: 1px solid black; padding: 15px;\">"+ora+" Óra</th><th style=\"border: 1px solid black; padding: 15px;\">"+penz+" Ft</th><th style=\"border: 1px solid black; padding: 15px;\">"+v.get(0)[3]+" Ft/óra</th></tr>"+szoveg;     

                    szoveg+="</table>";

                    if(!v.get(0)[4].equals("")){
                        boolean siker = flbr.emailKuld("Pénzügy", szoveg, v.get(0)[4], "pizzakert.gyorok@gmail.com", "Balintcs");
                        if(siker)
                            flbr.uzen("Az email sikeresen elküldve!\nA teljes lista kiküldésre került!", "Kész");
                        else
                            flbr.hiba("Hiba a küldés során!\nEllenőrizze az internet kapcsolatot.", "Hiba");
                    }

                    else{
                        try{
                            flbr.hiba("Nincs megadva az emal címed!", "Hiányos email");
                        }catch(Exception e){
                            flbr.hiba("Hiba az email küldése közben.\nKérlek vedd fel a kapcsolatot a rendszergazdával", "Hiba");
                        }            
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    flbr.hiba("A lista üres!", "Hiba");
                }
            }else
                flbr.hiba("Téves jelszó!", "Hiba");
        }
        
        
        
        
        
    }
    
    void kifizet(){
        int valasz = flbr.eldont("Biztosan?");
        if(valasz==0){        
            for(String s:idLista){
                sql="update orak set fizetve = 1 where id = "+s;
                flbr.vegrehajt(sql);
            }
            idLista.clear();
            flbr.uzen("A kifizetés megtörtént", "Kész");
            kiir();
        }
        
    }
    
    // METÓDUSOK VÉGE
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(25, 0), new java.awt.Dimension(10, 32767));
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabel3 = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 20));
        jButton3 = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jPanel3 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(jPanel5);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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
        jPanel2.add(filler1);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });
        jPanel2.add(jComboBox1);

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox2.setText("Fizetett megjelenítése");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox2);

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setText("Mind kijelöl");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jCheckBox1);

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Kifizetés");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jPanel2.add(filler2);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.X_AXIS));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Összesen: ");
        jPanel4.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("0 Ft");
        jPanel4.add(jLabel2);
        jPanel4.add(filler3);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("0 Óra");
        jPanel4.add(jLabel3);
        jPanel4.add(filler4);

        jButton3.setBackground(new java.awt.Color(255, 153, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Lista küldése emailben");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);
        jPanel4.add(filler5);

        add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));
        add(jPanel3, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Vissza gomb
        Foablak.retegek.show(Foablak.pHatter, "Menu");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // listázás
        
        kiir();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange

    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        kiir();
        jCheckBox1.setSelected(false);
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // mind kijelöl
        idLista.clear();
        oraOsszeg=0;
        penzOsszeg=0;
        
        if(jCheckBox1.isSelected()){ // pipálás
            for(JCheckBox c:sorok)
                c.setSelected(true);
            
            sql="select orak.id, ettol, eddig, oraber from orak inner join alkalmazott on alkalmazott.id=orak.alkid where nev like '"+jComboBox1.getSelectedItem()+"' and fizetve like 0 and eddig not like '-'";
            v=flbr.lekerdez(sql);            
            
            for(String[] s:v){
                idLista.add(s[0]);
                System.out.println("id "+s[0]);
                oraOsszeg+=pOraSor.oraSzamol(s[1], s[2]);
                penzOsszeg+=pOraSor.oraSzamol(s[1], s[2])*Integer.parseInt(s[3]);
            }
            System.out.println(pKifizetes.idLista);
            
        }else{// visszavonás
            for(JCheckBox c:sorok)
                c.setSelected(false);
            idLista.clear();
        }
        
        osszegek();
        
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        email(jComboBox1.getSelectedItem()+"");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Kifizetés gomb                
        sql="select jelszo from beallitasok";
        v=flbr.lekerdez(sql);
        
        
        JPasswordField pf = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, pf, "Kérlek add meg a jelszót!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (okCxl == JOptionPane.OK_OPTION) {
            String password = new String(pf.getPassword());
            if(password.equals(v.get(0)[0]))
                kifizet();
            else
                flbr.hiba("Téves jelszó!", "Hiba");
        }
        
            
        
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JCheckBox jCheckBox2;
    public static javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
