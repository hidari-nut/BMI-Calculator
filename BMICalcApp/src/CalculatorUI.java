
import classes.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author mputu
 */
public class CalculatorUI extends javax.swing.JFrame {

    User userLogin;
    double currentBMI;
    String currentCategory;
    double currentHeight;
    double currentWeight;

    /**
     * Creates new form CalculatorUI
     */
    public CalculatorUI() {
        initComponents();
    }

    public CalculatorUI(User user) {
        initComponents();
        userLogin = user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldHeight = new javax.swing.JTextField();
        jTextFieldWeight = new javax.swing.JTextField();
        jButtonCalculate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaResult = new javax.swing.JTextArea();
        jButtonHome = new javax.swing.JButton();
        jButtonGetRec = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Weight (kg)");

        jLabel3.setFont(new java.awt.Font("MS UI Gothic", 1, 18)); // NOI18N
        jLabel3.setText("Height (cm)");

        jTextFieldHeight.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N

        jTextFieldWeight.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N

        jButtonCalculate.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonCalculate.setText("Calculate!");
        jButtonCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalculateActionPerformed(evt);
            }
        });

        jButtonClear.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jTextAreaResult.setColumns(20);
        jTextAreaResult.setRows(5);
        jTextAreaResult.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaResult);

        jButtonHome.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonHome.setText("Home");
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });

        jButtonGetRec.setFont(new java.awt.Font("MS UI Gothic", 0, 12)); // NOI18N
        jButtonGetRec.setText("Get Recommendation");
        jButtonGetRec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetRecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jButtonCalculate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButtonGetRec)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonHome))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCalculate)
                    .addComponent(jButtonClear))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHome)
                    .addComponent(jButtonGetRec))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
        // TODO add your handling code here:
        MainUI mainUI = new MainUI(userLogin);
        mainUI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalculateActionPerformed
        double weight = Double.parseDouble(jTextFieldWeight.getText());
        double height = Double.parseDouble(jTextFieldHeight.getText());
        height = height / 100;
        currentHeight = height;
        currentWeight = weight;

        double bmi = calculateBMI(height, weight);
        String categoryBmi = classifyBMI(bmi);
        String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        boolean result = insertBMIResult(userLogin.getEmail(), bmi, currentDate, height, weight);

        String display = "YOUR BMI RESULTS:\n"
                + "BMI: " + bmi + "\n"
                + "Your Weight Category: " + categoryBmi;
        jTextAreaResult.setText(display);
        
        currentBMI = bmi;
        currentCategory = categoryBmi;
    }//GEN-LAST:event_jButtonCalculateActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        jTextFieldWeight.setText("");
        jTextFieldHeight.setText("");
        jTextAreaResult.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonGetRecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetRecActionPerformed
        try {
            String url = "http://oobaya.online/SWMG.php?bmi=" + currentBMI + "&category=" + currentCategory.replace(" ", "+") 
                    + "&height=" + currentHeight + "&weight=" + currentWeight;
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (Exception e) {
            System.out.println("Error on jButtonGetRec: " + e);
        }
    }//GEN-LAST:event_jButtonGetRecActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CalculatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculatorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculatorUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCalculate;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonGetRec;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaResult;
    private javax.swing.JTextField jTextFieldHeight;
    private javax.swing.JTextField jTextFieldWeight;
    // End of variables declaration//GEN-END:variables

    private static Double calculateBMI(double height, double weight) {
        com.bmicalc.services.BMICalcWebService_Service service = new com.bmicalc.services.BMICalcWebService_Service();
        com.bmicalc.services.BMICalcWebService port = service.getBMICalcWebServicePort();
        return port.calculateBMI(height, weight);
    }

    private static String classifyBMI(double bmi) {
        com.bmicalc.services.BMICalcWebService_Service service = new com.bmicalc.services.BMICalcWebService_Service();
        com.bmicalc.services.BMICalcWebService port = service.getBMICalcWebServicePort();
        return port.classifyBMI(bmi);
    }

    private static boolean insertBMIResult(java.lang.String userEmail, double bmi, java.lang.String dateAdded, double height, double weight) {
        com.bmicalc.services.BMICalcWebService_Service service = new com.bmicalc.services.BMICalcWebService_Service();
        com.bmicalc.services.BMICalcWebService port = service.getBMICalcWebServicePort();
        return port.insertBMIResult(userEmail, bmi, dateAdded, height, weight);
    }

}
