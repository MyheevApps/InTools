/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package intools;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Dividend;
import models.Plan;
import utils.Serialization;

/**
 *
 * @author Mikheev Maxim
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        fillProfit();
        fillDividends();
        fillPlan();
    }
    
    private void fillProfit() {
        HashMap<String, String> result = Serialization.getProfitFromFile();
        
        if (result != null) {
            if (result.containsKey("invested")) {
                invested.setText(result.get("invested"));
            }
            
            if (result.containsKey("dividend")) {
                dividend.setText(result.get("dividend"));
            }
            
            if (result.containsKey("cost")) {
                cost.setText(result.get("cost"));
            }
            
            if (result.containsKey("sum")) {
                profit.setText(result.get("sum"));
            }
        }
    }
    
    private void fillDividends() {
        List<Dividend> dataList = Serialization.getDataTable("dvdnd.txt");     
        DefaultTableModel model = (DefaultTableModel) tableDividends.getModel(); 
        if (dataList != null) {
            for (Dividend dividends : dataList) { 
                try {
                    model.addRow(new Object[]{dividends.getCompany(),
                        dividends.getSum(),
                        dividends.getClosePeriod(),
                        dividends.getPaymentDate()});
                } catch (Exception e) {

                }
            }
        }
    }
    
    private void fillPlan() {
        List<Plan> dataList = Serialization.getDataTable("plan.txt");     
        DefaultTableModel model = (DefaultTableModel) tablePlan.getModel(); 
        if (dataList != null) {
            for (Plan plan : dataList) { 
                try {
                    model.addRow(new Object[]{plan.getDate(),
                        plan.getSector(),
                        plan.getCompany(),
                        plan.getSum()});
                } catch (Exception e) {

                }
            }
        }
    }
    
    private void calculateProfit() {
        String strInvested = invested.getText().trim();
        String strDividend = dividend.getText().trim();
        String strCost = cost.getText().trim();
        
        if (!strInvested.isEmpty() & !strCost.isEmpty()) {
            try {
                double numCost = Double.parseDouble(strCost);
                double numInvested = Double.parseDouble(strInvested);
                double sum = numCost - numInvested;
                
                double percentage = 0.0;
                if (numCost > numInvested) {
                    percentage = (sum / numCost) * 100;
                }
                
                String textSum = String.format("%s (%s)", 
                        new DecimalFormat("0.00").format(sum),
                        new DecimalFormat("0.00").format(percentage) + "%");
                
                profit.setText(textSum);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            
            HashMap mProfit = new HashMap();
            
            mProfit.put("invested", strInvested);
            mProfit.put("dividend", strDividend);
            mProfit.put("cost", strCost);
            mProfit.put("sum", profit.getText());
            
            Serialization.saveToFile(mProfit);
        } else {
            profit.setText("0.0");
        }
    }
    
    private void calculateDifficultPercentage() {
        int startPeriod = 1;
        int endPeriod = (int) termSpinner.getValue();
        int sum = 0;
        int mySum = 0;
        int sumInYear = Integer.parseInt(textFieldSum.getText());
        double percent = (double) (percentSpinner.getValue()) / 100;
        
        DefaultTableModel model = (DefaultTableModel) tableDifficultPercent.getModel();
        model.setRowCount(0);
        
        while (startPeriod <= endPeriod) {
            sum += sumInYear;
            mySum += sumInYear;
            sum += (int) (sum * percent);
            Object[] row = {startPeriod, mySum, sum - mySum, sum};
            model.addRow(row);
            startPeriod++;
        }
    }
    
    private void setColor(JLabel label) {
        label.setBackground(new Color(43,51,90));
    }
    
    private void resetColor(JLabel label) {
        label.setBackground(new Color(31,36,65));
    }
    
    private void saveStatePlanTable() {
        List<Plan> data = new ArrayList<>();
        
        for (int i = 0; i < tablePlan.getRowCount(); i++) {
            data.add(new Plan(tablePlan.getValueAt(i, 0).toString(),
                    tablePlan.getValueAt(i, 1).toString(), 
                    tablePlan.getValueAt(i, 2).toString(), 
                    tablePlan.getValueAt(i, 3).toString()));
        }
        
        Serialization.saveToFile(data, "plan.txt");
    }
    
    private void saveStateDividendTable() {
        List<Dividend> data = new ArrayList<>();
        
        for (int i = 0; i < tableDividends.getRowCount(); i++) {
            data.add(new Dividend(tableDividends.getValueAt(i, 0).toString(),
                    tableDividends.getValueAt(i, 1).toString(), 
                    tableDividends.getValueAt(i, 2).toString(), 
                    tableDividends.getValueAt(i, 3).toString()));
        }
        
        Serialization.saveToFile(data, "dvdnd.txt");
    }
    
    private boolean deleteRowTable(JTable table) {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (selectedRow >= 0) {
            model.removeRow(selectedRow);
            return true;
        } else {
            JOptionPane.showMessageDialog(this,"Выберите элемент таблицы");
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        appName = new javax.swing.JLabel();
        btnPortfolio = new javax.swing.JLabel();
        btnCalculate = new javax.swing.JLabel();
        btnPlan = new javax.swing.JLabel();
        dynamicPanel = new javax.swing.JPanel();
        portfolioPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        profit = new javax.swing.JLabel();
        invested = new javax.swing.JTextField();
        dividend = new javax.swing.JTextField();
        cost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDividends = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        textFieldCompany = new javax.swing.JTextField();
        textFieldDividend = new javax.swing.JTextField();
        textFieldEndDate = new javax.swing.JTextField();
        textFieldDate = new javax.swing.JTextField();
        calculatePanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnCalculateProfit = new javax.swing.JButton();
        textFieldSum = new javax.swing.JTextField();
        termSpinner = new javax.swing.JSpinner();
        percentSpinner = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDifficultPercent = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        planPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePlan = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textFieldDateBuy = new javax.swing.JTextField();
        textFieldSector = new javax.swing.JTextField();
        textFieldCompanyPlan = new javax.swing.JTextField();
        textFieldSumPlan = new javax.swing.JTextField();
        btnAddPlan = new javax.swing.JButton();
        btnEditPlan = new javax.swing.JButton();
        btnDeletePlan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(798, 460));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        mainPanel.setBackground(new java.awt.Color(31, 36, 65));
        mainPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainPanel.setMaximumSize(new java.awt.Dimension(210, 32767));
        mainPanel.setPreferredSize(new java.awt.Dimension(180, 237));

        appName.setBackground(new java.awt.Color(255, 255, 255));
        appName.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
        appName.setForeground(new java.awt.Color(255, 255, 255));
        appName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appName.setText("InTools");
        appName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnPortfolio.setBackground(new java.awt.Color(43, 51, 90));
        btnPortfolio.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnPortfolio.setForeground(new java.awt.Color(255, 255, 255));
        btnPortfolio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPortfolio.setText("Портфель");
        btnPortfolio.setOpaque(true);
        btnPortfolio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPortfolioMousePressed(evt);
            }
        });

        btnCalculate.setBackground(new java.awt.Color(31, 36, 65));
        btnCalculate.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnCalculate.setForeground(new java.awt.Color(255, 255, 255));
        btnCalculate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCalculate.setText("Рассчеты");
        btnCalculate.setOpaque(true);
        btnCalculate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCalculateMousePressed(evt);
            }
        });

        btnPlan.setBackground(new java.awt.Color(31, 36, 65));
        btnPlan.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        btnPlan.setForeground(new java.awt.Color(255, 255, 255));
        btnPlan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnPlan.setText("План");
        btnPlan.setOpaque(true);
        btnPlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPlanMousePressed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appName, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(btnCalculate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPortfolio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(appName)
                .addGap(18, 18, 18)
                .addComponent(btnPortfolio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCalculate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnCalculate.getAccessibleContext().setAccessibleName("");
        btnCalculate.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(mainPanel);

        dynamicPanel.setBackground(new java.awt.Color(36, 43, 72));
        dynamicPanel.setLayout(new java.awt.CardLayout());

        portfolioPanel.setBackground(new java.awt.Color(43, 51, 90));

        jPanel1.setBackground(new java.awt.Color(75, 124, 253));

        jLabel1.setBackground(new java.awt.Color(75, 124, 253));
        jLabel1.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Вложил:");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(75, 124, 253));
        jLabel2.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Дивиденды:");

        jLabel3.setBackground(new java.awt.Color(75, 124, 253));
        jLabel3.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Стоимость портфеля:");

        jLabel5.setBackground(new java.awt.Color(75, 124, 253));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("ПРИБЫЛЬ:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(100, 1));
        jPanel2.setMinimumSize(new java.awt.Dimension(100, 1));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        profit.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        profit.setForeground(new java.awt.Color(255, 255, 255));
        profit.setText("0.0");

        invested.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        invested.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        invested.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                investedKeyReleased(evt);
            }
        });

        dividend.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        dividend.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        cost.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        cost.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        cost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(profit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cost, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(dividend, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(invested, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(invested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dividend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(profit))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ПОРТФЕЛЬ");

        jLabel11.setBackground(new java.awt.Color(143, 148, 179));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(143, 148, 179));
        jLabel11.setText("В этом разделе находится общая информиция о вашем портфеле");

        tableDividends.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableDividends.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Компания", "Размер выплаты", "Дата отсечки", "Дата выплаты"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableDividends);

        jPanel11.setBackground(new java.awt.Color(247, 82, 99));

        btnAdd.setText("Добавить");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Удалить");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton3.setText("Изменить");

        jPanel4.setBackground(new java.awt.Color(247, 82, 99));
        jPanel4.setLayout(new java.awt.GridLayout(2, 3, 4, 0));

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Компания");
        jPanel4.add(jLabel29);

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Размер");
        jPanel4.add(jLabel30);

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Отсечка");
        jPanel4.add(jLabel32);

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Дата выплаты");
        jLabel33.setToolTipText("");
        jPanel4.add(jLabel33);

        textFieldCompany.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        textFieldCompany.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        textFieldCompany.setMaximumSize(new java.awt.Dimension(64, 16));
        textFieldCompany.setPreferredSize(new java.awt.Dimension(66, 16));
        jPanel4.add(textFieldCompany);

        textFieldDividend.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        textFieldDividend.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        textFieldDividend.setMaximumSize(new java.awt.Dimension(64, 16));
        textFieldDividend.setPreferredSize(new java.awt.Dimension(66, 16));
        jPanel4.add(textFieldDividend);

        textFieldEndDate.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        textFieldEndDate.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        textFieldEndDate.setMaximumSize(new java.awt.Dimension(64, 16));
        textFieldEndDate.setPreferredSize(new java.awt.Dimension(66, 16));
        jPanel4.add(textFieldEndDate);

        textFieldDate.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        textFieldDate.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        textFieldDate.setMaximumSize(new java.awt.Dimension(64, 16));
        textFieldDate.setPreferredSize(new java.awt.Dimension(66, 16));
        jPanel4.add(textFieldDate);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(jButton3)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        javax.swing.GroupLayout portfolioPanelLayout = new javax.swing.GroupLayout(portfolioPanel);
        portfolioPanel.setLayout(portfolioPanelLayout);
        portfolioPanelLayout.setHorizontalGroup(
            portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfolioPanelLayout.createSequentialGroup()
                .addGroup(portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(portfolioPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(portfolioPanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(portfolioPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        portfolioPanelLayout.setVerticalGroup(
            portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portfolioPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(57, 57, 57)
                .addGroup(portfolioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(portfolioPanelLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        dynamicPanel.add(portfolioPanel, "card4");

        calculatePanel.setBackground(new java.awt.Color(43, 51, 90));
        calculatePanel.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(247, 82, 99));
        jPanel3.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Срок (год):");

        jLabel6.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Сумма (год):");

        jLabel7.setFont(new java.awt.Font("Arial", 2, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Доходность (%):");

        btnCalculateProfit.setText("Рассчитать");
        btnCalculateProfit.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCalculateProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateProfitActionPerformed(evt);
            }
        });

        textFieldSum.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        textFieldSum.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        textFieldSum.setMinimumSize(new java.awt.Dimension(64, 22));
        textFieldSum.setPreferredSize(new java.awt.Dimension(64, 22));

        termSpinner.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        termSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));

        percentSpinner.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        percentSpinner.setModel(new javax.swing.SpinnerNumberModel(1.0d, 1.0d, 100.0d, 1.0d));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(btnCalculateProfit)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGap(136, 136, 136)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(termSpinner)
                    .addComponent(textFieldSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(percentSpinner))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(termSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textFieldSum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(percentSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCalculateProfit)
                .addContainerGap())
        );

        tableDifficultPercent.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        tableDifficultPercent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Год", "Вложил", "Проценты", "Всего"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDifficultPercent);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("РАССЧЕТЫ");

        jLabel12.setBackground(new java.awt.Color(143, 148, 179));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(143, 148, 179));
        jLabel12.setText("В разделе \"Рассчеты\" Вы можете рассчитать свою доходность за указанный Вами период");

        jPanel5.setBackground(new java.awt.Color(75, 124, 253));
        jPanel5.setPreferredSize(new java.awt.Dimension(315, 129));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("КАЛЬКУЛЯТОР УСРЕДНЕНИЯ");

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("КОЛИЧЕСТВО");
        jLabel21.setPreferredSize(new java.awt.Dimension(157, 17));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("ОБЩАЯ СУММА");
        jLabel22.setPreferredSize(new java.awt.Dimension(157, 17));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("0.0");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("0.0");
        jLabel24.setMaximumSize(new java.awt.Dimension(157, 17));
        jLabel24.setPreferredSize(new java.awt.Dimension(157, 17));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("СРЕДНЯЯ ЦЕНА");
        jLabel25.setPreferredSize(new java.awt.Dimension(157, 17));

        jLabel26.setBackground(new java.awt.Color(102, 255, 0));
        jLabel26.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 255, 51));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("0.0");

        jPanel12.setBackground(new java.awt.Color(75, 124, 253));
        jPanel12.setLayout(new java.awt.GridLayout(1, 2, 2, 0));

        jPanel6.setBackground(new java.awt.Color(75, 124, 253));
        jPanel6.setForeground(new java.awt.Color(75, 124, 253));
        jPanel6.setPreferredSize(new java.awt.Dimension(105, 0));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("ЦЕНА");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jTextField10))
                .addContainerGap())
            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(75, 124, 253));
        jPanel7.setPreferredSize(new java.awt.Dimension(105, 100));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("КОЛИЧЕСТВО");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(jTextField5)
                    .addComponent(jTextField8)
                    .addComponent(jTextField11))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(75, 124, 253));
        jPanel8.setPreferredSize(new java.awt.Dimension(105, 100));
        jPanel8.setRequestFocusEnabled(false);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("СУММА");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jTextField6)
                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField12))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel8);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout calculatePanelLayout = new javax.swing.GroupLayout(calculatePanel);
        calculatePanel.setLayout(calculatePanelLayout);
        calculatePanelLayout.setHorizontalGroup(
            calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(calculatePanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calculatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculatePanelLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(calculatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap())
        );
        calculatePanelLayout.setVerticalGroup(
            calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calculatePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(54, 54, 54)
                .addGroup(calculatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calculatePanelLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
                .addContainerGap())
        );

        dynamicPanel.add(calculatePanel, "card3");

        planPanel.setBackground(new java.awt.Color(43, 51, 90));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ПЛАНИРОВАНИЕ");

        jLabel13.setBackground(new java.awt.Color(143, 148, 179));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(143, 148, 179));
        jLabel13.setText("В этом разделе Вы можете запланировать покупку акций");

        tablePlan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Дата покупки", "Сектор", "Компания", "Сумма"
            }
        ));
        tablePlan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePlanMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablePlan);

        jPanel9.setBackground(new java.awt.Color(75, 124, 253));
        jPanel9.setForeground(new java.awt.Color(247, 82, 99));

        jPanel10.setBackground(new java.awt.Color(75, 124, 253));
        jPanel10.setForeground(new java.awt.Color(75, 124, 253));
        jPanel10.setLayout(new java.awt.GridLayout(2, 3, 4, 0));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Дата покупки");
        jPanel10.add(jLabel27);

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Сектор");
        jPanel10.add(jLabel28);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Компания");
        jPanel10.add(jLabel16);

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Сумма");
        jPanel10.add(jLabel15);
        jPanel10.add(textFieldDateBuy);
        jPanel10.add(textFieldSector);
        jPanel10.add(textFieldCompanyPlan);
        jPanel10.add(textFieldSumPlan);

        btnAddPlan.setText("Добавить");
        btnAddPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPlanActionPerformed(evt);
            }
        });

        btnEditPlan.setText("Изменить");

        btnDeletePlan.setText("Удалить");
        btnDeletePlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletePlanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddPlan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditPlan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeletePlan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPlan)
                    .addComponent(btnEditPlan)
                    .addComponent(btnDeletePlan))
                .addContainerGap())
        );

        javax.swing.GroupLayout planPanelLayout = new javax.swing.GroupLayout(planPanel);
        planPanel.setLayout(planPanelLayout);
        planPanelLayout.setHorizontalGroup(
            planPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(planPanelLayout.createSequentialGroup()
                .addGroup(planPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(planPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(planPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(planPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addContainerGap())
        );
        planPanelLayout.setVerticalGroup(
            planPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, planPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        dynamicPanel.add(planPanel, "card2");

        getContentPane().add(dynamicPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPortfolioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPortfolioMousePressed
        setColor(btnPortfolio);
        resetColor(btnCalculate);
        resetColor(btnPlan);
        portfolioPanel.setVisible(true);
        calculatePanel.setVisible(false);
        planPanel.setVisible(false);
    }//GEN-LAST:event_btnPortfolioMousePressed

    private void btnCalculateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalculateMousePressed
        resetColor(btnPortfolio);
        setColor(btnCalculate);
        resetColor(btnPlan);
        portfolioPanel.setVisible(false);
        calculatePanel.setVisible(true);
        planPanel.setVisible(false);
    }//GEN-LAST:event_btnCalculateMousePressed

    private void btnPlanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPlanMousePressed
        resetColor(btnPortfolio);
        resetColor(btnCalculate);
        setColor(btnPlan);
        portfolioPanel.setVisible(false);
        calculatePanel.setVisible(false);
        planPanel.setVisible(true);
    }//GEN-LAST:event_btnPlanMousePressed

    private void btnCalculateProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateProfitActionPerformed
        if (textFieldSum.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле \"Сумма\" не заполнено", "Внимание", JOptionPane.INFORMATION_MESSAGE);
        } else {
            calculateDifficultPercentage();
        }
    }//GEN-LAST:event_btnCalculateProfitActionPerformed

    private void costKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costKeyReleased
        calculateProfit();
    }//GEN-LAST:event_costKeyReleased

    private void investedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_investedKeyReleased
        calculateProfit();
    }//GEN-LAST:event_investedKeyReleased

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableDividends.getModel();
        model.addRow(new Object[]{textFieldCompany.getText(), textFieldDividend.getText(),
            textFieldEndDate.getText(), textFieldDate.getText()});
        
        saveStateDividendTable();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPlanActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablePlan.getModel();
        model.addRow(new Object[]{textFieldDateBuy.getText(), textFieldSector.getText(),
            textFieldCompanyPlan.getText(), textFieldSumPlan.getText()});
        
        saveStatePlanTable();
    }//GEN-LAST:event_btnAddPlanActionPerformed

    private void tablePlanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePlanMouseClicked
        DefaultTableModel model = (DefaultTableModel) tablePlan.getModel();
        int selectedRow = tablePlan.getSelectedRow();
        
        textFieldDateBuy.setText(model.getValueAt(selectedRow, 0).toString());
        textFieldSector.setText(model.getValueAt(selectedRow, 1).toString());
        textFieldCompanyPlan.setText(model.getValueAt(selectedRow, 2).toString());
        textFieldSumPlan.setText(model.getValueAt(selectedRow, 3).toString());
    }//GEN-LAST:event_tablePlanMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (deleteRowTable(tableDividends)) {
            saveStateDividendTable();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDeletePlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletePlanActionPerformed
        if (deleteRowTable(tablePlan)) {
            saveStatePlanTable();
        }
    }//GEN-LAST:event_btnDeletePlanActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appName;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddPlan;
    private javax.swing.JLabel btnCalculate;
    private javax.swing.JButton btnCalculateProfit;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeletePlan;
    private javax.swing.JButton btnEditPlan;
    private javax.swing.JLabel btnPlan;
    private javax.swing.JLabel btnPortfolio;
    private javax.swing.JPanel calculatePanel;
    private javax.swing.JTextField cost;
    private javax.swing.JTextField dividend;
    private javax.swing.JPanel dynamicPanel;
    private javax.swing.JTextField invested;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner percentSpinner;
    private javax.swing.JPanel planPanel;
    private javax.swing.JPanel portfolioPanel;
    private javax.swing.JLabel profit;
    private javax.swing.JTable tableDifficultPercent;
    private javax.swing.JTable tableDividends;
    private javax.swing.JTable tablePlan;
    private javax.swing.JSpinner termSpinner;
    private javax.swing.JTextField textFieldCompany;
    private javax.swing.JTextField textFieldCompanyPlan;
    private javax.swing.JTextField textFieldDate;
    private javax.swing.JTextField textFieldDateBuy;
    private javax.swing.JTextField textFieldDividend;
    private javax.swing.JTextField textFieldEndDate;
    private javax.swing.JTextField textFieldSector;
    private javax.swing.JTextField textFieldSum;
    private javax.swing.JTextField textFieldSumPlan;
    // End of variables declaration//GEN-END:variables
}
