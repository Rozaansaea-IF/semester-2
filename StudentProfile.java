import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class StudentProfile extends JFrame {

    // ─── Color Palette ───────────────────────────────────────────────────────
    private static final Color BG_DARK      = new Color(10, 12, 20);
    private static final Color BG_CARD      = new Color(18, 22, 38);
    private static final Color ACCENT_CYAN  = new Color(0, 210, 220);
    private static final Color ACCENT_BLUE  = new Color(60, 120, 255);
    private static final Color ACCENT_GOLD  = new Color(255, 195, 0);
    private static final Color TEXT_WHITE   = new Color(235, 240, 255);
    private static final Color TEXT_MUTED   = new Color(130, 145, 180);
    private static final Color BORDER_GLOW  = new Color(0, 210, 220, 80);

    public StudentProfile() {
        setTitle("Student Profile — Rozaan Asyraf");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 780);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(new BackgroundPanel());
        setLayout(new BorderLayout());

        add(buildHeader(),  BorderLayout.NORTH);
        add(buildContent(), BorderLayout.CENTER);
        add(buildFooter(),  BorderLayout.SOUTH);
    }

    // ─── Header ──────────────────────────────────────────────────────────────
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // gradient bar
                GradientPaint gp = new GradientPaint(0, 0, ACCENT_BLUE, getWidth(), 0, ACCENT_CYAN);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                // bottom glow line
                g2.setColor(ACCENT_GOLD);
                g2.setStroke(new BasicStroke(2f));
                g2.drawLine(0, getHeight()-1, getWidth(), getHeight()-1);
                g2.dispose();
            }
        };
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(700, 120));
        header.setBorder(BorderFactory.createEmptyBorder(22, 32, 18, 32));

        // Avatar circle
        JPanel avatarWrap = new JPanel(null) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // outer glow
                for (int i = 8; i > 0; i--) {
                    g2.setColor(new Color(0, 210, 220, i * 8));
                    g2.fillOval(i, i, 80-i*2, 80-i*2);
                }
                // fill
                g2.setColor(BG_DARK);
                g2.fillOval(8, 8, 64, 64);
                // initials
                g2.setColor(ACCENT_CYAN);
                g2.setFont(new Font("Monospaced", Font.BOLD, 24));
                FontMetrics fm = g2.getFontMetrics();
                String init = "RA";
                int tx = (80 - fm.stringWidth(init)) / 2;
                int ty = (80 + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(init, tx, ty);
                g2.dispose();
            }
        };
        avatarWrap.setOpaque(false);
        avatarWrap.setPreferredSize(new Dimension(80, 80));

        // Name + subtitle
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        JLabel nameLabel = new JLabel("ROZAAN ASYRAF");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        nameLabel.setForeground(TEXT_WHITE);

        JLabel nimLabel = new JLabel("NIM  :  202510370110233");
        nimLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
        nimLabel.setForeground(ACCENT_GOLD);

        JLabel statusLabel = new JLabel("▶  Mahasiswa Aktif  |  Teknik Informatika");
        statusLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        statusLabel.setForeground(new Color(180, 220, 255));

        namePanel.add(nameLabel);
        namePanel.add(Box.createVerticalStrut(4));
        namePanel.add(nimLabel);
        namePanel.add(Box.createVerticalStrut(4));
        namePanel.add(statusLabel);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        left.add(avatarWrap);
        left.add(namePanel);

        header.add(left, BorderLayout.WEST);
        return header;
    }

    // ─── Content ─────────────────────────────────────────────────────────────
    private JPanel buildContent() {
        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(24, 32, 12, 32));

        content.add(sectionTitle("◈  INFORMASI MAHASISWA"));
        content.add(Box.createVerticalStrut(12));
        content.add(buildInfoGrid());
        content.add(Box.createVerticalStrut(24));
        content.add(sectionTitle("◈  REKAP AKADEMIK"));
        content.add(Box.createVerticalStrut(12));
        content.add(buildStatCards());
        content.add(Box.createVerticalStrut(20));
        content.add(buildActionButtons());

        return content;
    }

    private JLabel sectionTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 13));
        lbl.setForeground(ACCENT_CYAN);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }

    // Info grid ───────────────────────────────────────────────────────────────
    private JPanel buildInfoGrid() {
        JPanel grid = new JPanel(new GridLayout(3, 2, 14, 10));
        grid.setOpaque(false);
        grid.setMaximumSize(new Dimension(640, 160));
        grid.setAlignmentX(LEFT_ALIGNMENT);

        String[][] fields = {
                {"Nama Lengkap", "Rozaan Asyraf"},
                {"NIM", "202510370110233"},
                {"Program Studi", "Teknik Informatika"},
                {"Angkatan", "2025"},
                {"Email", "rozaan.asyraf@student.ac.id"},
                {"Status", "Aktif"}
        };

        for (String[] f : fields) grid.add(infoField(f[0], f[1]));
        return grid;
    }

    private JPanel infoField(String label, String value) {
        JPanel p = new GlowCard(BORDER_GLOW);
        p.setLayout(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 9));
        lbl.setForeground(TEXT_MUTED);

        JLabel val = new JLabel(value);
        val.setFont(new Font("Monospaced", Font.BOLD, 13));
        val.setForeground(TEXT_WHITE);

        p.add(lbl, BorderLayout.NORTH);
        p.add(val, BorderLayout.CENTER);
        return p;
    }

    // Stat cards ──────────────────────────────────────────────────────────────
    private JPanel buildStatCards() {
        JPanel row = new JPanel(new GridLayout(1, 4, 14, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(640, 90));
        row.setAlignmentX(LEFT_ALIGNMENT);

        row.add(statCard("IPK", "4.00", ACCENT_CYAN));
        row.add(statCard("SKS", "2", ACCENT_BLUE));
        row.add(statCard("Semester", "7", ACCENT_GOLD));
        row.add(statCard("Predikat", "Cum. Laude", new Color(100, 220, 150)));
        return row;
    }

    private JPanel statCard(String label, String value, Color accent) {
        JPanel p = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                // top accent bar
                g2.setColor(accent);
                g2.fillRoundRect(0, 0, getWidth(), 4, 4, 4);
                // glow border
                g2.setColor(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), 60));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
            }
        };
        p.setOpaque(false);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

        JLabel val = new JLabel(value);
        val.setFont(new Font("Monospaced", Font.BOLD, 20));
        val.setForeground(accent);
        val.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 10));
        lbl.setForeground(TEXT_MUTED);
        lbl.setAlignmentX(LEFT_ALIGNMENT);

        p.add(val);
        p.add(Box.createVerticalStrut(4));
        p.add(lbl);
        return p;
    }

    // Action buttons ──────────────────────────────────────────────────────────
    private JPanel buildActionButtons() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setOpaque(false);
        row.setAlignmentX(LEFT_ALIGNMENT);

        row.add(actionButton("  KELUAR KAMPUS  ", ACCENT_CYAN, BG_DARK));
        row.add(actionButton("  LOG OUT  ", ACCENT_BLUE, TEXT_WHITE));
        row.add(actionButton("  EDIT PROFIL  ", BG_CARD, TEXT_MUTED));
        return row;
    }

    private JButton actionButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color base = bg;
                if (getModel().isRollover()) base = base.brighter();
                g2.setColor(base);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(fg);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(getText().trim(), (getWidth()-fm.stringWidth(getText().trim()))/2,
                        (getHeight()+fm.getAscent()-fm.getDescent())/2);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Monospaced", Font.BOLD, 11));
        btn.setForeground(fg);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 36));
        btn.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Fitur \"" + text.trim() + "\" belum tersedia.\nSilakan hubungi admin.", "Info",
                JOptionPane.INFORMATION_MESSAGE));
        return btn;
    }

    // Footer ──────────────────────────────────────────────────────────────────
    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(20, 25, 45));
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(ACCENT_BLUE);
                g.drawLine(0, 0, getWidth(), 0);
            }
        };
        footer.setOpaque(false);
        footer.setPreferredSize(new Dimension(700, 36));
        footer.setBorder(BorderFactory.createEmptyBorder(6, 32, 6, 32));

        JLabel left = new JLabel("© 2025  Sistem Informasi Akademik");
        left.setFont(new Font("Monospaced", Font.PLAIN, 10));
        left.setForeground(TEXT_MUTED);

        JLabel right = new JLabel("NIM : 202510370110233  •  Rozaan Asyraf");
        right.setFont(new Font("Monospaced", Font.PLAIN, 10));
        right.setForeground(new Color(0, 210, 220, 160));

        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);
        return footer;
    }

    // ─── Custom Background Panel ──────────────────────────────────────────────
    static class BackgroundPanel extends JPanel {
        BackgroundPanel() { setBackground(BG_DARK); }
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            // subtle dot grid
            g2.setColor(new Color(255,255,255,8));
            for (int x = 0; x < getWidth(); x += 22)
                for (int y = 0; y < getHeight(); y += 22)
                    g2.fillOval(x, y, 2, 2);
            // bottom right glow
            RadialGradientPaint rg = new RadialGradientPaint(
                    getWidth(), getHeight(), 300,
                    new float[]{0f, 1f},
                    new Color[]{new Color(60,120,255,40), new Color(0,0,0,0)});
            g2.setPaint(rg);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }

    // ─── Glowing Card Panel ───────────────────────────────────────────────────
    static class GlowCard extends JPanel {
        private final Color glowColor;
        GlowCard(Color glow) {
            this.glowColor = glow;
            setOpaque(false);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(BG_CARD);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.setColor(glowColor);
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ─── Main ────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Use system look and feel as base, then override
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); }
        catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            StudentProfile frame = new StudentProfile();
            frame.setVisible(true);
        });
    }
}