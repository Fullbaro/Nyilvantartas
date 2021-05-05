package nyilvantartas;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class Foablak extends javax.swing.JFrame {

    
    // KOMPONENSEK
    
    public static CardLayout retegek = new CardLayout(); 
    
    // KOMPONENSEK VÉGE
    
    // VÁLTOZÓK 
    
    // VÁLTOZÓK VÉGE
    
    public Foablak() {
        initComponents();        
        this.setTitle("Munkaidő nyilvántartás 1.5");        
        epit();
        this.setSize(1000,650);
        this.setLocationRelativeTo(null);
    }// konstruktor vége

    // METÓDUSOK
    
    void epit(){
        pHatter.setLayout(retegek);        
        JPanel p0 = new pMuszakok();
        pHatter.add(p0, "Muszakok");
        JPanel p1 = new pMenu();
        pHatter.add(p1, "Menu");
        JPanel p2 = new pBeallitasok();
        pHatter.add(p2, "Beallitasok");
        JPanel p3 = new pKifizetes();
        pHatter.add(p3, "Kifizetes");
        JPanel p4 = new pFelvesz();
        pHatter.add(p4, "Felvesz");
        JPanel p5 = new pModosit();
        pHatter.add(p5, "Modosit");
        JPanel p6 = new pUtolagosMuszak();
        pHatter.add(p6, "UtolagosMuszak");
        
        
        retegek.show(pHatter, "Menu");
    }
    
    // METÓDUSOK VÉGE
    
    // FÜGGVÉNYEK
    
    // FÜGGVÉNYEK VÉGE
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pHatter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        pHatter.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHatter, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pHatter, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Foablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Foablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Foablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Foablak.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Foablak().setVisible(true);
            }
        });
    }// main vége.

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel pHatter;
    // End of variables declaration//GEN-END:variables
}
