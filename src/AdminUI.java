import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {
    public JFrame view;

    public JButton btnAddUser = new JButton("Add New User");
    public JButton btnChangePassword = new JButton("Change password");
    public JButton btnChangeType = new JButton("Change the type");
    public UserModel user;
    public JTextField Password = new JTextField(20);




    public AdminUI(UserModel user) {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Cashier View");
        view.setSize(400, 300);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password"));
        line.add(Password);
        view.getContentPane().add(line);


        line = new JPanel(new FlowLayout());
        line.add(btnChangePassword);
        view.getContentPane().add(line);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddUser);
        panelButtons.add(btnChangeType);
        panelButtons.add(btnChangePassword);

        view.getContentPane().add(panelButtons);


        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUserUI ap = new AddUserUI();
                ap.run();
            }
        });

        btnChangeType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeTypeUI ap = new ChangeTypeUI();
                ap.run();
            }
        });

        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                user.mPassword = Password.getText();


                int res = StoreClient.getInstance().getDataAdapter().changePassword(user);
                if (res == SQLiteDataAdapter.USER_TYPE_CHANGE_FAILED)
                    JOptionPane.showMessageDialog(null, "type change failed");
                else
                    JOptionPane.showMessageDialog(null, "type change successfully!" + user);
            }
        });
    }
}
