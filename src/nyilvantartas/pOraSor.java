package nyilvantartas;

import Fullbaro.flbr;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class pOraSor extends javax.swing.JPanel {

    // VÁLTOZÓK
    
    String sql, ettol, eddig, id, fizetve;
    
    double ora;
    
    int oraber=0;
    
    Vector<String[]> v = new Vector();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    
    // VÁLTOZÓK VÉGE
    
    // KOMPONENSEK
    
    JCheckBox cb;
    
    // KOMPONENSEK VÉGE
    
    public pOraSor(String[] s) {
        initComponents();
        this.id=s[0];
        this.ettol=s[1];
        this.eddig=s[2];
        this.fizetve=s[3];
        this.oraber=Integer.parseInt(s[4]);
        
        ora=oraSzamol(ettol, eddig);       
        
        epit();
    }

    
    // METÓDUSOK
    
    void epit(){
        if(fizetve.equals("1")){
            JLabel l = new JLabel("Fizetve");
            flbr.betumeret(l, 18);
            l.setForeground(Color.red);
            jPanel1.add(l);
        }else{
            cb = new JCheckBox();
            cb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cb.isSelected()){
                        pKifizetes.oraOsszeg=flbr.kerekit(pKifizetes.oraOsszeg+ora,2);
                        pKifizetes.penzOsszeg+=(ora*oraber);
                        pKifizetes.idLista.add(id);
                            System.out.println(pKifizetes.idLista);
                    }else{                    
                        pKifizetes.oraOsszeg=flbr.kerekit(pKifizetes.oraOsszeg-ora,2);
                        pKifizetes.penzOsszeg-=(ora*oraber);
                        
                        for (int i = 0; i < pKifizetes.idLista.size(); i++)
                            if(pKifizetes.idLista.get(i).equals(id))                        
                                pKifizetes.idLista.remove(i);
                    }
                    
                    pKifizetes.osszegek();
                }
            });
            cb.setBackground(Color.white);
            jPanel1.add(cb);
            
            pKifizetes.sorok.add(cb);
        }
        
        jLabel1.setText(ettol+"-tól/től");
        jTextField1.setText(eddig);
        jLabel3.setText(ora+" Óra");
        
        if(ora>13)jLabel3.setForeground(Color.red);
    }
    
    // METÓDUSOK VÉGE
    
    
    // FÜGGVÉNYEK
    
    public static double oraSzamol(String ettol, String eddig){
        double kesz=0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            Date date = sdf.parse(ettol);
            long millisEttol = date.getTime();
            
            date=sdf.parse(eddig);
            long millisEddig=date.getTime();
            
            long kul=millisEddig-millisEttol;
            
            kesz=kul/1000.0/60.0/60.0;
            
        }catch(Exception e){e.printStackTrace();}
        
        return flbr.kerekit(kesz, 2);
    }
    
    // FÜGGVÉNYEK VÉGE
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        jLabel3 = new javax.swing.JLabel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1, new java.awt.GridBagConstraints());

        jPanel1.add(jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 0, 20));

        jLabel2.setText("-ig");
        jPanel2.add(jLabel2);

        jPanel1.add(jPanel2);
        jPanel1.add(filler1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("xx Óra");
        jPanel1.add(jLabel3);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String megadott=jTextField1.getText();
        try{
            Date tol = sdf.parse(jLabel1.getText().substring(0, jLabel1.getText().indexOf("-")));
            Date ig = sdf.parse(megadott);
            long lTol= tol.getTime();
            long lIg = ig.getTime();
            long lMost = new Date().getTime();
            
            System.out.println("tol "+lTol+"\nIg  "+lIg);
            if(lIg>lMost || lIg<lTol || lTol==lIg){
                flbr.hiba("A dátum előre és a kezdés indőpontjánál korábbra nem állítható!", "Hiba");
            }else{
                int valasz = flbr.eldont("Biztosan módosítod?\nA dátum előre nem lesz állítható");
                if(valasz==0){                                   
                
                    int ora = Integer.parseInt(megadott.substring(megadott.indexOf(" ")+1, megadott.indexOf(":")));
                    int perc=Integer.parseInt(megadott.substring(megadott.indexOf(":")+1));

                    // EZT MÉG MEG KELL CSINÁLNI!
                    if(pBeallitasok.igazitas==15){
                        if(perc<15)
                            perc=0;
                        else if(perc>15 && perc<30)
                            perc=15;
                        else if(perc>30 && perc<=59)
                            perc=30;                       

                    }else if(pBeallitasok.igazitas==30){
                        if(perc<30)
                            perc=0;
                        else if(perc>30 && perc<=59)
                            perc=30;

                    }else if(pBeallitasok.igazitas==60)
                        if(perc>0)
                            perc=0;


                    megadott=megadott.substring(0,megadott.indexOf(":"))+":"+perc;
                    if(perc==0) megadott+="0";
                    
                    sql="update orak set eddig='"+megadott+"' where id like "+id;
                    flbr.vegrehajt(sql);
                    flbr.uzen("Módosítás végrehajtva!", "Kész");
                    
                    pKifizetes.kiir();
                }
            }
        }catch(Exception e){
            flbr.hiba("Hibás dátum szintaxis", "hiba");
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.Box.Filler filler1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
