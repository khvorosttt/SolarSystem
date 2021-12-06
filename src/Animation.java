
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class Animation extends javax.swing.JFrame {

    int N;
    double dt;
    Electron[] e;
    PanelGraphics panel;
    int center_x = 960;
    int center_y = 500;
    int x_maxSize = 1920;
    int y_maxSize = 1000;
    double step = 30.0;
    double[][] F;

    class PanelGraphics extends JPanel implements ActionListener {

        Timer timer = new Timer(1, this);

        PanelGraphics() {//в конструкторе будем стартовать
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.clearRect(0, 0, x_maxSize, y_maxSize);
            for (int i = 0; i < e.length; i++) {
                double F_xi = 0;
                double F_yi = 0;
                for (int j = 0; j < e.length; j++) {
                    if (i != j) {
                        double R_ij = Math.sqrt(Math.pow(Math.abs(e[i].X() - e[j].X()), 2) + Math.pow(Math.abs(e[i].Y() - e[j].Y()), 2));
                        F[i][j] = e[i].Q() * e[j].Q() / Math.pow(R_ij, 2);
                        double cos_alpha = (e[i].X() - e[j].X()) / R_ij;
                        double sin_alpha = (e[i].Y() - e[j].Y()) / R_ij;
                        F_xi += F[i][j] * cos_alpha;
                        F_yi += F[i][j] * sin_alpha;
                    }
                }
                e[i].newX(e[i].X() + e[i].VX() * dt);
                e[i].newY(e[i].Y() + e[i].VY() * dt);
                e[i].AX(F_xi / e[i].M());
                e[i].AY(F_yi / e[i].M());
                e[i].VX(e[i].VX() + e[i].AX() * dt);
                e[i].VY(e[i].VY() + e[i].AY() * dt);

                e[i].draw(g);
            }
            for (int i = 0; i < e.length; i++) {
                e[i].X(e[i].newX);
                e[i].Y(e[i].newY);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

    }

    /**
     * Creates new form Animation
     */
    public Animation() throws IOException {

        initComponents();
        In();
        panel = new PanelGraphics();
        add(panel);//к Frame добавляем панель
        panel.setBounds(0, 0, x_maxSize, y_maxSize);//устанавливаем начальные параметры
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Animation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Animation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Animation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Animation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Animation().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void In() throws FileNotFoundException, IOException {
        BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "\\Input.dat")));
        N = Integer.parseInt(fin.readLine().split(" ")[0]);//узнаём количество электронов
        dt = Double.parseDouble(fin.readLine().split(" ")[0]);//задаём шаг дискретизации
        e = new Electron[N];
        F = new double[N][N];
        //Fy=new double[N][N];
        for (int i = 0; i < N; i++) {
            String[] data = fin.readLine().split(" ");//строка, содержащая M, X0, Y0, V0, Alpha0, Q
            e[i] = new Electron(Integer.parseInt(data[0]), step * Double.parseDouble(data[1]), 950-step * Double.parseDouble(data[2]),
                    step*Double.parseDouble(data[3]), -Integer.parseInt(data[4]), step*step*Integer.parseInt(data[5]));
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}