package cst4;

import java.util.Stack;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Administrator
 */


public class PDA extends javax.swing.JFrame {
    
    private Stack<Character> stack;
    private javax.swing.JPanel stackPanel;
    private SwingWorker<Void, Void> worker;

    /**
     * Creates new form PDA
     */
    public PDA() {
        stack = new Stack<>();
        initComponents();
        setSize(1200, 700);
        txtField2.setEditable(false);
        getContentPane().setBackground(Color.WHITE);
        
            // Add stackPanel to the GUI
        stackPanel = new JPanel();
        stackPanel.setLayout(new BoxLayout(stackPanel, BoxLayout.Y_AXIS));  // Vertical layout for stack
        stackPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        stackPanel.setPreferredSize(new Dimension(150, 560));  // Adjust size as needed
        getContentPane().add(stackPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, 150, 560));  // Place next to the text area
    }
    
    private void updateStackPanel() {
    stackPanel.removeAll();  // Clear the panel first
    stackPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for more control
    GridBagConstraints gbc = new GridBagConstraints();
    
    gbc.gridx = 0;  // Set column index
    gbc.weightx = 1.0;  // Allow components to stretch horizontally
    gbc.anchor = GridBagConstraints.SOUTH;  // Align items to the bottom
    gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
    gbc.weighty = 0.0;  // Reset weighty to ensure correct stacking

    // Loop through the stack from the bottom to the top
    for (int i = 0; i < stack.size(); i++) {
        JLabel label = new JLabel(String.valueOf(stack.get(i)));
        label.setPreferredSize(new Dimension(100, 50));  // Adjust label size
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Set font and size here
        label.setFont(new Font("Serif", Font.BOLD, 36));  // Enlarge font size to 36

        // Set specific row index for each label
        gbc.gridy = stack.size() - 1 - i;  // Place the first element at the bottom

        stackPanel.add(label, gbc);  // Add label to panel with constraints
    }

    stackPanel.revalidate();
    stackPanel.repaint();
}





    public String transition(String state, char symbol) throws InterruptedException {
    switch (state) {
        case "q0":
            // In state q0, we expect 'a' and push 'Z' to the stack for each 'a'
            if (symbol == 'a') {
                stack.push('Z'); // Push 'Z' to the stack for each 'a'
                txtArea1.append("--Transition: " + state + " -- 'a' --> " + state + " (push 'Z' to stack)\n");
                updateStackPanel();  // Update stack visualization
                return "q0"; // Stay in q0 for 'a'
            } 
            // If we encounter 'b', we transition to state q1 and start popping the stack
            else if (symbol == 'b' && !stack.isEmpty()) {
                stack.pop(); // Pop 'Z' from the stack for each 'b'
                txtArea1.append("--Transition: " + state + " -- 'b' --> q1 (pop 'Z' from stack)\n");
                updateStackPanel();  // Update stack visualization
                return "q1"; // Move to q1 for 'b'
            } 
            // If 'b' comes before 'a', or an invalid symbol is encountered, reject
            else {
                txtArea1.append("--Transition: " + state + " -- " + symbol + " --> reject (invalid symbol or empty stack)\n");
                return "reject"; // Invalid symbol or stack is empty when 'b' is encountered
            }

        case "q1":
            // In state q1, we expect more 'b's and pop 'Z' for each one
            if (symbol == 'b' && !stack.isEmpty()) {
                stack.pop(); // Pop 'Z' for each 'b'
                txtArea1.append("--Transition: " + state + " -- 'b' --> " + state + " (pop 'Z' from stack)\n");
                updateStackPanel();  // Update stack visualization
                return "q1"; // Stay in q1 for more 'b's
            } 
            // If the stack is empty and we've processed all input, accept the string
            else if (stack.isEmpty() && symbol == '\0') { // Epsilon transition
                txtArea1.append("--Transition: " + state + " -- epsilon --> accept (stack is empty)\n");
                updateStackPanel();  // Update stack visualization
                return "accept"; // Accept if stack is empty
            } 
            // If there are more symbols and the stack isn't empty, reject
            else {
                txtArea1.append("--Transition: " + state + " -- " + symbol + " --> reject (invalid symbol or stack not empty)\n");
                return "reject"; // Invalid transition
            }

        default:
            return "reject"; // Default reject case
    }
}

    
    public void processInput(String inputString) { // Change return type to void
    worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            String state = "q0";

            for (char symbol : inputString.toCharArray()) {
                
                if (isCancelled()) {
                    return null; // Exit if cancelled
                }
                
                state = transition(state, symbol);
                if (state.equals("reject")) {
                    break; // Early exit if rejected
                }
                Thread.sleep(1000); // Simulate delay between transitions
            }

            // After consuming all input symbols, check for empty stack with epsilon transition
            String finalState = transition(state, '\0'); // epsilon transition
            Thread.sleep(2000);
            txtArea1.append("\nFinal Result: " + (finalState.equals("accept") ? "Accepted" : "Rejected"));
            //JOptionPane.showMessageDialog(, "Execution Complete");
            return null; // Returning null as there is no meaningful return value
        }

        @Override
        protected void done() {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(
                    SwingUtilities.getWindowAncestor(txtArea1), 
                    "Execution Complete"
                );
            });
        }
    };

    worker.execute(); // Start the worker asynchronously
}


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea1 = new javax.swing.JTextArea();
        btnClear = new javax.swing.JButton();
        btnInput = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txtField2 = new javax.swing.JTextField();
        txtField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Push Down Automata");
        setPreferredSize(new java.awt.Dimension(1120, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtArea1.setEditable(false);
        txtArea1.setBackground(new java.awt.Color(0, 0, 0));
        txtArea1.setColumns(20);
        txtArea1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtArea1.setForeground(new java.awt.Color(0, 255, 51));
        txtArea1.setRows(5);
        jScrollPane1.setViewportView(txtArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 520, 560));

        btnClear.setBackground(new java.awt.Color(153, 153, 153));
        btnClear.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 220, 80));

        btnInput.setBackground(new java.awt.Color(153, 153, 153));
        btnInput.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnInput.setForeground(new java.awt.Color(255, 255, 255));
        btnInput.setText("Input");
        btnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputActionPerformed(evt);
            }
        });
        getContentPane().add(btnInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 220, 80));

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back to Main Menu");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 530, 220, 80));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 320, 20));

        txtField2.setBackground(new java.awt.Color(0, 0, 0));
        txtField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtField2.setForeground(new java.awt.Color(0, 255, 0));
        txtField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtField2ActionPerformed(evt);
            }
        });
        getContentPane().add(txtField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 320, 60));

        txtField1.setBackground(new java.awt.Color(0, 0, 0));
        txtField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtField1.setForeground(new java.awt.Color(0, 255, 51));
        getContentPane().add(txtField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 320, 60));

        jLabel1.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        jLabel1.setText("Enter the value of N (max number of 'a's and 'b's)");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        jLabel2.setText("String of 'a's and 'b's");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        MACHINE machineFrame = new MACHINE();
        machineFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtField1.setText("");
        txtField2.setText("");
        txtArea1.setText("");
        stack.clear();
        
        // Cancel the worker if it is running
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);     // Cancel the worker
        }
    
        updateStackPanel();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputActionPerformed
       
    try {
        String N = txtField1.getText();
        
        // Validate input for N
        if (N.isEmpty() || !N.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Invalid input for N or the Field is Empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert N to an integer
        int n = Integer.parseInt(N);

        // Generate the string of a's and b's
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < n; i++) {
            input.append("a");
        }
        for (int i = 0; i < n; i++) {
            input.append("b");
        }

        // Set the generated input in txtField2 and make it uneditable
        txtField2.setText(input.toString());
        txtField2.setEditable(false); // Disable editing on txtField2

        // Displaying the Push Down Automata information in txtArea1
        String label = "Push Down Automata";
        txtArea1.append("\nL = { a^n b^n } where n is >= 1");
        txtArea1.append("\n==============================\n");
        txtArea1.append("                  " + label + "\n");
        txtArea1.append("==============================\n");
        txtArea1.append("Generated input for n = " + n + ": " + input.toString() + "\n");

        // Call the processInput method to handle transitions
        processInput(input.toString());  // Add this line to process the input
        
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Invalid input for N. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
    }
       
    }//GEN-LAST:event_btnInputActionPerformed

    private void txtField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtField2ActionPerformed

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
            java.util.logging.Logger.getLogger(PDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PDA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PDA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea txtArea1;
    private javax.swing.JTextField txtField1;
    private javax.swing.JTextField txtField2;
    // End of variables declaration//GEN-END:variables
}
