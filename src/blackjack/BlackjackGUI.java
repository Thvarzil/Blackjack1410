package blackjack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class BlackjackGUI extends JFrame {

	private JPanel contentPane;
	private GameState gameState = new GameState();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackjackGUI frame = new BlackjackGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BlackjackGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = newLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
	
		
	}

	private JLayeredPane newLayeredPane() {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setOpaque(true);
		layeredPane.setBackground(new Color(0, 102, 51));
		
		JPanel PlayersCards = newPlayersCards();
		layeredPane.add(PlayersCards);
		
		JPanel DealersCards = newDealersCards();
		layeredPane.add(DealersCards);

		JPanel BettingPanel = newBettingPanel();
		layeredPane.add(BettingPanel);
	
		JPanel panelEndOfGame = newPanelEndOfGame();
		
		layeredPane.add(panelEndOfGame);
		
		JPanel HitStayPanel = newHitStayPanel();
		layeredPane.add(HitStayPanel);
		{
			JLabel lblNewLabel = new JLabel("You Win!!");
			lblNewLabel.setForeground(new Color(255, 255, 255));
			lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 46));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 26, 285, 38);
			layeredPane.add(lblNewLabel);
		}
		
		return layeredPane;
	}

	private JPanel newHitStayPanel() {
		JPanel HitStayPanel = new JPanel();
		HitStayPanel.setBounds(47, 202, 217, 38);
		HitStayPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnHit = newBtnHit();
		HitStayPanel.add(btnHit);
	
		JButton btnStay = newBtnStay();
		HitStayPanel.add(btnStay);
		
		return HitStayPanel;
	}

	private JButton newBtnStay() {
		JButton btnStay = new JButton("Stay");
		btnStay.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnStay;
	}

	private JButton newBtnHit() {
		JButton btnHit = new JButton("Hit");
		btnHit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnHit;
	}

	private JPanel newPanelEndOfGame() {
		JPanel panelEndOfGame = new JPanel();
		panelEndOfGame.setBounds(47, 75, 217, 38);
		panelEndOfGame.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnPlayAgain = newbtnPlayAgain();
		panelEndOfGame.add(btnPlayAgain);
	
		JButton btnClose = newbtnClose();
		panelEndOfGame.add(btnClose);
		
		return panelEndOfGame;
	}

	private JButton newbtnClose() {
		JButton btnClose = new JButton("End");
		btnClose.setFont(new Font("Monospaced", Font.PLAIN, 11));
		btnClose.addActionListener(e -> System.exit(0));

		return btnClose;
	}

	private JButton newbtnPlayAgain() {
		JButton btnPlayAgain = new JButton("Next Hand");
		btnPlayAgain.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnPlayAgain;
	}

	private JPanel newBettingPanel() {
		JPanel BettingPanel = new JPanel();
		BettingPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		BettingPanel.setBounds(90, 290, 134, 113);
		BettingPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel BidPanel = newBidPanel();
		BettingPanel.add(BidPanel);
		
		JButton btnIncreaseBet = newbtnIncreaseBet();
		BettingPanel.add(btnIncreaseBet);

		JButton btnDecreaseBet = newbtnDecreaseBet();
		BettingPanel.add(btnDecreaseBet);
		
		JButton btnSubmit = newbtnSubmit();
		BettingPanel.add(btnSubmit);
		
		return BettingPanel;
	}

	private JPanel newBidPanel() {
		JPanel BidPanel = new JPanel();
		BidPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
		BidPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel CurrentBet = newCurrentBet();
		BidPanel.add(CurrentBet);
		
		return BidPanel;
	}

	private JLabel newCurrentBet() {
		JLabel CurrentBet = new JLabel("Current Bet: 100");
		CurrentBet.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return CurrentBet;
	}

	private JButton newbtnSubmit() {
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Monospaced", Font.PLAIN, 11));
		return btnSubmit;
	}

	private JButton newbtnDecreaseBet() {
		JButton btnDecreaseBet = new JButton("\u25BC");
		return btnDecreaseBet;
	}

	private JButton newbtnIncreaseBet() {
		JButton btnIncreaseBet = new JButton("\u25B2");
		return btnIncreaseBet;
	}

	private JPanel newPlayersCards() {
		JPanel PlayersCards = new PlayersCards();
		PlayersCards.setBounds(320, 256, 335, 182);
		return PlayersCards;
	}
	
	private JPanel newDealersCards() {
		JPanel DealersCards = new DealersCards();
		DealersCards.setBounds(320, 11, 335, 182);
		return DealersCards;
	}
}
