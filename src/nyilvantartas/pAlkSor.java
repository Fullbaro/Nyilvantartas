package nyilvantartas;

import Fullbaro.flbr;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class pAlkSor extends javax.swing.JPanel {

    // VÁLTOZÓK 
    
    String id, nev, dolgozik, sql;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    
    Vector<String[]> v = new Vector();
    
    Date date;
    
    // VÁLTOZÓK VÉGE
    
    public pAlkSor(String[] s) {
        initComponents();
        this.id=s[0];
        this.nev=s[1];
        this.dolgozik=s[3];
        epit();
    }

    // METÓDUSOK
    
    void epit(){
        try{
            v=flbr.lekerdez("select ettol from orak where alkid = "+id+" and eddig = '-' and (select dolgozik from alkalmazott where nev like '"+nev+"') = 1");
            jLabel3.setText("Kezdés: "+v.get(0)[0]);
        }catch(Exception e){}
        
        jPanel1.setPreferredSize(new Dimension(0,50));
        
        jLabel1.setText(nev);
        
        if(dolgozik.equals("1")){
            jButton1.setText("Vége");
            jPanel1.setBackground(new Color(153,255,153));
        }else{
            jButton1.setText("Kezdés");
            jPanel1.setBackground(new Color(255,204,204));
        }
    }
    
    void kezd(){
        String teljesDatum=sdf.format(new Date());
                
        int ora = Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(" ")+1, teljesDatum.indexOf(":")));
        int perc=Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(":")+1));
        
        try{
            date = sdf.parse(teljesDatum);
        }catch(Exception e){e.printStackTrace();}
        
        long miliben = date.getTime();
        
                
        if(pBeallitasok.igazitas==15){
            if(perc<15)
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
            if(perc<30)
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
        
        jLabel3.setText("Kezdés: "+teljesDatum);
        jButton1.setText("Vége");
        jPanel1.setBackground(new Color(153,255,153)); 
        
        sql="update alkalmazott set dolgozik=1 where nev like '"+nev+"'";
        flbr.vegrehajt(sql);
        
        sql="insert into orak(ettol, alkid) values ('"+teljesDatum+"', "+id+")";
        flbr.vegrehajt(sql);
    }
    
    void vegez(){
        String teljesDatum=sdf.format(new Date());
                
        int ora = Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(" ")+1, teljesDatum.indexOf(":")));
        int perc=Integer.parseInt(teljesDatum.substring(teljesDatum.indexOf(":")+1));
        
        
        if(pBeallitasok.igazitas==15){
            if(perc<15 && perc>0)
                perc=0;
            else if(perc>15 && perc<30)
                perc=15;
            else if(perc>30 && perc<45)
                perc=30;         
            else if(perc>45 && perc<=59)
                perc=45;
                
        }else if(pBeallitasok.igazitas==30){
            if(perc<30 && perc>0)
                perc=0;
            else if(perc>30 && perc<=59)
                perc=30;
            
        }else if(pBeallitasok.igazitas==60)
            if(perc>0)
                perc=0;

        
        teljesDatum=teljesDatum.substring(0,teljesDatum.indexOf(":"))+":"+perc;
        if(perc==0) teljesDatum+="0";
        
        
        jButton1.setText("Kezdés");
        jPanel1.setBackground(new Color(255,204,204));
        
        sql="update orak set eddig='"+teljesDatum+"' where alkid = "+id+" and eddig = '-'";
        flbr.vegrehajt(sql);
        
        sql="update alkalmazott set dolgozik=0 where nev like '"+nev+"'";
        flbr.vegrehajt(sql);
        
        jLabel3.setText("Nem dolgozik");
        
        tisztitas();
        
        sql="select keremailt from alkalmazott where nev like '"+nev+"'";
        v=flbr.lekerdez(sql);
        if(v.get(0)[0].equals("1"))
            pKifizetes.email(nev);
        
        pKifizetes.feltolt();
        pKifizetes.kiir();
        
        
    }
    
    void tisztitas(){ // minusz órákat tisztítja
        sql="select id, ettol, eddig from orak where eddig not like '-'";
        v=flbr.lekerdez(sql);
        for(String[]s:v){
            if(pOraSor.oraSzamol(s[1], s[2])<=0){
                sql="delete from orak where id like "+s[0];
                flbr.vegrehajt(sql);
            }
        }
            
    }
    
    // METÓDUSOK VÉGE
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Bálint dániel Lajos Ferenc");
        jPanel1.add(jLabel1);

        jLabel3.setText("Nem dolgozik");
        jPanel1.add(jLabel3);

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // kezdés gomb
        if(jButton1.getText().equals("Vége")){// befejez
            vegez();
        }else{ // kezd
            kezd();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
